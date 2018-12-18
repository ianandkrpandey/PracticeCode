package com.pratik.kinesis.producer;

import java.util.UUID;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.pratik.kinesis.config.ConfigurationUtils;
import com.pratik.kinesis.config.CredentialUtils;
import com.pratik.kinesis.factory.RecordProcessorFactory;

public class Consumer {

	public static void main(String[] args) {

		String applicationName = "c6_cd_dev_kinesis_sample_app";
		String streamName = "c6-cd-dev-ks";
		String regionName = "us-east-1";

		AWSCredentialsProvider credentialsProvider = CredentialUtils.getCredentialsProvider();
		
		String workerId = String.valueOf(UUID.randomUUID());
		KinesisClientLibConfiguration kclConfig = new KinesisClientLibConfiguration(applicationName, streamName,
				credentialsProvider, workerId).withRegionName(regionName)
						.withCommonClientConfig(ConfigurationUtils.getClientConfigWithUserAgent());
		

        IRecordProcessorFactory recordProcessorFactory = new RecordProcessorFactory();

        // Create the KCL worker with the stock trade record processor factory
        Worker worker = new Worker(recordProcessorFactory, kclConfig);

        try {
            worker.run();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
}
