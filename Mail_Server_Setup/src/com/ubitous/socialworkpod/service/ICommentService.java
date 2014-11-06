package com.ubitous.socialworkpod.service;

import com.ubitous.socialworkpod.bean.Comment;

public interface ICommentService {	
	 String uploadAttachment(String fileName);
	 void addCoomment(Comment comment);
	 String getUserId(String emailAddress);

}
