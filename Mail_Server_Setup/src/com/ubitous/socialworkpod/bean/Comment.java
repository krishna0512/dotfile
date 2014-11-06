package com.ubitous.socialworkpod.bean;

import java.util.Arrays;

public class Comment {

	private String commentid;
	private String tileid;
	private String commenttext;
	private String createdby;
	private String[] attachment;
	private String replytoid;
	
	
	
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getTileid() {
		return tileid;
	}
	public void setTileid(String tileid) {
		this.tileid = tileid;
	}
	public String getCommenttext() {
		return commenttext;
	}
	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String[] getAttachment() {
		return attachment;
	}
	public void setAttachment(String[] attachment) {
		this.attachment = attachment;
	}
	public String getReplytoid() {
		return replytoid;
	}
	public void setReplytoid(String replytoid) {
		this.replytoid = replytoid;
	}
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", tileid=" + tileid
				+ ", commenttext=" + commenttext + ", createdby=" + createdby
				+ ", attachment=" + Arrays.toString(attachment)
				+ ", replytoid=" + replytoid + "]";
	}
	
	
	
	
}
