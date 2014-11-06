package com.ubitous.socialworkpod.mail.template.util;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.google.gson.Gson;


public class GenerateHTMLContent {

	public String generateHTMLContentForPost(String jsonData)
	{
		Gson gson= new Gson();
		Tile tile =gson.fromJson(jsonData, Tile.class);
		String htmlContent=null;
		  try {
	        	VelocityEngine ve = new VelocityEngine();
				ve.init();				
				VelocityContext context = new VelocityContext();
		        context.put("tile", tile);
		        Template t = ve.getTemplate("post.vm");
		        
		        StringWriter writer = new StringWriter();

		        t.merge( context, writer );
		        
		        htmlContent=   writer.toString() ;
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return htmlContent;
	}
	
}
