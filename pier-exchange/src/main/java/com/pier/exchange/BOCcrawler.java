package com.pier.exchange;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pier.exchange.util.Loginer;
import com.pier.exchange.util.RequestBuilder;
import com.pier.exchange.util.Type;

public class BOCcrawler extends Loginer{
	public Loginer login(Type t, String url) {
		RequestBuilder builder = new RequestBuilder(url,null,null);
		HttpPost post = builder.buildPost(t);
		try {
			httpClient = HttpClients.custom().build();
			CloseableHttpResponse redirect = httpClient.execute(post);
//			System.out.println("getStatusCode:"+redirect.getStatusLine().getStatusCode());
//			System.out.println(redirect.toString());
//			Header[] headers = redirect.getAllHeaders();
//			for(Header h:headers) {
//				System.out.println(h.getName()+": "+h.getValue());
//			}
			HttpEntity myEntity = redirect.getEntity();
	        webtext = EntityUtils.toString(myEntity,"UTF-8"); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public Loginer crawl() {
		Document doc = Jsoup.parse(webtext);
		Element element = doc.select("div[class^=BOC]").first();
		Elements elements = element.select("tr");
		//Elements keys = elements.get(0).select("th");
		Elements values = elements.get(1).select("td");
		//System.out.println("keys: "+keys.size()+"; values: "+values.size());
		//System.out.println("key: "+keys.get(1).text()+"; value: "+values.get(1).text());
		//System.out.println("key: "+keys.get(3).text()+"; value: "+values.get(3).text());
		
		map = new HashMap<String, String>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		map.put("Date", "'"+df.format(new Date())+"'");
		map.put("Buying_Rate", values.get(1).text());
		map.put("Selling_Rate", values.get(3).text());
		return this;
	}
	
	public static void main(String[] args) {
		String url = "http://srh.bankofchina.com/search/whpj/search.jsp";
		try {
			BOCcrawler l = new BOCcrawler();
			l.login(Type.BOC, url);
			l.crawl();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
