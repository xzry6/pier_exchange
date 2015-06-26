package com.pier.exchange.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Loginer {
	protected CloseableHttpClient httpClient;
	protected String webtext;
	protected Map<String, String> map;
	public Loginer login(Type t, String url, String user, String pwd) {
		RequestBuilder builder = new RequestBuilder(url,user,pwd);
		HttpPost post = builder.buildPost(t);
		try {
			httpClient = HttpClients.custom().build();
			CloseableHttpResponse redirect = httpClient.execute(post);
			//System.out.println("getStatusCode:"+response.getStatusLine().getStatusCode());
			String address = redirect.getHeaders("Location")[0].getValue();
			HttpRequestBase get = builder.buildGet(t, address);
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity myEntity = response.getEntity();
	        webtext = EntityUtils.toString(myEntity,"UTF-8"); 
			redirect.close();
	        response.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	public void printf(String filename) {  
        try {
	        PrintStream ps = new PrintStream(new File(filename));
	        ps.println(webtext);
	        ps.flush();
	        ps.close();
	        httpClient.close();
        } catch(IOException e) {
        	e.printStackTrace();
        } 
	}
	
	public Loginer crawl() {
		return this;
	}

	public String getText() {
		return webtext;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
}
