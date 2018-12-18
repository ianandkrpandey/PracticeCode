package com.pearson.global.configuration.model;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

@DynamoDBTable(tableName = "mypedia_dev_configuration")
public class Result {
	@DynamoDBAttribute(attributeName = "functionname")
	private String methodname;
	@DynamoDBHashKey(attributeName = "env")
	private String env;
	@DynamoDBAttribute(attributeName = "configuration")
	private Map<String,AttributeValue> configuration;
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public Map<String,AttributeValue> getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Map<String,AttributeValue> configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public String toString(){
		StringBuffer response=new StringBuffer();
		response.append("{\"methodname\":\""+getMethodname()+"\",\"env\":\""+getEnv()+"\"");
		StringBuffer configuration=new StringBuffer();
		getConfiguration().keySet().forEach(key->{AttributeValue aval=getConfiguration().get(key);if(configuration.toString().length()>0){configuration.append(",");};configuration.append("\""+key+"\":\"");configuration.append(aval.getS());configuration.append("\"");});		
		response.append(",\"configuration\":{").append(configuration).append("}}");		
		return response.toString();
	}
	
}