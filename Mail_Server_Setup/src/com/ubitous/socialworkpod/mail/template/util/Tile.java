package com.ubitous.socialworkpod.mail.template.util;

public class Tile {

	private String author;
    private String avatar;
    private String tiletype;
    private TileData data;
    private String id;
    private String authorid;
    private String teaser;
    private String status;
    private String[] attachment;
    private String createddate;
    private String title;
    private String activityid;
    private Comment[] comments;
    private Recipient recipients;
    
    
    
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTiletype() {
		return tiletype;
	}
	public void setTiletype(String tiletype) {
		this.tiletype = tiletype;
	}

	
	public TileData getData() {
		return data;
	}
	public void setData(TileData data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getTeaser() {
		return teaser;
	}
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getAttachment() {
		return attachment;
	}
	public void setAttachment(String[] attachment) {
		this.attachment = attachment;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public Comment[] getComments() {
		return comments;
	}
	public void setComments(Comment[] comments) {
		this.comments = comments;
	}
	public Recipient getRecipients() {
		return recipients;
	}
	public void setRecipients(Recipient recipients) {
		this.recipients = recipients;
	}
    
    
}
