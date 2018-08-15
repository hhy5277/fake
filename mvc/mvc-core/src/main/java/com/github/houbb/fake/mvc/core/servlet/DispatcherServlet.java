/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.servlet;

import com.github.houbb.fake.mvc.annotation.Autowired;
import com.github.houbb.fake.mvc.annotation.Controller;
import com.github.houbb.fake.mvc.annotation.RequestMapping;
import com.github.houbb.fake.mvc.annotation.RequestParam;
import com.github.houbb.fake.mvc.annotation.Service;
import com.github.houbb.fake.mvc.core.dto.HandlerMethodDto;
import com.github.houbb.fake.mvc.core.dto.ParamDto;
import com.github.houbb.fake.mvc.core.exception.MvcRuntimeException;
import com.github.houbb.fake.mvc.core.utils.ReflectionUtils;
import com.github.houbb.fake.mvc.core.utils.StringUtils;
import com.github.houbb.paradise.common.util.ArrayUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2018/4/9
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -1348648710084069715L;

    /**
     * 存放 class 信息
     */
    private List<Class> classList = new LinkedList<>();

    /**
     * 存放实例化对象信息
     */
    private Map<String, Object> instanceMap = new HashMap<>();


    /**
     * URL 请求与方法间的映射
     */
    private Map<String, HandlerMethodDto> urlHandlerMethodMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1. 这里可以直接从配置文件读取
        final String scanPackage = config.getInitParameter("scanPackage");

        scanPackage(scanPackage);
        instanceClass();
        fillAutoWiredInstance();
        urlHandlerMethodMap();

        System.out.println("urlHandlerMethodMap=========" + urlHandlerMethodMap);
        //After init
    }


    private void scanPackage(String pkgName) {
        //获取指定的包的实际路径url，将com.tianyalei.mvc变成目录结构com/tianyalei/mvc
        URL url = Thread.currentThread().getContextClassLoader().getResource(pkgName.replaceAll("\\.", "/"));
        //转化成file对象
        File dir = new File(url.getFile());
        //递归查询所有的class文件
        for (File file : dir.listFiles()) {
            //如果是目录，就递归目录的下一层，如com.tianyalei.mvc.controller
            if (file.isDirectory()) {
                scanPackage(pkgName + "." + file.getName());
            } else {
                //如果是class文件，并且是需要被spring托管的
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                //举例，className = com.github.houbb.mvc.controller.WebController
                String className = pkgName + "." + file.getName().replace(".class", "");
                //判断是否被Controller或者Service注解了，如果没注解，那么我们就不管它，譬如annotation包和DispatcherServlet类我们就不处理
                try {
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class)) {
                        classList.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 初始化 class
     */
    private void instanceClass() {
        try {
            for(Class clazz : classList) {
                String name = getInstanceName(clazz);
                instanceMap.put(name, clazz.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new MvcRuntimeException(e);
        }
    }

    /**
     * 构建自动装配的实例
     */
    private void fillAutoWiredInstance() {
        //1. 遍历实体列表
        for(Class clazz : classList) {
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields) {
                if(field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    String autowiredName = getAutowiredName(field);
                    Object current = instanceMap.get(getInstanceName(clazz));
                    Object autowiredField = instanceMap.get(autowiredName);
                    try {
                        field.set(current, autowiredField);
                    } catch (IllegalAccessException e) {
                        throw new MvcRuntimeException(e);
                    }
                }
            }
        }
    }


    /**
     * 获取实例化名称
     * 1. controller 直接返回 value，或者类名首字母小写
     * 2. service 直接返回 value，或者接口类名首字母小写（如果没有接口，则使用类名首字母小写）
     * @param clazz
     * @return
     */
    private String getInstanceName(final Class clazz) {
        if(clazz.isAnnotationPresent(Controller.class)) {
            Controller controller = (Controller) clazz.getAnnotation(Controller.class);
            String value = controller.value();
            if(StringUtils.isNotEmpty(value)) {
                return value;
            }
            return StringUtils.firstCharLower(clazz.getSimpleName());
        }
        if(clazz.isAnnotationPresent(Service.class)) {
            Service service = (Service) clazz.getAnnotation(Service.class);
            String value = service.value();
            if(StringUtils.isNotEmpty(value)) {
                return value;
            }

            Class<?>[] interfacesArray = clazz.getInterfaces();
            if(ArrayUtil.isNotEmpty(interfacesArray)) {
                Class parentClass = interfacesArray[0];
                return StringUtils.firstCharLower(parentClass.getSimpleName());
            }

            return StringUtils.firstCharLower(clazz.getSimpleName());
        }
        return null;
    }

    private String getAutowiredName(Field field) {
        Class clazz = field.getType();
        return StringUtils.firstCharLower(clazz.getSimpleName());
    }

    /**
     * 请求的 url 与控制的方法之间的映射
     */
    private void urlHandlerMethodMap() {
        if(instanceMap.isEmpty()) {
            return;
        }

        for(Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            Object object = entry.getValue();
            Class<?> clazz = object.getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            //1. controller 类的处理
            String baseUrl = "/";
            if(clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl += requestMapping.value();
            }

            //2. requestMapping 方法的处理
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                if(!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }

                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String tempUrl = baseUrl + requestMapping.value();
                String realUrl = tempUrl.replaceAll("/+", "/");

                urlHandlerMethodMap.put(realUrl, buildHandlerMethodDto(object, method));
            }
        }
    }

    /**
     * 构建 controller 操作方法的对象
     * @param object controller 对象
     * @param method 方法
     * @return 操作的对象
     */
    private HandlerMethodDto buildHandlerMethodDto(Object object, Method method) {
        HandlerMethodDto dto = new HandlerMethodDto();
        //1. method
        dto.setMethod(method);
        //2. controller
        dto.setController(object);

        //3. args
        Parameter[] parameters = method.getParameters();
        if(ArrayUtil.isNotEmpty(parameters)) {
            Set<ParamDto> paramDtoSet = new HashSet<>();

            List<String> paramNameList = ReflectionUtils.getParamNames(method);
            for(int i = 0; i < paramNameList.size(); i++) {
                //1. JDK 1.7 及其之前，无法获取到 paramName
                String paramName = paramNameList.get(i);
                //是否必填
                boolean required = true;

                Parameter parameter = parameters[i];
                Class<?> type = parameter.getType();

                if(parameter.isAnnotationPresent(RequestParam.class)) {
                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                    if(StringUtils.isNotEmpty(requestParam.value())) {
                        paramName = requestParam.value();
                        required = requestParam.required();
                    }
                }

                ParamDto paramDto = new ParamDto();
                paramDto.setName(paramName);
                paramDto.setIndex(i);
                paramDto.setRequired(required);
                paramDto.setType(type);
                paramDtoSet.add(paramDto);
            }
            dto.setParams(paramDtoSet);
        }

        return dto;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String reqUrl = req.getRequestURI();
            if("/favicon.ico".endsWith(reqUrl)) {
                return;
            }

            out(resp, "请求到我啦 "+reqUrl);
            boolean isSuccess = isInvokeMethodSuccess(req, resp);
            if(!isSuccess) {
                out(resp,"404 not found");
            }
        } catch (Exception e) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            e.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();
            buf.close();
            out(resp, "500 Exception" + "\n" + expMessage);
        }
    }

    /**
     * 是否成功反射执行方法
     * @param req
     * @param resp
     * @return {@code true} 成功执行
     */
    private boolean isInvokeMethodSuccess(HttpServletRequest req, HttpServletResponse resp)
            throws InvocationTargetException, IllegalAccessException {
        String requestUrl = req.getRequestURI();
        System.out.println("requestUrl: " + requestUrl);
        HandlerMethodDto handlerMethodDto = urlHandlerMethodMap.get(requestUrl);
        if(handlerMethodDto == null) {
            return false;
        }

        Method method = handlerMethodDto.getMethod();

        Object[] paramValues = new Object[handlerMethodDto.getParams().size()];

        for(ParamDto paramDto : handlerMethodDto.getParams()) {
            Class<?> type = paramDto.getType();
            if(type.equals(HttpServletRequest.class)){
                paramValues[paramDto.getIndex()] = req;
            } else if(type.equals(HttpServletResponse.class)) {
                paramValues[paramDto.getIndex()] = resp;
            } else {
                //1. 根据名称获取对应的参数
                String requestParamStr = req.getParameter(paramDto.getName());
                paramValues[paramDto.getIndex()] = convert(requestParamStr, type);
            }
        }
        method.invoke(handlerMethodDto.getController(), paramValues);
        return true;
    }

    /**
     * 类型转换
     * @param parameter 原书参数
     * @param targetType 目标类型
     * @return 对应结果
     */
    private Object convert(String parameter, Class<?> targetType) {
        if (targetType == String.class) {
            return parameter;
        } else if (targetType == Integer.class || targetType == int.class) {
            return Integer.valueOf(parameter);
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.valueOf(parameter);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            if ("true".equals(parameter.toLowerCase()) || "1".equals(parameter)) {
                return true;
            } else if ("false".equals(parameter.toLowerCase()) || "0".equals(parameter)) {
                return false;
            }
            throw new RuntimeException("不支持的参数");
        }
        else {
            //TODO 还有很多其他的类型，char、double之类的依次类推，也可以做List<>, Array, Map之类的转化
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void out(HttpServletResponse response, String str) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
