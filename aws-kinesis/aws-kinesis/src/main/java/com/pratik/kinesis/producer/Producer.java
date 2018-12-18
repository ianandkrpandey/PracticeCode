package com.pratik.kinesis.producer;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.pratik.kinesis.config.CredsConfig;

public class Producer {

	private static String kinesisStream="c6-cd-dev-ks"; 
	
	public static void main(String[] args) {
		

        
        AmazonKinesis kinesisClient = CredsConfig.getKinesisClient();
        
        for(int i=40 ; i < 41 ; i++) {
        	
        	byte[] b = new byte[1];
        	b[0] = (byte)i;
    		PutRecordRequest putRecord = new PutRecordRequest();
            putRecord.setStreamName(kinesisStream);
        	putRecord.setPartitionKey(String.valueOf(i));
        	System.out.println("writing.." + Arrays.toString(b));
            putRecord.setData(ByteBuffer.wrap(b));
            kinesisClient.putRecord(putRecord);
            
        }
        
	}
}

