package com.ubitous.socialworkpod.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.ubitous.socialworkpod.bean.Comment;
import com.ubitous.socialworkpod.bean.User;

public class CommentService implements ICommentService{

	@Override
	public String uploadAttachment(String fileName) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer stringBuffer=null;

        try {
        	
        	HttpPost httppost = new HttpPost("http://192.168.0.3:8080/attach");
        	System.out.println(new File(fileName).getName());
        	 FileBody bin = new FileBody(new File(fileName),
             		ContentType.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document"), 
             		new File(fileName).getName());

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();   
                      
           //  example for setting a HttpMultipartMode 
            builder.setMode(HttpMultipartMode.STRICT);

           //  example for adding an image part             
            builder.addPart("file",bin);
            
            httppost.setEntity(builder.build());
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            System.out.println("After execute ");
            try {     
                
                	InputStream is = response.getEntity().getContent();
                	if(null!=is)
                	 stringBuffer = new StringBuffer();
                	int ch = is.read();
                	while(ch!=-1)
                	{
                		System.out.print((char)ch);
                		stringBuffer.append((char)ch);
                		try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {							
							e.printStackTrace();
							}                		
                		ch = is.read();                		
                	}     	
                	System.out.println("Data "+stringBuffer);
                
                
            } 
            catch(IOException e)
            {
            	e.printStackTrace();
            }
            finally {
            	try{
                response.close();
            	}
            	catch(IOException e)
            	{
            		e.printStackTrace();
            	}
            }
        } catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
        	try
        	{
            httpclient.close();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
        }
        
		return stringBuffer.toString().substring(1, stringBuffer.toString().length()-1);
	}

	@Override
	public void addCoomment(Comment comment) {
		
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://192.168.0.3:8080/createcomment");
			post.setHeader("Referer", "http://192.168.0.3:8080/createcomment");
			post.setHeader("Authorization", "Basic (with a username and password)");
			post.setHeader("Content-type", "application/x-www-form-urlencoded");

			// if you need any parameters
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();		
			
			Gson gson=new Gson();		
			String json=gson.toJson(comment);
			System.out.println("------------IN ADD COMMENT ----------------");
			System.out.println(json);
			urlParameters.add(new BasicNameValuePair("jsondata","["+ json+"]"));
			try {
				post.setEntity(new UrlEncodedFormEntity(urlParameters));
				HttpResponse response = client.execute(post);			
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = rd.readLine();
				while (line   != null) {
					System.out.println(line+"------");
				    result.append(line);
				    line = rd.readLine();
				}			
				System.out.println(result);
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}				
	}
	

public String getUserId(String emailAddress)
{
	
	 CloseableHttpClient httpclient = HttpClients.createDefault();
	 User user=null;
     try {
         HttpGet httpget = new HttpGet("http://192.168.0.3:8080/userByEmail/"+emailAddress);

         System.out.println("Executing request " + httpget.getURI());
         CloseableHttpResponse response = httpclient.execute(httpget);
         try {
             System.out.println("----------------------------------------");
             System.out.println(response.getStatusLine());
             // Do not feel like reading the response body
             // Call abort on the request object
             
             
             //response.getEntity().writeTo(System.out);
             InputStream is = response.getEntity().getContent();
             StringBuffer stringBuffer = new StringBuffer();
             int ch = is.read();
         	while(ch!=-1)
         	{       	
         		stringBuffer.append((char)ch);       			
         		ch = is.read();                		
         	}     	
         	
         	
           //  System.out.println(stringBuffer.toString());
             
         	Gson gson = new Gson();
         	 user = gson.fromJson(stringBuffer.toString(), User.class);
         	 System.out.println(user);
         	System.out.println(user.getId());
             
             httpget.abort();
         } finally {
             response.close();
         }
     } catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
         try {
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	return user.getId();
}
	
}
