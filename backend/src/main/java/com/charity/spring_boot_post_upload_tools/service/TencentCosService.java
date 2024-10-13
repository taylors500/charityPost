package com.charity.spring_boot_post_upload_tools.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class TencentCosService {

    private final String secretId = "你的SecretId";  // 腾讯云 COS 的 SecretId
    private final String secretKey = "你的SecretKey";  // 腾讯云 COS 的 SecretKey
    private final String bucketName = "你的BucketName";  // 存储桶名称
    private final String regionName = "ap-region";  // 地域

    private COSClient initCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        return new COSClient(cred, clientConfig);
    }

    public String uploadFile(MultipartFile file) {
        COSClient cosClient = initCosClient();
        String fileUrl = "";

        try {
            File convertedFile = convertMultiPartToFile(file);
            String key = "uploads/" + file.getOriginalFilename();  // 文件存储在 COS 中的路径

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, convertedFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

            fileUrl = "https://" + bucketName + ".cos." + regionName + ".myqcloud.com/" + key;

            // 删除临时文件
            convertedFile.delete();

        } catch (CosClientException | IOException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }

        return fileUrl;
    }

    // 将 MultipartFile 转换为 File
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
