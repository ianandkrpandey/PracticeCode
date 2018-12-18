package com.json.compare;

import java.util.List;

public class Project {
	String id1;
	String id2;
	String type;
	int level =1;
	List<Chapters> chapters;
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
	public List<Chapters> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapters> chapters) {
		this.chapters = chapters;
	}
	

}
