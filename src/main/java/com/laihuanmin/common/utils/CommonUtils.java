package com.laihuanmin.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static List<File> iterateFileRecursion(File dirFile, FileFilter filter) {
        List<File> list = new ArrayList<>();
        if (dirFile == null) {
            return list;
        } else {
            boolean accept = filter.accept(dirFile);
            if (accept) {
                list.add(dirFile);
            }
            if (dirFile.isDirectory()) {
                File[] files = dirFile.listFiles();
                if (files != null) {
                    for (File file : files) {
                        List<File> crtAddList = iterateFileRecursion(file, filter);
                        list.addAll(crtAddList);
                    }
                }
            }
        }
        return list;
    }

    public static File getClassFilePath(Class clz) {
        File classFilePath = getClassFilePath(clz, "");
        return classFilePath;
    }

    public static File getClassFilePath(Class clz, String subFile) {
        String path = clz.getResource("/" + subFile).getPath();
        return new File(path);
    }
}
