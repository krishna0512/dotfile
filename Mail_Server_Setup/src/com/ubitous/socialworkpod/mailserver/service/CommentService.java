package com.ubitous.socialworkpod.mailserver.service;

import java.lang.reflect.Type;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
import com.google.gson.reflect.TypeToken;
import com.ubitous.socialworkpod.mailserver.bean.Comment;
import com.ubitous.socialworkpod.mailserver.bean.User;
import com.ubitous.socialworkpod.mailserver.util.MailServerUtility;

public class CommentService implements ICommentService {

	CloseableHttpClient client = null;
	Properties properties = null;
	CloseableHttpResponse response = null;

	public CommentService() {
		client = HttpClients.createDefault();
		properties = new Properties();
		try {
			properties
					.load(CommentService.class
							.getResourceAsStream("/com/ubitous/socialworkpod/mailserver/properties/mailserver.properties"));
			System.out.println("loaded file !!!!");
		} catch (IOException e) {
			System.out.println("In Comment Service Constructor ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addComment(Comment comment) {

		System.out.println("In Add Comment -------");

		String url = properties.getProperty("BASE_URL")
				+ properties.getProperty("CREATE_COMMENT_URL");
		System.out.println(url);
		HttpPost post = new HttpPost(url);
		post.setHeader("Referer", url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		String json = MailServerUtility.constructJsonFromObject(comment);
		
		System.out.println(json);
		urlParameters.add(new BasicNameValuePair("jsondata", "[" + json + "]"));
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			response = client.execute(post);
			System.out.println(MailServerUtility.convertStreamToString(response
					.getEntity().getContent()));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String getUserId(String emailAddress) {

		System.out.println("In getUserId------------------");
		User user = null;
		try {
			String url = properties.getProperty("BASE_URL")
					+ properties.getProperty("USER_BY_EMAIL") + emailAddress;
			System.out.println(url);
			HttpGet httpget = new HttpGet(url);
			response = client.execute(httpget);

			user = MailServerUtility.constructObjectFromJson(
					User.class,
					MailServerUtility.convertStreamToString(
							response.getEntity().getContent()).toString());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user.getId();
	}

	@Override
	public String uploadAttachment(String fileName) {

		StringBuffer stringBuffer = null;

		try {
			String url = properties.getProperty("BASE_URL")
					+ properties.getProperty("UPLOAD_ATTACHMENT");
			System.out.println(url);
			HttpPost httppost = new HttpPost(url);
			// System.out.println(new File(fileName).getName());
			FileBody bin = new FileBody(
					new File("/home/sadmin/Mail_POC/attachments/" + fileName),
					ContentType
							.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
					new File(fileName).getName());

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			// example for setting a HttpMultipartMode
			builder.setMode(HttpMultipartMode.STRICT);

			// example for adding an image part
			builder.addPart("file", bin);

			httppost.setEntity(builder.build());
			System.out
					.println("executing request " + httppost.getRequestLine());
			response = client.execute(httppost);
			stringBuffer = MailServerUtility.convertStreamToString(response
					.getEntity().getContent());

		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Type listType = new TypeToken<List<String>>() {}.getType();
		ArrayList<String> stringList = new Gson().fromJson(stringBuffer.toString(), listType);
		System.out.println(stringList.get(0));
		return stringList.get(0);
		//return stringBuffer.toString();
		//return stringBuffer.toString().substring(1, stringBuffer.toString().length()-1);
	}

}
