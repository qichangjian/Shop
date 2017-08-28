package com.qcj.entity;

public class Role {
	private int rid;
	private String rname;
	private String rmessage;
	public Role() {
		super();
	}
	public Role(int rid, String rname, String rmessage) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rmessage = rmessage;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRmessage() {
		return rmessage;
	}
	public void setRmessage(String rmessage) {
		this.rmessage = rmessage;
	}
	@Override
	public String toString() {
		return "role [rid=" + rid + ", rname=" + rname + ", rmessage=" + rmessage + "]";
	}
}
