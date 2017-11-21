package com.lichi.increaselimit.common.utils;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import lombok.extern.slf4j.Slf4j;

/**
 * 七牛云文件上传
 * @author majie
 *
 */
@Slf4j
public class UploadUtils {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private final String ACCESS_KEY = "Rop8ks5Q051Er5uVir2P_hV4MuS4S-qUp7fHaMfA";
    private final String SECRET_KEY = "6wC-8djnXfb_InRcpXZk5OJz2FZa7P_bRf1KuqAl";
    //要上传的空间
    private String bucketname = "lichi";
    
    private String url = "ozlfwi1zj.bkt.clouddn.com";

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    private Zone z;
    private Configuration c;

    //创建上传对象
    private UploadManager uploadManager;
    
    //密钥配置
    private Auth auth;
    
    public UploadUtils() {
    	z = Zone.autoZone();
    	c = new Configuration(z);
    	uploadManager = new UploadManager(c);
    	auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String upload(MultipartFile file,String fileName) throws IOException {
        //调用put方法上传
        Response res = uploadManager.put(file.getInputStream(), fileName,getUpToken(),null, null);
        //打印返回的信息
        String bodyString = res.bodyString();
        log.info("七牛云返回数据为：" + bodyString);
        StringMap object = res.jsonToMap();
        Object object2 = object.get("key");
		return url + "/" + object2;
    }

}