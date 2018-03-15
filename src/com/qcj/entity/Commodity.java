package com.qcj.entity;

public class Commodity {
	private int commodityId;
	private int gategoriesId;
	private String commodityName;
	private float commodityPrice;
	private String commodityIsMan;
	private String commodityIsShelves;
	private String commoditySize;
	
	
	public Commodity() {
		super();
	}


	public Commodity(int commodityId, int gategoriesId, String commodityName, float commodityPrice,
			String commodityIsMan, String commodityIsShelves, String commoditySize) {
		super();
		this.commodityId = commodityId;
		this.gategoriesId = gategoriesId;
		this.commodityName = commodityName;
		this.commodityPrice = commodityPrice;
		this.commodityIsMan = commodityIsMan;
		this.commodityIsShelves = commodityIsShelves;
		this.commoditySize = commoditySize;
	}


	public int getCommodityId() {
		return commodityId;
	}


	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}


	public int getGategoriesId() {
		return gategoriesId;
	}


	public void setGategoriesId(int gategoriesId) {
		this.gategoriesId = gategoriesId;
	}


	public String getCommodityName() {
		return commodityName;
	}


	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}


	public float getCommodityPrice() {
		return commodityPrice;
	}


	public void setCommodityPrice(float commodityPrice) {
		this.commodityPrice = commodityPrice;
	}


	public String getCommodityIsMan() {
		return commodityIsMan;
	}


	public void setCommodityIsMan(String commodityIsMan) {
		this.commodityIsMan = commodityIsMan;
	}


	public String getCommodityIsShelves() {
		return commodityIsShelves;
	}


	public void setCommodityIsShelves(String commodityIsShelves) {
		this.commodityIsShelves = commodityIsShelves;
	}


	public String getCommoditySize() {
		return commoditySize;
	}


	public void setCommoditySize(String commoditySize) {
		this.commoditySize = commoditySize;
	}


	@Override
	public String toString() {
		return "Commodity [commodityId=" + commodityId + ", gategoriesId=" + gategoriesId + ", commodityName="
				+ commodityName + ", commodityPrice=" + commodityPrice + ", commodityIsMan=" + commodityIsMan
				+ ", commodityIsShelves=" + commodityIsShelves + ", commoditySize=" + commoditySize + "]";
	}

	
}
