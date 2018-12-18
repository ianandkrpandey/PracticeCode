package com.pearson.kinesis.producer;

import java.nio.ByteBuffer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.pearson.kinesis.config.AWSKinesisCredentials;
import com.pearson.kinesis.model.Container;

/**
 * Kinesis producer sample class which produce the Container object messages to the kinesisStream data stream in AWS
 * @author VGup899
 *
 */
public class Producer {

	private static String kinesisStream = "c6-cd-dev-ks";

	public static void main(String[] args) {

		AmazonKinesis kinesisClient = AWSKinesisCredentials.getKinesisClient();
		produceContnerObject(kinesisClient);
	}

	private static void produceContnerObject(AmazonKinesis kinesisClient) {

		for (int i = 1121; i < 1131; i++) {
			Container child = new Container();
			child.setId1("urn:pearson:manifestation:" + i);
			child.setId2("urn:pearson:manifestation:" + i);
			child.setLevel(1);
			child.setType("module");

			byte[] bytes = child.toJsonAsBytes();
			if (bytes == null) {
				System.out.println("Could not get JSON bytes for container");
				return;
			}

			PutRecordRequest putRecord = new PutRecordRequest();
			putRecord.setStreamName(kinesisStream);
			putRecord.setPartitionKey(child.getId1());
			putRecord.setData(ByteBuffer.wrap(bytes));

			try {
				System.out.println("... publis record for id: " +child.getId1());
				kinesisClient.putRecord(putRecord);
			} catch (AmazonClientException ex) {
				System.out.println("Error sending record to Amazon Kinesis." + ex);
			}
		}
	}
}
