package com.springboot.quartz.entry;/**
 * Created by Administrator on 2017/6/14.
 */

/**
 * 全局系统参数
 *
 * @author renzh
 * @create 2017-06-14
 **/
public class SystemParams {
    public static final int batchSize = 500;  //批量更新size
    public static final String uploadPath = "upload";   //文件上传目录
    public static final String downloadPath = "download";  //文件下载目录
    public static final String appPath = "download/app";  //APP上传下载目录
    public static final String[] imgType = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};  //图片类型
    public static final String[] fileType = new String[]{"apk", "ipa", "gif", "jpg", "jpeg", "png", "bmp", "html"};  //文件类型
    public static final String[] appType = new String[]{"apk", "ipa"};  //APP类型
    public static final String ENCRYPT_FORMAT = "%s%s";  //密码加密格式  [password+salt]

}
