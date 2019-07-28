package com.laihuanmin.common.utils;

import java.io.File;

public class CommonUtils {
    public static File getClassFilePath(Class clz, String subFile) {
        File classFilePath = getClassFilePath(clz);
        return new File(classFilePath, subFile);
    }

    public static File getClassFilePath(Class clz) {
        String path = clz.getResource("/").getPath();
        return new File(path);
    }
}
