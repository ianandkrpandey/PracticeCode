package com.pearson.kinesis.config;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

/**
 * Instantiate the AWS Kinesis Credentials
 * @author VGup899
 *
 */
public class AWSKinesisCredentials {

	public static AmazonKinesis getKinesisClient() {

		String regionName = "us-east-1";
		AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();

		clientBuilder.setRegion(regionName);
		try {
			clientBuilder.setCredentials(CredentialUtils.getCredentialsProvider());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientBuilder.build();
	}
}
