package com.evoke.cartshop.services;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AwsS3Service {

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonStorage;

    public String save(MultipartFile file) {
        File convertedFile = convertMultiPartFileToFile(file);
        String fileName = file.getOriginalFilename();
        amazonStorage.putObject(new PutObjectRequest(bucket, fileName, convertedFile));
        return "https://" + bucket + ".s3.amazonaws.com/" + fileName;
    }
    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(convertedFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;

    }
}
