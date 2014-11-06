package com.ubitous.socialworkpod.mailet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.apache.mailet.Mail;
import org.apache.mailet.base.GenericMailet;

import com.ubitous.socialworkpod.bean.Comment;
import com.ubitous.socialworkpod.service.CommentService;

public class CustomMailet extends GenericMailet {
	
	public void service(Mail mail) throws MessagingException {		
		
			
		
		
		Comment comment= new Comment();
		System.out.println("--------------------Object created for class Comment !!! ----------------");
		CommentService commentService = new CommentService();
		
		ArrayList<String> attachmentList = null;		
		Collection<?> collection = mail.getRecipients();
		System.out.println("------ Recipeients : "+collection);
		boolean bisectStatus=false;
		for(Object object:collection)
		{
			if(object.toString().indexOf("tcollab.in")!=-1)
				bisectStatus=true;
		}
		if(bisectStatus)
		for(Object object:collection)
		{
			String emailId = new StringTokenizer(object.toString(),"@").nextToken();
			StringTokenizer stringTokenizer = new StringTokenizer(emailId,"_");
			int countOfTokens = stringTokenizer.countTokens();
			String activityId = stringTokenizer.nextToken();
			String tileId=null;
			String commentId =null;
			if(countOfTokens == 2)
			{				
				tileId = stringTokenizer.nextToken();
				System.out.println("Token Count : "+countOfTokens);
				System.out.println("Tile Id : "+tileId);			
			}
			else 
			{
				tileId = stringTokenizer.nextToken();
				commentId = stringTokenizer.nextToken();
				System.out.println("Token Count : "+countOfTokens);
				System.out.println("Tile Id : "+tileId);
				System.out.println("Comment  Id : "+commentId);			
				
			}
			comment.setTileid(tileId);
			comment.setReplytoid(commentId);
			MimeMessage mimeMessage = mail.getMessage();			
			
			try {				
					
					
				Multipart multipart= (Multipart)mimeMessage.getContent();				
				if(multipart.getCount()!=0)
				{
					attachmentList=new ArrayList<String>();
				}
				String[] attachments=null;
				for(int i=0;i<multipart.getCount();i++)
				{
				
					
				/*	
					if(multipart.getBodyPart(i).getFileName()!=null)
					{
						FileOutputStream fos = new FileOutputStream(new File(multipart.getBodyPart(i).getFileName()));
						multipart.writeTo(fos);					
						System.out.println("------- Attachment file name -------"+multipart.getBodyPart(i).getFileName());
						//attachmentList.add(commentService.uploadAttachment(multipart.getBodyPart(i).getFileName()));
					}
				 */				
					
					if(multipart.getBodyPart(i).getFileName()!=null)
					{
						System.out.println("With file ");						
						System.out.println(multipart.getBodyPart(i).getContent());
						System.out.println();
					}
					else
					{
						System.out.println("Without file ");					
						
						 InputStream is =multipart.getBodyPart(i).getInputStream();
						 int ch = is.read();
						StringBuffer sb = new StringBuffer();						
						while(ch!=-1)
						{
							sb.append((char)ch);
							ch=is.read();			
							
						}
				//		System.out.println("String Buffer : "+sb);
						
						if(sb.toString().startsWith("-"))
						{
							int index = sb.toString().indexOf("Content-Type");
							System.out.println(index);
							int newLineIndex = sb.toString().substring(index).indexOf("\n");
							System.out.println(newLineIndex);
							int endIndex = sb.toString().indexOf("\n"+"On");
							System.out.println(endIndex);
							System.out.println(sb.toString().substring(index+newLineIndex, endIndex));
							if(comment.getCommenttext()==null)
							comment.setCommenttext(sb.toString().substring(index+newLineIndex, endIndex).trim().replaceAll("\n", "<br>").replaceAll("\r","<br>"));
						}
						else
						{
							int endIndex = sb.toString().indexOf("\n"+"On");
							System.out.println(sb.toString().substring(0, endIndex));
							if(comment.getCommenttext()==null)
								comment.setCommenttext(sb.toString().substring(0, endIndex).trim().replaceAll("\n", "<br>").replaceAll("\r","<br>"));							
						}
						
						System.out.println();						
					}
					System.out.println(new java.util.Date());
				}
				
				/*if(null!=attachmentList)
				{
					attachments = new String[attachmentList.size()];
					for(int i=0;i<attachmentList.size();i++){
						attachments[i]=attachmentList.get(i);
					System.out.println("Generated Attachment Id's : "+attachments[i]);
					int firstIndex =attachments[i].indexOf("/");
					int lastIndex =attachments[i].lastIndexOf("/");
					attachments[i]=attachments[i].substring(firstIndex, lastIndex);
					}
				}
				System.out.println(Arrays.toString(attachments));*/
				comment.setAttachment(attachments);
				comment.setCreatedby(commentService.getUserId(mail.getSender().toString()));
				commentService.addCoomment(comment);
				System.out.println("Before Exception Block ,,,,");
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
		}	

				
	}

}
