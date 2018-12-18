package com.aws.kinesis;

public class ModelData {
String text = "Test Data";

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public ModelData(String text) {
	super();
	this.text = text;
}

@Override
public String toString() {
	return "ModelData [text=" + text + "]";
}

}
