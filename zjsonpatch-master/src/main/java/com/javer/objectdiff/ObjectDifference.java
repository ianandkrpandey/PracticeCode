package com.javer.objectdiff;

import java.util.List;

/**
 * This class is the final wrapper of all the type of changes in comparison of
 * two objects.
 * 
 * @author VGup899
 *
 */
public class ObjectDifference {

	List<AddedObject> newObjects;
	List<ChangedValue> valueChanges;
	List<RemovedObject> removedObjects;

	public List<AddedObject> getNewObjects() {
		return newObjects;
	}

	public void setNewObjects(List<AddedObject> newObjects) {
		this.newObjects = newObjects;
	}

	public List<ChangedValue> getValueChanges() {
		return valueChanges;
	}

	public void setValueChanges(List<ChangedValue> valueChanges) {
		this.valueChanges = valueChanges;
	}

	public List<RemovedObject> getRemovedObjects() {
		return removedObjects;
	}

	public void setRemovedObjects(List<RemovedObject> removedObjects) {
		this.removedObjects = removedObjects;
	}

}
