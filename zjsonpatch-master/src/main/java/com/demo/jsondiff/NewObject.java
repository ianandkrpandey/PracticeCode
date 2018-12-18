package com.demo.jsondiff;

public class NewObject {

	String id1;
	String id2;
	String type;
	int level;
	String location;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "[id1=" + id1 + ", id2=" + id2 + ", type=" + type + ", level=" + level + ", location="
				+ location + "]";
	}
	
	
}
