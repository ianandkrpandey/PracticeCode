package com.pearson.kinesis.consumer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class GenericKinesisConsumerHandler<T> implements RequestHandler<T, Void> {


	@Override
	public Void handleRequest(T input, Context context) {
		if(input instanceof KinesisEvent) {
			KinesisConsumerHandler kinesisevent = new KinesisConsumerHandler();
			kinesisevent.handleRequest((KinesisEvent)input, context);
		}
		else if(input instanceof SNSEvent) {
			
		}
		// TODO Auto-generated method stub
		return null;
	}

}
