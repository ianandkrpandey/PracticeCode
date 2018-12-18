package com.pearson.kinesis.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Container implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private String id1 = ""; // can be content or version urn
	private String id2 = ""; // can be content or version urn
	private String type = ""; // type of entity describing this element
	private String subType = ""; // subtype of the type describing this element
	private int level = 0; // depth of the current element - starts at zero for
							// the root
	private String parentId = ""; // the parent that contains this - this refers
									// to id1 of the parent

	private final static ObjectMapper JSON = new ObjectMapper();
	static {
		JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private int hasCode = 0;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private List<Container> children = new ArrayList<Container>();

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

	public List<Container> getChildren() {
		return children;
	}

	public void setChildren(List<Container> children) {
		this.children = children;
	}

	public void addChild(Container child) {
		children.add(child);
	}

	public Container() {
		// TODO Auto-generated constructor stub
	}

	public byte[] toJsonAsBytes() {
		try {
			return JSON.writeValueAsBytes(this);
		} catch (IOException e) {
			return null;
		}
	}

	public static Container fromJsonAsBytes(byte[] bytes) {
		try {
			return JSON.readValue(bytes, Container.class);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Container [id1=" + id1 + ", id2=" + id2 + ", type=" + type + ", subType=" + subType + ", level=" + level
				+ ", parentId=" + parentId + ", hasCode=" + hasCode + ", children=" + children + "]";
	}

}
