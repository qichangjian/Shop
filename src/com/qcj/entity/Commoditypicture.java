package com.qcj.entity;

public class Commoditypicture {
	private int cpId;
    private int commodityId;
    private String cpURL;
    private String cpPosition;
    
	public Commoditypicture() {
		super();
	}
    
	public Commoditypicture(int cpId, int commodityId, String cpURL, String cpPosition) {
		super();
		this.cpId = cpId;
		this.commodityId = commodityId;
		this.cpURL = cpURL;
		this.cpPosition = cpPosition;
	}

	public int getCpId() {
		return cpId;
	}

	public void setCpId(int cpId) {
		this.cpId = cpId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCpURL() {
		return cpURL;
	}

	public void setCpURL(String cpURL) {
		this.cpURL = cpURL;
	}

	public String getCpPosition() {
		return cpPosition;
	}

	public void setCpPosition(String cpPosition) {
		this.cpPosition = cpPosition;
	}

	@Override
	public String toString() {
		return "Commoditypicture [cpId=" + cpId + ", commodityId=" + commodityId + ", cpURL=" + cpURL
				+ ", cpPosition=" + cpPosition + "]";
	}

    
    
}
