package com.pearson.kinesis.producer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.pearson.kinesis.config.AWSKinesisCredentials;
import com.pearson.kinesis.model.Container;

/**
 * Kinesis producer sample class which produce the Container object messages to
 * the kinesisStream data stream in AWS
 * 
 * @author VGup899
 *
 */
public class Producer {

	private static String kinesisStream = "c6-cd-dev-ks";

	public static void main(String[] args) {

		AmazonKinesis kinesisClient = AWSKinesisCredentials.getKinesisClient();

	//produceRecordsInSequence(kinesisClient); // to produce records in sequence
		produceRecordsInBatch(kinesisClient); // to produce records in batch..max supported 500 records/batch
	}

	private static void produceRecordsInSequence(AmazonKinesis kinesisClient) {
		for (int i = 0; i < 100; i++) {

			Container child = getContainerObject(i);
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
				System.out.println("... publish record for id: " + child.getId1());
				kinesisClient.putRecord(putRecord);
			} catch (AmazonClientException ex) {
				System.out.println("Error sending record to Amazon Kinesis." + ex);
			}
		}
	}

	/**
	 * this method push records in batch to kinesis data stream
	 * 
	 * @param kinesisClient
	 */
	private static void produceRecordsInBatch(AmazonKinesis kinesisClient) {

		PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
		putRecordsRequest.setStreamName(kinesisStream);
		List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();
		for (int i = 200; i < 400; i++) {

			Container child = getContainerObject(i);
			byte[] bytes = child.toJsonAsBytes();
			if (bytes == null) {
				System.out.println("Could not get JSON bytes for container");
				return;
			}

			PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
			putRecordsRequestEntry.setData(ByteBuffer.wrap(bytes));
			putRecordsRequestEntry.setPartitionKey(child.getId1());
			putRecordsRequestEntryList.add(putRecordsRequestEntry);
		}

		putRecordsRequest.setRecords(putRecordsRequestEntryList);
		System.out.println("---- pushing records in Batch ----");
		PutRecordsResult putRecordsResult = kinesisClient.putRecords(putRecordsRequest);
		System.out.println("Put Result" + putRecordsResult);
	}

	private static Container getContainerObject(int i) {
		Container child = new Container();
		child.setId1("urn:pearson:manifestation:" + i);
		child.setId2("urn:pearson:manifestation:" + i);
		child.setLevel(1);
		child.setType("module");
		return child;
	}
}
