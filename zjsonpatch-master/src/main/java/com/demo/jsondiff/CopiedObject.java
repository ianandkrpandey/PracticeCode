package com.demo.jsondiff;

public class CopiedObject {
String from;
String path;
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
@Override
public String toString() {
	return "CopiedObject [from=" + from + ", path=" + path + "]";
}

}
