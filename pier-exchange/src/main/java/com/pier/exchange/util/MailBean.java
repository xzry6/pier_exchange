package com.pier.exchange.util;

public class MailBean {

	private String toEmail;
	private String subject;
	private String content;
	
	public MailBean(String toEmail, String subject, String content){
		this.toEmail = toEmail;
		this.subject = subject;
		this.content = content;
	}

	public String getToEmail() {
		return toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("MailBean[")
			.append("toEmail:").append(toEmail)
			.append(", subject:").append(subject)
			.append(", content:").append(content)
			.append("]");
		return buffer.toString();
	}
}
