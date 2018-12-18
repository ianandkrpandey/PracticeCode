package com.jackson.conversion;

import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

public class MyCustomExceptionTest {
	static String payload = "{ \"detail\": \"Not Found\", \"status\": 1234 }";
public static void main(String[] args) throws IOException {
	boolean bb= IsJsonPathexistsForString(payload, "status");
	System.out.println(bb);
	Integer status =  (Integer) searchForPatternObject(payload, "$.status");
	if(status == 404) {
		try {
			throw new MyException(status);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	System.out.println(status);
	
	
}

public static Object searchForPatternObject(String response, String pathString) throws IOException {
	Object str = null;
	try {
		str = JsonPath.read(response, pathString);
	} catch (Exception e) {
		e.printStackTrace(); // need a more descriptive error message here
	}
	return str;
}
public static boolean IsJsonPathexistsForString(String response, String pathString) {
	String str = null;
	try {
		str = JsonPath.read(response, pathString);
		if (str instanceof String) {
			return !str.isEmpty();
		}
	} catch (Exception e) {
		return false;
	}
	return false;
}
}
class MyException extends Exception{
	int status;
	MyException(int arg0){
		super();
		this.status = arg0;
	}
	public int getErrorCode() {
		System.out.println("----"+status);
		return this.status;
		
	}
}
