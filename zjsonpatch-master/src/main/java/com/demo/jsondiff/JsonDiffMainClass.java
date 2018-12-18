package com.demo.jsondiff;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;

public class JsonDiffMainClass {

	public static void main(String[] args) throws JsonProcessingException, IOException {

		System.out.println(System.currentTimeMillis());

		ObjectMapper mapper = new ObjectMapper();
		
		//File jsonInputFile = new File("C:/Users/vkum168/Desktop/wiphierarchy_old.json");
		
		File jsonInputFile = new File("C:/Users/vkum168/Desktop/old.json");
		
		File jsonoutputFile = new File("C:/Users/vkum168/Desktop/new.json");

		//File jsonoutputFile = new File("C:/Users/vkum168/Desktop/wiphierarchy_new.json");

		JsonNode source = mapper.readTree(jsonInputFile);

		JsonNode target = mapper.readTree(jsonoutputFile);	

		JsonNode patch = JsonDiff.asJson(source, target);

		System.out.println(patch);
		
	}

}
