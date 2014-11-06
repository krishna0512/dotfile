package com.ubitous.socialworkpod.mailserver.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

public class MailServerUtility {

	private static Gson gson = new Gson();

	public static <E> E constructObjectFromJson(Class<E> E, String json) {

		return gson.fromJson(json, E);
	}

	public static <E> String constructJsonFromObject(E e) {

		return gson.toJson(e);
	}

	public static StringBuffer convertStreamToString(InputStream inputStream) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				inputStream));
		StringBuffer result = new StringBuffer();

		try {
			int ch = rd.read();
			while (ch != -1) {

				result.append((char) ch);
				ch = rd.read();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static Collection<?> filterList(Collection<?> collection) {

		for (Object object : collection) {
			if (object.toString().indexOf("tcollab.in") == -1)
				collection.remove(object);
		}
		return collection;
	}

	public static String storeFile(InputStream inputStream, String fileName) {
		File file = null;
		FileOutputStream fos = null;
		try {
			file = new File("/home/sadmin/Mail_POC/attachments");
			if (file.isDirectory()) {
				String files[] = file.list();
				for (int i = 0; i < files.length; i++) {
					if (files[i].equals(fileName))
						fileName = "new_" + fileName;
				}
			}

			fos = new FileOutputStream(new File(
					"/home/sadmin/Mail_POC/attachments/" + fileName));
			byte b[] = new byte[1024];
			while (inputStream.read(b) > 0) {
				fos.write(b);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fos)
					fos.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return fileName;
	}
	
	public static String[] getAttachmentIds(ArrayList<String> attachmentsList )
	{
		String[] attachments = null;
		if(attachmentsList.size()>0)
		attachments = new String[attachmentsList.size()];
		for(int i=0;i<attachmentsList.size();i++)
		{
			attachments[i]=attachmentsList.get(i);
		}					
		return attachments;
	}

}
