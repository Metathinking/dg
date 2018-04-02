/**
 * @(#) FileUtil.java 2014/12/08 08:07
 *
 * 版权所有 (c) 北京银软网络技术有限公司
 * 北京市海淀区上地国际创业园西区1号
 * 保留所有权利
 */
package com.hu.dg.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 孙鹏鹏
 * @version 1.0
 */
public class FileUtil {
    public static boolean isImage(MultipartFile file){
        // 获得文件后缀名
        String filePath = file.getOriginalFilename();
        String extendName = filePath.substring(filePath.lastIndexOf(".") + 1,
                filePath.length());
        // 声明图片后缀名数组
        String imgeArray[] = {"jpg", "png", "jpeg", "ico","gif","bmp"};
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断符合全部类型的场合
            if (imgeArray[i].equals(extendName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为文件类型
     * @param file
     * @return
     */
    public static boolean isFile(MultipartFile file){
        // 获得文件后缀名
        String filePath = file.getOriginalFilename();
        String extendName = filePath.substring(filePath.lastIndexOf(".") + 1,
                filePath.length());
        // 声明文件后缀名数组       doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2
        String imgeArray[] = {"doc", "docx", "xls", "xlsx","ppt","htm","html","txt","zip","rar","gz","bz2"};
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断符合全部类型的场合
            if (imgeArray[i].equals(extendName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为flash类型
     * @param file
     * @return
     */
    public static boolean isFlash(MultipartFile file){
        // 获得文件后缀名
        String filePath = file.getOriginalFilename();
        String extendName = filePath.substring(filePath.lastIndexOf(".") + 1,
                filePath.length());
        // 声明文件后缀名数组
        String imgeArray[] = {"swf", "flv"};
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断符合全部类型的场合
            if (imgeArray[i].equals(extendName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为media类型
     * @param file
     * @return
     */
    public static boolean isMedia(MultipartFile file){
        // 获得文件后缀名
        String filePath = file.getOriginalFilename();
        String extendName = filePath.substring(filePath.lastIndexOf(".") + 1,
                filePath.length());
        // 声明文件后缀名数组
        String imgeArray[] = {"swf", "flv","mp3","wav","wma","wmv","mid","avi","mpg","asf","rm","rmvb"};
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断符合全部类型的场合
            if (imgeArray[i].equals(extendName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }



    public static String getFileExtendName(String fileName) {
        if (fileName.indexOf(".") < 0) {
            return "";
        }
        String extendName = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        return extendName;
    }
}