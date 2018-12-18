/**
 * 
 */
package com.pearson.global.configuration.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.global.configuration.dao.DynamoDBConfig;
import com.pearson.global.configuration.dao.DynamoDBImpl;
import com.pearson.global.configuration.model.GlobalConfigModel;


/**
 * @author VGUDLSA
 *
 */
public class SaveGlobalConfigLambdaHandler implements RequestStreamHandler, RequestHandler<Object, String>{

	AmazonDynamoDB amazonDynamoDB = null;

	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		if (context != null) {
			LambdaLogger logger = context.getLogger();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode readTree = objectMapper.readTree(input);

			GlobalConfigModel globalConfigModel = objectMapper.treeToValue(readTree, GlobalConfigModel.class);
			
			logger.log("DYnamo ID :  " + globalConfigModel.getEnv());

			GlobalConfigModel result = saveAndGetLamdaConfiguration(globalConfigModel);

			output.write(objectMapper.writeValueAsBytes("Record saved "+result));
			logger.log("completed " + result);

		}

	}

	private GlobalConfigModel saveAndGetLamdaConfiguration(GlobalConfigModel objects) {
		AmazonDynamoDB init = null;
		try {
			init = DynamoDBConfig.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DynamoDBImpl dynamoDBImpl = new DynamoDBImpl(init);
		return dynamoDBImpl.save(objects);
	}

	public String handleRequest(Object arg0, Context arg1) {
		return null;
	}

}
