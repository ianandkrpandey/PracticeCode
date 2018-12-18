package com.javer.objectdiff;

/**
 * This class captured any changes in two object comparison and returns the
 * location where the change has been made in the Object tree along with the
 * changes in property.
 * 
 * @author VGup899
 *
 */
public class ChangedValue {

	String location;
	String propertyName;
	Object oldValue;
	Object newValue;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		return "ChangedValue [location=" + location + ", propertyName=" + propertyName + ", oldValue=" + oldValue
				+ ", newValue=" + newValue + "]";
	}
}
