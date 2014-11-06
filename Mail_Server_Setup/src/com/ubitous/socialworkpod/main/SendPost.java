package com.ubitous.socialworkpod.main;

import java.io.StringWriter;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.json.simple.*;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.ubitous.socialworkpod.mail.template.util.GenerateHTMLContent;

public class SendPost {

	public void sendPost(String emailTitle,String emailBody) {
		System.out.println ("Creating the JSON..");
		JSONObject attr = new JSONObject();
 		JSONObject rcpt = new JSONObject();
		JSONObject obj = new JSONObject();
		System.out.println ("Entered The Function SendPost..");
		attr.put("attributes",null);
		attr.put("attributeMaps",null);
	
		rcpt.put("users", null);
		rcpt.put("dls", null);
	
		obj.put("id", null);
		obj.put("title", emailTitle);
		obj.put("teaser", emailBody);
		obj.put("tiletype", "post");
		obj.put("createdby", "7xJZmCTOjd");
		obj.put("attachment", null);
		obj.put("data", attr.toJSONString() );
		obj.put("createddate", "2014-10-13 11:17:15.0");
		obj.put("activityLinked", "Y");
		obj.put("activityAssigned", "Y");
		obj.put("activityid", "5BB2oCQwYk");
    	obj.put("recipients", rcpt.toJSONString());
    	obj.put("tags", null);
    
    	String url = "http://app.tcollab.in:9091/serv/createtile";
    	System.out.println ("Trying to connect the web..");
    	HttpClient httpClient = new DefaultHttpClient();

    	try {
    		HttpPost request = new HttpPost(url);
    		StringEntity params = new StringEntity("jsondata=[" + obj.toJSONString() +"]");
    		request.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        	request.setEntity(params);
        	System.out.println ("Trying to execute the request..");
        	HttpResponse response = httpClient.execute(request);
        	System.out.println ("Request Executed..");
        	
        	System.out.println(obj.toJSONString());

        	Header[] headers = response.getAllHeaders();
        	for (Header header : headers) {
        		System.out.println("Key : " + header.getName() 
        		      + " ,Value : " + header.getValue());
        	}
        	System.out.println (EntityUtils.toString(response.getEntity()));
        	System.out.println("Done!");
    	}
		catch (Exception ex) {
			ex.printStackTrace();
    	}
		finally {
        	httpClient.getConnectionManager().shutdown();
    	}
	}
}
