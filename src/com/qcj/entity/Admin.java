package com.qcj.entity;

public class Admin {
	private int aid;
	private String aname;
	private String apassword;
	private String amessage;	
	
	public Admin() {
		super();
	}
	public Admin(int aid, String aname, String apassword, String amessage) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.apassword = apassword;
		this.amessage = amessage;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	public String getAmessage() {
		return amessage;
	}
	public void setAmessage(String amessage) {
		this.amessage = amessage;
	}
	@Override
	public String toString() {
		return "admin [aid=" + aid + ", aname=" + aname + ", apassword=" + apassword + ", amessage=" + amessage + "]";
	}
}
