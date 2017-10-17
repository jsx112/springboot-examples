package com.jsx.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsx.entry.CODE;
import com.jsx.exception.BizException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传工具类   [目前只支持上传图像]
 * @author renzh 2017/5/19
 */
public class FileUploadUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private static final String url = "http://file.13322.com/upload/uploadImage.do";  //文件服务器url

    /**
     * 单文件上传
     * @param fileName  本地文件名（完整路径）
     * @return
     */
    public static String upload(String fileName)
    {
        HttpClient client = new HttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            File file = new File(fileName);// 判断文件是否存在存不存在
            if (!file.exists()){
                throw new BizException(CODE.file_not_found);
            }
            PostMethod postMethod = new PostMethod(url);
            FilePart filePart = new FilePart("imageFile", file);   // imageFile 文件服务器接收的参数名
            Part[] parts = { filePart };

            /**
             *对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
             */
            MultipartRequestEntity mre = new MultipartRequestEntity(parts,postMethod.getParams());
            postMethod.setRequestEntity(mre);

            /**
             * 执行请求，返回状态码
             */
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK){
                String result = postMethod.getResponseBodyAsString();  /** 上传到服务器 返回结果格式={result:"",url:"",msg:""} */
                logger.info("文件上传结果：" + result);  //打印文件上传结果到日志

                if (result != null && !result.trim().equals("")){
                    Map resultMap = objectMapper.readValue(result,Map.class);   // 解析返回信息
                    String code = resultMap.get("result").toString();       // 返回结果：0表示成功，其他表示失败
                    if ("0".equals(code)) {
                        return resultMap.get("url").toString();  //上传成功时，返回文件url，此时msg=null
                    }else{
                        logger.error("" + resultMap.get("msg"));      //上传失败时，打印提示信息，此时url=null
                        return null;
                    }
                }
            }else{
                throw new BizException(CODE.file_upload_server_error);
            }
        }
        catch (Exception e){
            throw new BizException(CODE.system_error);
        }

        return null;
    }

    /**
     * 多文件上传
     * @param fileNames  本地文件名列表（完整路径）
     * @return
     */
    public static List<String> upload(List<String> fileNames)
    {
        List<String> urls = new ArrayList<>();
        if (StringUtil.hasNull(fileNames)) {
            return urls;
        }

        for (String fileName : fileNames) {
            String url = FileUploadUtil.upload(fileName);
            if (!StringUtil.isNull(url)) {
                urls.add(url);
            }else{
                throw new BizException(CODE.file_upload_fail);
            }
        }

        return urls;
    }
}
