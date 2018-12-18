package com.pearson.cd.content.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pearson.cd.utils.GenerateChecksum;

/*
 * This class is used to model the parent child relationship
 * It is typically used to house content entities and maintain their
 * hierarchy and as such the class contains references to itself
 * since this will be used to model content entities there are 2
 * identifiers for the entity, but keeping the class generic allows
 * its reuse for other relationships
 * REMEMBER - the relationship is valid only in the context its being used in
 * It is important to have a wrapper to "contain" this relationship - something
 * like a specific version of a project/chapter/module/section... etc.
 */

public class Container implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private String id1 = ""; //can be content or version urn
	private String id2= ""; //can be content or version urn
	private String type = ""; //type of entity describing this element
	private int level = 0; //depth of the current element - starts at zero for the root
	
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
	
	public Container(String id1,String id2,String type,int level) {
		this.id1 = id1;
		this.id2 = id2;
		this.type = type;
		this.level = level;
	}

	@Override
	public int hashCode() {
		if ( hasCode == 0 ) {
			GenerateChecksum checksum = new GenerateChecksum();
			hasCode = checksum.checkSum(this).intValue();// is this adequate?
		}
		return hasCode;
	}
}
