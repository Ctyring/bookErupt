package com.example.book.proxy;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.AttachmentProxy;
import xyz.erupt.core.exception.EruptWebApiRuntimeException;
import xyz.erupt.core.util.MimeUtil;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 七牛对象存储demo
 *
 * @author yuepeng
 * @date 2020-05-17
 */
@Service
public class MinioProxy implements AttachmentProxy {

    @Value("${minio.endpoint}")
    private  String ENDPOINT;
    @Value("${minio.bucketName}")
    private  String BUCKETNAME;
    @Value("${minio.accessKey}")
    private  String ACCESSKEY;
    @Value("${minio.secretKey}")
    private  String SECRETKEY;


    @Override
    public String upLoad(InputStream inputStream, String path) {

        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESSKEY, SECRETKEY);
            //存入bucket不存在则创建，并设置为只读
            if (!minioClient.bucketExists(BUCKETNAME)) {
                minioClient.makeBucket(BUCKETNAME);
                minioClient.setBucketPolicy(BUCKETNAME, "*.*", PolicyType.READ_ONLY);
            }
            // 文件存储的目录结构
            String objectName = path;
            //xxxxxxxxxxxxxxxxxx.jpg
            // 存储文件
            minioClient.putObject(BUCKETNAME, objectName, inputStream,objectName.substring(objectName.indexOf(".")));
            path=ENDPOINT + "/" + BUCKETNAME + "/" + objectName;
        } catch (Exception e) {
            throw new EruptWebApiRuntimeException("出现错误");
        }
        return path;
    }

    @Override
    public boolean isLocalSave() {
        return false;
    }

    @Override
    public String fileDomain() {
        return "http://oos.erupt.xyz";
    }
}