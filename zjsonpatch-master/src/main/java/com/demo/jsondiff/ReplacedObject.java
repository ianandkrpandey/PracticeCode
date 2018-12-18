package com.demo.jsondiff;

public class ReplacedObject {
	private String location;
	private String value;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ReplacedObject [location=" + location + ", value=" + value + "]";
	}
	
	
}
