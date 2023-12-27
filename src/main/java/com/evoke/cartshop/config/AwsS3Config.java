package com.evoke.cartshop.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
    private String accessKey = System.getProperty("cloud.aws.s3.credentials.access-key");
    private String secretKey = System.getProperty("cloud.aws.s3.credentials.secret-key");
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3 getS3Client() {
        AWSCredentials credentials=new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build();
    }
}







