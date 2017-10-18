package com.springboot.quartz.base;

import com.springboot.quartz.entry.CODE;
import com.springboot.quartz.entry.RedisKeys;
import com.springboot.quartz.entry.SystemParams;
import com.springboot.quartz.exception.BizException;
import com.springboot.quartz.redis.IRedisRepository;
import com.springboot.quartz.system.entity.SysUser;
import com.springboot.quartz.util.FileUploadUtil;
import com.springboot.quartz.util.StringUtil;
import com.springboot.quartz.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 基础controller
 * @author renzh 2017/5/8
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected  HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected IRedisRepository iRedisRepository;


    /**
     * DESC：操作成功时，统一返回【成功】 返回对象
     */
    protected String ok(){
        return "SUCCESS";
    }

    /**
     * 获取当前用户ID
     * @return
     */
    protected Long getUserId() {
        return Utils.getUserId();
    }

    /**
     * 获取当前用户登录ID
     * @return
     */
    protected String getLoginId() {
        return Utils.getLoginId();
    }

    /**
     * 获取当前用户token
     * @return
     */
    protected String getUserToken() {
        return Utils.getUserToken();
    }

    /**
     * 清除登录上下文信息
     * @return
     */
    protected void clearLoginContext() {
        Utils.clearLoginContext();
    }

    /**
     * 获取当前用户
     */
    SysUser getUser() {
        try {
            String userToken = getUserToken();
            if (!StringUtil.isNull(userToken)) {
                String redisKey = String.format(RedisKeys.USER_TOKEN, getUserToken());
                return (SysUser) iRedisRepository.get(redisKey);
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //文件上传默认路径（服务器相对路径 ，如：/download/xxxxx.xls）
    public String uploadServerPath = "";

    //记录上传的文件名
    public List<String> fileList = null;

    /**
     * 通用文件上传
     */
    public void upload(MultipartFile[] multipartFiles){
        uploadToServer(multipartFiles);
    }

    /**
     * 通用文件上传 [上传到download，用于下载]
     */
    public void uploadToDownload(MultipartFile[] multipartFiles){
        uploadToServer(multipartFiles, SystemParams.fileType, SystemParams.downloadPath);
    }

    /**
     * 文件上传【Base方法1】
     * @param multipartFiles  文件入参
     */
    public void uploadToServer(MultipartFile[] multipartFiles) {
        uploadToServer(multipartFiles, null, null);
    }

    /**
     * 文件上传【Base方法2】
     * @param multipartFiles  文件入参
     */
    public void uploadToServer(MultipartFile[] multipartFiles, String[] fileType) {
        uploadToServer(multipartFiles, fileType, null);
    }

    /**
     * 文件上传【Base方法0】
     * @param multipartFiles  文件入参
     * @param fileType  文件类型
     * @param dirPath   上传到哪个目录  默认上传到upload目录
     */
    public void uploadToServer(MultipartFile[] multipartFiles, String[] fileType, String dirPath) {
        fileList = new ArrayList<>();
        if (multipartFiles == null || multipartFiles.length == 0) {
            throw new BizException(CODE.file_not_found);
        }

        fileType = StringUtil.hasNull(fileType) ? SystemParams.fileType : fileType;  //默认上传到resource下的upload目录
        dirPath = StringUtil.hasNull(dirPath) ? SystemParams.uploadPath : dirPath;  //默认上传到resource下的upload目录

        List<String> typeList = new ArrayList<>();
        typeList.addAll(Arrays.asList(fileType));

        try {
            int count = 0;
            //1、上传到本地
            for (MultipartFile multipartFile : multipartFiles) {
                ++count;
                String fileName = multipartFile.getOriginalFilename();
                if (StringUtil.isNull(fileName)) {
                    throw new BizException(CODE.file_not_found);
                }
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!fileName.contains(".") || StringUtil.isNull(suffix) || !typeList.contains(suffix.toLowerCase())) {
                    throw new BizException(CODE.file_type_error);
                }

                String newFileName = Utils.generateId() + "-" + fileName;       //生成新文件名：原始上传文件名+UUID【防止文件名重复】

                //确认上传文件夹
                System.out.println("资源文件根目录："+getRootPath());
                String uploadDir = getRootPath() + dirPath;
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }

                //上传文件
                uploadServerPath = uploadDir + "/" + newFileName;             //文件完整路径
                File localFile = new File(uploadServerPath);
                if (localFile.exists() && localFile.isFile()) {
                    localFile.delete();
                }
                try {
                    multipartFile.transferTo(localFile);
                    fileList.add(uploadServerPath);//记录文件全路径
                } catch (IOException e) {
                    for (String file : fileList) {//出现异常时，删除已上传到本地的文件
                        File fileTmp = new File(file);
                        fileTmp.delete();
                    }
                    throw new BizException(CODE.file_upload_fail);
                }
            }

            if (count == 0 || StringUtil.hasNull(fileList)) {
                throw new BizException(CODE.no_file_upload);
            }
        } catch (Exception e) {
            throw new BizException(CODE.file_upload_fail);
        }
    }

    /**
     * APP文件上传
     */
    public void uploadApp(MultipartFile[] multipartFiles){
        uploadToServer(multipartFiles, SystemParams.appType, SystemParams.appPath);
    }

    /**
     * 图片文件上传
     */
    public List<String> uploadImg(MultipartFile[] multipartFiles){
        List<String> urls = new ArrayList<>();
        if (multipartFiles == null || multipartFiles.length == 0) {
            return urls;
        }

        //1、上传到本地服务器临时目录
        uploadToServer(multipartFiles, SystemParams.imgType);

        //2、上传到图像服务器
        urls = FileUploadUtil.upload(fileList);

        //3、上传成功后删除临时文件
        for (String file : fileList) {
            File fileTmp = new File(file);
            fileTmp.delete();
        }

        return urls;
    }

    /**
     * 获取项目根目录路径（即classes路径）
     * @return
     */
    public String getRootPath(){
        return this.getClass().getResource("/").getPath();
    }
}
