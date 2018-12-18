package com.jackson.conversion;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToJson {
public static void main(String[] args) throws IOException {
	String input ="{\"body\":\"{\"RequestMetadata\":{\"requester\":\"john.doe@pearson.com\",\"preview\":false,\"redeliveryIndicator\":true,\"requestid\":\"d290f1ee-6c54-4b01-90e6-d701748f0851\",\"learningTool\":\"eT2\",\"distributableVersionUrn\":\"urn:pearson:distributable:e9c139be-3cf4-4508-87aa-3ee7b883343c\",\"destination\":\"CITE\",\"distributableContentUrn\":\"urn:pearson:entity:d290f1ee-6c54-4b01-90e6-d701748f0851\",\"section\":[\"string\"],\"title\":\"A Test Title\",\"version\":2,\"timestamp\":{}},\"ResponseMetadata\":{\"requestStatusCode\":200,\"requestStatus\":\"success\"}}\",\"statusCode\":200}";
/*ObjectMapper om = new ObjectMapper();
String op = om.writeValueAsString(input);
JsonNode actualObj = om.readTree("{\"body\":\"{\\\"RequestMetadata\\\":{\\\"requester\\\":\\\"john.doe@pearson.com\\\",\\\"preview\\\":false,\\\"redeliveryIndicator\\\":true,\\\"requestid\\\":\\\"d290f1ee-6c54-4b01-90e6-d701748f0851\\\",\\\"learningTool\\\":\\\"eT2\\\",\\\"distributableVersionUrn\\\":\\\"urn:pearson:distributable:e9c139be-3cf4-4508-87aa-3ee7b883343c\\\",\\\"destination\\\":\\\"CITE\\\",\\\"distributableContentUrn\\\":\\\"urn:pearson:entity:d290f1ee-6c54-4b01-90e6-d701748f0851\\\",\\\"section\\\":[\\\"string\\\"],\\\"title\\\":\\\"A Test Title\\\",\\\"version\\\":2,\\\"timestamp\\\":{}},\\\"ResponseMetadata\\\":{\\\"requestStatusCode\\\":200,\\\"requestStatus\\\":\\\"success\\\"}}\",\"statusCode\":200}");
//actualObj.toString().replace("\\", "");
actualObj.toString().replaceAll("\\\\\"", "\"");
System.out.println(actualObj);

String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";*/
String ss = "{\"body\":\"{\"RequestMetadata\":{\"requester\":\"john.doe@pearson.com\",\"preview\":false,\"redeliveryIndicator\":true,\"requestid\":\"d290f1ee-6c54-4b01-90e6-d701748f0851\",\"learningTool\":\"eT2\",\"distributableVersionUrn\":\"urn:pearson:distributable:e9c139be-3cf4-4508-87aa-3ee7b883343c\",\"destination\":\"CITE\",\"distributableContentUrn\":\"urn:pearson:entity:d290f1ee-6c54-4b01-90e6-d701748f0851\",\"section\":[\"string\"],\"title\":\"A Test Title\",\"version\":2,\"timestamp\":{}},\"ResponseMetadata\":{\"requestStatusCode\":200,\"requestStatus\":\"success\"}}\",\"statusCode\":200}";
ObjectMapper mapper = new ObjectMapper();
ss.replaceAll("\\\"", "\"");
System.out.println(ss);
//mapper.writeValue(out, value);
//mapper.writeV
//JsonNode actualObj1 = mapper.readTree(ss);
//System.out.println(actualObj1.toString());
}
}
