package com.hebta.common.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {

    public static void createFile(File tempFile, MultipartFile file) {
        try (FileOutputStream fos = FileUtils.openOutputStream(tempFile); InputStream inputStream = file.getInputStream()) {
            IOUtils.copy(inputStream, fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void createFolder(String filePath) {
        File file = new File(filePath);
        // 如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("//不存在");
            if (!file.mkdirs()) {
                System.out.println("//创建失败");
            }
        } else {
            System.out.println("//目录存在");
        }
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            File myFilePath = new File(folderPath);
            if (myFilePath.delete()) {
                System.out.println("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        if (tempList == null) {
            return;
        }
        File temp;
        for (String tempPath : tempList) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempPath);
            } else {
                temp = new File(path + File.separator + tempPath);
            }
            if (temp.isFile()) {
                if (temp.delete()) {
                    System.out.println("删除成功");
                }
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempPath);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempPath);// 再删除空文件夹
            }
        }
    }

}
