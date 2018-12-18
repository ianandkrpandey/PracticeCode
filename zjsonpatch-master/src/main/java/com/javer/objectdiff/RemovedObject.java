package com.javer.objectdiff;

/**
 * This class captured any deletion in two objects comparison and returns the
 * location where the deletion has been made in the Object tree along with the
 * deleted object.
 * 
 * @author VGup899
 *
 */
public class RemovedObject {

	Object object;
	String location;
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
