package com.ubitous.socialworkpod.mailserver.service;

import com.ubitous.socialworkpod.mailserver.bean.Comment;

public interface ICommentService {	
	String uploadAttachment(String fileName);
	 void addComment(Comment comment);
	 String getUserId(String emailAddress);

}
