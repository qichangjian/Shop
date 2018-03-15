package com.qcj.entity;

public class Gategories {
	private int gategoriesId;
	private String gategoriesName;
	private String gategoriesMessage;
	
	
	public Gategories() {
		super();
	}

	public Gategories(int gategoriesId, String gategoriesName, String gategoriesMessage) {
		super();
		this.gategoriesId = gategoriesId;
		this.gategoriesName = gategoriesName;
		this.gategoriesMessage = gategoriesMessage;
	}

	public int getGategoriesId() {
		return gategoriesId;
	}

	public void setGategoriesId(int gategoriesId) {
		this.gategoriesId = gategoriesId;
	}

	public String getGategoriesName() {
		return gategoriesName;
	}

	public void setGategoriesName(String gategoriesName) {
		this.gategoriesName = gategoriesName;
	}

	public String getGategoriesMessage() {
		return gategoriesMessage;
	}

	public void setGategoriesMessage(String gategoriesMessage) {
		this.gategoriesMessage = gategoriesMessage;
	}

	@Override
	public String toString() {
		return "Gategories [gategoriesId=" + gategoriesId + ", gategoriesName=" + gategoriesName
				+ ", gategoriesMessage=" + gategoriesMessage + "]";
	}
	
	
}
