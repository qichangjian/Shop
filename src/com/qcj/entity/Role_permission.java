package com.qcj.entity;

public class Role_permission {
	private int rpid;
	private int rid;
	private int pid;
	public Role_permission() {
		super();
	}
	public Role_permission(int rpid, int rid, int pid) {
		super();
		this.rpid = rpid;
		this.rid = rid;
		this.pid = pid;
	}
	public int getRpid() {
		return rpid;
	}
	public void setRpid(int rpid) {
		this.rpid = rpid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "role_permission [rpid=" + rpid + ", rid=" + rid + ", pid=" + pid + "]";
	}
}
