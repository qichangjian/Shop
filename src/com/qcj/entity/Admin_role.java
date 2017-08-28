package com.qcj.entity;

public class Admin_role {
	private int arid;
	private int aid;
	private int rid;
	public Admin_role() {
		super();
	}
	public Admin_role(int arid, int aid, int rid) {
		super();
		this.arid = arid;
		this.aid = aid;
		this.rid = rid;
	}
	public int getArid() {
		return arid;
	}
	public void setArid(int arid) {
		this.arid = arid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "admin_role [arid=" + arid + ", aid=" + aid + ", rid=" + rid + "]";
	}
}
