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
    @Value("${CLOUD.AWS.S3.CREDENTIALS.ACCESS-KEY}")
    private String accessKey ;
            //= System.getProperty("CLOUD.AWS.S3.CREDENTIALS.ACCESS-KEY")

    @Value("${CLOUD.AWS.S3.CREDENTIALS.SECRET-KEY}")
    private String secretKey ;
                    //= System.getProperty("CLOUD.AWS.S3.CREDENTIALS.SECRET-KEY");
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







