package com.pearson.global.configuration.model;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.fasterxml.jackson.annotation.JsonView;
import com.pearson.global.utils.Constants;

/**
 * 
 */

/**
 * @author VGUDLSA
 *
 */
@DynamoDBTable(tableName = Constants.TABLENAME)
public class GlobalConfigModel {
	
	@DynamoDBHashKey(attributeName = Constants.ENVIRONMENT)
	private String env;
	
	@DynamoDBAttribute(attributeName = Constants.FUNCTIONNAME)
	private String functionname;
	
	@DynamoDBAttribute(attributeName = "configuration")
	@JsonView
	private Map<String,String> configuration;
	
	@DynamoDBAttribute(attributeName = "event")
	private String event;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
