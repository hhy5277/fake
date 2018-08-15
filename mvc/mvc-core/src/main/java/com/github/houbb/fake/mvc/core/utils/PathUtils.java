/*
 * Copyright (c)  2018. houbinbin Inc.
 * fake All rights reserved.
 */

package com.github.houbb.fake.mvc.core.utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2018/4/12
 *
 * @author houbinbin
 * @version 1.0
 * @since 1.7
 */
public class PathUtils {

    /**
     * 获取某一文件夹下的所有子文件/文件
     *
     * @param dirStr
     * @return
     */
    public static List<Path> getChildPathList(String dirStr) {
        List<Path> pathList = new LinkedList<>();
        Path dir = Paths.get(dirStr);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            for (Path entry : directoryStream) {
                pathList.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathList;
    }

    public static List<Path> listJavaSourceFiles(Path dir) {
        try {
            List<Path> paths = Files.walk(dir).filter(p-> p.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
            return paths;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public static void main(String[] args) throws IOException {
//        final String path = "/Users/houbinbin/code/_opensource/mvc/mvc-core/src/main/java/com/github/houbb/mvc/core/";
//        listJavaSourceFiles(Paths.get(path)).forEach(System.out::println);

        System.out.println(PathUtils.class.getClassLoader().getResource(""));
    }

}
