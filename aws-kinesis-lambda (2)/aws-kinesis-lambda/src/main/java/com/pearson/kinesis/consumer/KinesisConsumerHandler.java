package com.pearson.kinesis.consumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent.KinesisEventRecord;
import com.pearson.kinesis.model.Container;

/**
 * Kinesis consumer lambda handler which triggers as soon as the messages are
 * produced to the kinesis data stream configured for this lambda function in AWS
 * console.
 * 
 * @author VGup899
 *
 */
public class KinesisConsumerHandler implements RequestHandler<KinesisEvent, Void> {
	

	public Void handleRequest(KinesisEvent event, Context context) {

		for (KinesisEventRecord record : event.getRecords()) {
			processRecord(record, context);
		}
		return null;
	}

	private void processRecord(KinesisEventRecord record, Context context) {
		Container containerObj = Container.fromJsonAsBytes(record.getKinesis().getData().array());
		context.getLogger().log("--- Container Obj:" + containerObj);

	}
}
