package com.javer.objectdiff;

/**
 * This class captured any addition in two objects comparison and returns the
 * location where the change has been made in the Object tree along with the
 * added object.
 * 
 * @author VGup899
 *
 */
public class AddedObject {

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
	@Override
	public String toString() {
		return "ObjectAdded [object=" + object + ", location=" + location + "]";
	}
}
