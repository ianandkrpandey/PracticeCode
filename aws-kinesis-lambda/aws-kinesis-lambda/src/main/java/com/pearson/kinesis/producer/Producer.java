package com.pearson.kinesis.producer;

import java.nio.ByteBuffer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.pearson.kinesis.config.CredsConfig;
import com.pearson.kinesis.model.Container;

public class Producer {

	private static String kinesisStream = "c6-cd-dev-ks";

	public static void main(String[] args) {

		AmazonKinesis kinesisClient = CredsConfig.getKinesisClient();

		produceContnerObject(kinesisClient);

		/*
		 * for (int i = 1; i < 2; i++) {
		 * 
		 * byte[] b = new byte[1]; b[0] = (byte) i; PutRecordRequest putRecord =
		 * new PutRecordRequest(); putRecord.setStreamName(kinesisStream);
		 * putRecord.setPartitionKey(String.valueOf(i));
		 * System.out.println("writing.." + Arrays.toString(b));
		 * putRecord.setData(ByteBuffer.wrap(b));
		 * kinesisClient.putRecord(putRecord); }
		 */
	}

	private static void produceContnerObject(AmazonKinesis kinesisClient) {

		for (int i = 22; i < 30; i++) {
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
