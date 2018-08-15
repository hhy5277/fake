/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.dto;

/**
 * 2018/4/18
 * 参数临时传输层
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class ParamDto {

    /**
     * 参数类型
     */
    private Class<?> type;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 下标
     */
    private int index;

    /**
     * 当前字段是否必填
     */
    private boolean required;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParamDto paramDto = (ParamDto) o;

        if (index != paramDto.index) return false;
        if (required != paramDto.required) return false;
        if (type != null ? !type.equals(paramDto.type) : paramDto.type != null) return false;
        return name != null ? name.equals(paramDto.name) : paramDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + index;
        result = 31 * result + (required ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParamDto{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", index=" + index +
                ", required=" + required +
                '}';
    }
}
