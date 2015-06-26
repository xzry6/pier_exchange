package com.pier.exchange.util;

import org.apache.http.client.methods.HttpPost;

public class PostFactory extends Factory{
	
	private HttpPost request;
	
	@Override
	public PostFactory set(Type cookie, String url, String user, String pwd){
		switch(cookie) {
			case LinkedIn: {
				request = Parameters.setLinkedIn(url,user,pwd);
				break;
			}
			case BOC: {
				request = Parameters.setBOC(url);
				break;
			}
		}
		return this;
	}
	
	public HttpPost getPost() {
		return request;
	}

}
