package com.jackson.conversion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToMap {
public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
	String lastStatus = "{\r\n" + 
			"  \"endtime\": \"2018-06-22T06:46:57Z\",\r\n" + 
			"  \"message\": \" || Router ID: 7849830a-58a8-5e50-8ac5-d72dbfae1104 || Batches [32f1554e-234f-4020-bc23-822ac34bef3d, 0acc3fd0-f940-43c0-868b-431ab27c227c, b688c2f2-8f8b-4c35-bb0c-b9799dd0a2bf, cdb92d8d-313e-4854-ade1-53317773188a, 9bf6efbe-cc24-4254-a132-c0383485608d] for transaction 887ad931-e19d-447a-bce2-821fbab75aa6 has timed out\",\r\n" + 
			"  \"starttime\": \"2018-06-22T06:46:55Z\",\r\n" + 
			"  \"workflowtask\": \"ERROR\"\r\n" + 
			"}";
	ObjectMapper mapper = new ObjectMapper();


	Map<String, Object> map = new HashMap<String, Object>();

	// convert JSON string to Map
	map = mapper.readValue(lastStatus, new TypeReference<Map<String, String>>(){});

	//System.out.println(map.get("message"));
	String msg = (String) map.get("message");
	int index = ((String) map.get("message")).lastIndexOf("||");
	System.out.println(msg.substring(index).replace("||", ""));
	System.out.println(map.get("fssfsd"));
}
}
