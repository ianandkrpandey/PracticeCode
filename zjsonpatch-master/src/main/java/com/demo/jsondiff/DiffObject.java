package com.demo.jsondiff;

import java.util.List;

public class DiffObject {
	List<NewObject> newObject;
	List<RemovedObject> removedObject;
	List<ReplacedObject> replacedObject;
	List<CopiedObject> copyiedObject;

	public List<NewObject> getNewObject() {
		return newObject;

	}

	public void setNewObject(List<NewObject> newObject) {
		this.newObject = newObject;
	}

	public List<RemovedObject> getRemovedObject() {
		return removedObject;
	}

	public void setRemovedObject(List<RemovedObject> removedObject) {
		this.removedObject = removedObject;
	}

	public List<ReplacedObject> getReplacedObject() {
		return replacedObject;
	}

	public void setReplacedObject(List<ReplacedObject> replacedObject) {
		this.replacedObject = replacedObject;
	}

	public List<CopiedObject> getCopyiedObject() {
		return copyiedObject;
	}

	public void setCopyiedObject(List<CopiedObject> copyiedObject) {
		this.copyiedObject = copyiedObject;
	}

	@Override
	public String toString() {
		/*return "DiffObject [newObject=" + newObject + ", removedObject=" + removedObject + ", replacedObject="
				+ replacedObject + "]";*/
		return "New Object =\n {" +newObject+"}\n"
				+"Removed Obbject =\n"+ removedObject+"\n"
				+"Replaced Object =\n "+replacedObject+""
				+"Copied Object = \n"+copyiedObject+"";
	}

}
