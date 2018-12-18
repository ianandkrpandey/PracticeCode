package com.pearson.global.configuration.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.global.configuration.dao.DynamoDBConfig;
import com.pearson.global.configuration.dao.DynamoDBImpl;
import com.pearson.global.configuration.model.GlobalConfigModel;
import com.pearson.global.utils.Constants;



public class GlobalConfigLambdaFunctionHandler implements RequestStreamHandler, RequestHandler<Object, String> {

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		if(context!=null){
			LambdaLogger logger = context.getLogger();
			logger.log("Fetching Configuration Details");
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode readTree = objectMapper.readTree(input);
			
			String env = readTree.path(Constants.PARAMS).path(Constants.QUERYSTRING).path(Constants.ENVIRONMENT).asText();
			String functionname = readTree.path(Constants.PARAMS).path(Constants.QUERYSTRING).path(Constants.FUNCTIONNAME).asText();
			logger.log("output"+getLamdaconfigurations(env,functionname));
			output.write(objectMapper.writeValueAsBytes(getLamdaconfigurations(env,functionname)));
			logger.log("Completed getting Configuration Details");
		}
		
	}
	
	private GlobalConfigModel getLamdaconfigurations(String env,String functioName) {
		AmazonDynamoDB init = null;
		try {
			init = DynamoDBConfig.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":functionname", new AttributeValue().withS(functioName));
        
		DynamoDBQueryExpression<GlobalConfigModel> queryexp = new DynamoDBQueryExpression<>();
		GlobalConfigModel configModel = new GlobalConfigModel();
		configModel.setEnv(env);
		queryexp.setHashKeyValues(configModel);
		queryexp.withFilterExpression("functionname=:functionname").withExpressionAttributeValues(eav);
		
		DynamoDBImpl dynamoDBImpl = new DynamoDBImpl(init);
		
		PaginatedQueryList<GlobalConfigModel> LambdaConfigs = dynamoDBImpl.query(GlobalConfigModel.class, queryexp);
		
		return LambdaConfigs.get(0);
	}

	@Override
	public String handleRequest(Object arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
