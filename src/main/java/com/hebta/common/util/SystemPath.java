package com.hebta.common.util;

import java.net.URL;

/**
 * @author Han.Sun
 * @version v 1.0
 * @description 得到当前应用的系统路径
 * @since 2017年07月22日
 */
public class SystemPath {

    public static String getSysPath() {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource("");
        if (url == null) {
            return "";
        }
        String path = url.toString();
        String temp = path.replaceFirst("file:/", "").replaceFirst(
                "target/classes/", "");
        temp = temp.replaceFirst("core/", "");
        temp = temp.replaceFirst("admin/", "");
        String separator = System.getProperty("file.separator");
        return temp.replaceAll("/", separator + separator);
    }

    public static String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        String temp = path.replaceFirst("file:/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    public static String getSystempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String getSeparator() {
        return System.getProperty("file.separator");
    }
}
