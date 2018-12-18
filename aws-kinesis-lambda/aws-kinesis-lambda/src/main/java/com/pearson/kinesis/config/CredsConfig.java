package com.pearson.kinesis.config;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

public class CredsConfig {

	public static AmazonKinesis getKinesisClient() {
		
        String regionName = "us-east-1";

        AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();

		clientBuilder.setRegion(regionName);
        try {
			clientBuilder.setCredentials(CredentialUtils.getCredentialsProvider());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        clientBuilder.setClientConfiguration(ConfigurationUtils.getClientConfigWithUserAgent());
        
        return clientBuilder.build();
        
	}
}
