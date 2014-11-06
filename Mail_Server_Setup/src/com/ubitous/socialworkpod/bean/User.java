package com.ubitous.socialworkpod.bean;

public class User {
	private String id;
	private String email;
	private String fname;
	private String lname;
	private String avatar;
	private String orgid;
	private String orgname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", fname=" + fname
				+ ", lname=" + lname + ", avatar=" + avatar + ", orgid="
				+ orgid + ", orgname=" + orgname + "]";
	}
	
	

}
