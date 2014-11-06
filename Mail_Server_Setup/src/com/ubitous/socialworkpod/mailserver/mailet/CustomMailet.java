package com.ubitous.socialworkpod.mailserver.mailet;

import java.io.*;
import java.util.Collection;
import java.util.*;
import java.util.StringTokenizer;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.apache.mailet.Mail;
import org.apache.mailet.base.GenericMailet;
import org.apache.james.mime4j.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

import com.ubitous.socialworkpod.mailserver.bean.Comment;
import com.ubitous.socialworkpod.mailserver.service.CommentService;
import com.ubitous.socialworkpod.main.SendPost;
import com.ubitous.socialworkpod.mailserver.util.MailServerUtility;

public class CustomMailet extends GenericMailet {
	
	/*
	 * Process each body part of a multipart mail.
	 * and prints the content if the type == text/plain
	 * and prints the name of the attachment if available.
	 * 
	 * gmail: done
	 * live: done
	 * yahoo: unverified.
	 */
	private String processBodyPart (BodyPart bp) throws MessagingException, IOException {
		if (bp.isMimeType("text/html"))
			return "";
		
		String ret="";
		String dp = bp.getDisposition();
		//System.out.println ("Disposition is equal to: " + dp);
		if (dp != null && (dp.equals(BodyPart.ATTACHMENT))) {
			// handle the attachment of the mail
			System.out.println ("This mail has some Attachments.");
			System.out.println ("The file-name is: " + bp.getDataHandler().getName());
		}
		else {
			// print the mail contents.
			String s = (String) bp.getContent();
			StringTokenizer st = new StringTokenizer (s, "\n");
			while (st.hasMoreTokens()) {
				String t = st.nextToken();
				if (t.charAt(0) == '>')
					continue;
				if (t.contains("write:") || (t.length() > 10 && t.substring(0,3).equals("On ") && ( t.contains("gmail") || t.contains("yahoo") )))
					continue;
				ret = ret + "\n" + t;
				//System.out.println (t);
			}
			ret.trim();
			//System.out.println (ret);
		}
		return ret;
	}
	public String getEmailTitle (MimeMessage mimeMessage) throws Exception {
		return mimeMessage.getSubject().trim();
	}	
	public String getEmailContent (MimeMessage mimeMessage) throws Exception {
		String emailBody="";
		if (mimeMessage.isMimeType("text/*")) {
			//System.out.println ("This is a plain text");
			emailBody = (String)(mimeMessage.getContent());
			//System.out.println ("The messeage is: " + emailBody);
		}
		else if (mimeMessage.isMimeType("multipart/*")) {
			//System.out.println ("This is multipart\n");
			
			Multipart mp = (Multipart) mimeMessage.getContent();
			
			//System.out.println ("Number of the multiparts = " + mp.getCount());
			
			for (int i=0; i < mp.getCount(); i++) {
				if (mp.getBodyPart(i).isMimeType("multipart/*")) {
					Multipart mpp = (Multipart)(mp.getBodyPart(i).getContent());
					for (int j=0; j < mpp.getCount(); j++)
						emailBody = emailBody + "" + (processBodyPart (mpp.getBodyPart(j)));
				}
				else {
					emailBody = emailBody + "" + (processBodyPart (mp.getBodyPart(i)));
				}
			}
		}
		return emailBody.trim();
	}
	public void service(Mail mail) throws MessagingException {

		// Collection<?> collection =
		// MailServerUtility.filterList(mail.getRecipients());
		System.out.println ("Mail recieved.. and queued for processing.");
		Collection<?> collection = mail.getRecipients();
		if (collection.size() > 0)
			
				// iterating over each recipient		
				for (Object object : collection) {
					
				System.out.println ("The recieptent currenly processing is: "+object.toString());
				if (true) 	{ // was before != -1

					// stores the list of the attachments of the mail
					ArrayList<String> attachmentsList = new ArrayList<String>();

					MimeMessage mimeMessage = mail.getMessage();
					try {
						String emailTitle = getEmailTitle(mimeMessage);
						String emailBody = getEmailContent(mimeMessage);
						System.out.println ("The subject of the message is: " + getEmailTitle(mimeMessage));
						System.out.println ("The Content of the message is: \n" + getEmailContent(mimeMessage));
						System.out.println ();
						if (getEmailTitle(mimeMessage).startsWith("Re:")) {
							System.out.println ("This is a COMMENT");
						}
						else {
							System.out.println ("This is a POST");
						}
						System.out.println ("trying posting to the website..");
						SendPost sp = new SendPost ();
						System.out.println ("Trying to call the function..");
						sp.sendPost(emailTitle,emailBody);
						System.out.println ("Posted..");
						System.out.println ("End of the mailet.");
					}
					catch (Exception e) {
					  	e.printStackTrace(); 
					}
				}
			}
	}

}
