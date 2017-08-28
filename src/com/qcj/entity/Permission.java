package com.qcj.entity;

public class Permission {
	private int pid;
	private String pname;
	private String purl;
	private String pmessage;
	public Permission() {
		super();
	}
	public Permission(int pid, String pname, String purl, String pmessage) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.purl = purl;
		this.pmessage = pmessage;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getPmessage() {
		return pmessage;
	}
	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}
	@Override
	public String toString() {
		return "permission [pid=" + pid + ", pname=" + pname + ", purl=" + purl + ", pmessage=" + pmessage + "]";
	}
}
