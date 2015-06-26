package com.pier.exchange.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: SendEmailUtil
 * @author Caliph.Chen clp_job@163.com
 */

public class MailUtil {
	private static final Logger LOG = Logger.getLogger(MailUtil.class);
	private static String HOST_NAME, USER_NAME, PASSWORD;
	
	static {
		HOST_NAME = ConfigUtil.getString(ConfigConst.EMAIL_HOST_NAME, null);
		USER_NAME = ConfigUtil.getString(ConfigConst.EMAIL_SENDER_NAME, null);
		PASSWORD = ConfigUtil.getString(ConfigConst.EMAIL_SENDER_PASSWORD, null);
		//System.out.println(HOST_NAME);
		//System.out.println(USER_NAME);
		//System.out.println(PASSWORD);
	}
	
	
	public static void sendMail(MailBean bean){
		sendMail(bean, null);
	}
	
	public static void sendMail(MailBean bean, Callback callback){
		ExecutorUtil.execute(new MailTask(bean, callback));
	}
	
	/**
	 * 
	 * @author Adore CHEN
	 * @version 1.0.0
	 * @param <Boolean>
	 */
	private static class MailTask implements Runnable {

		private MailBean bean;
		private Callback callback;
		
		public MailTask(MailBean bean, Callback callback){
			this.bean = bean;
			this.callback = callback;
		}
		
		@Override
		public void run() {
			try {
				// Create the email message
				HtmlEmail email = new HtmlEmail();
				email.setStartTLSEnabled(true);
				email.setHostName(HOST_NAME);
				email.setAuthentication(USER_NAME, PASSWORD);
				email.setFrom(USER_NAME);
				
				email.addTo(bean.getToEmail());
				email.setSubject(bean.getSubject());
				email.setHtmlMsg(bean.getContent());

				// send the email
				String msgId = email.send();
				if(StringUtils.isNotEmpty(msgId)){
					System.out.println("msgId:"+msgId);
					//LOG.info("鍙戦�侀偖浠跺埌鐢ㄦ埛["+bean.getToEmail()+"]鎴愬姛锛� 杩斿洖鐨刴sgId["+msgId+"]");
					if(callback != null){
						callback.success();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				///LOG.error("鍙戦�侀偖浠跺彂鐢熷紓甯�, "+bean, e);
				if(callback != null){
					callback.error();
				}
			}
		}
		
	}
	
	private MailUtil(){
		
	}
	
	public static void main(String args[]){
		String toEmail = "eric7890@sina.com";
		String subject = "BOC job monitor";
		String content = "it works well for test";
		MailBean bean = new MailBean(toEmail, subject, content);
		
		new MailTask(bean, null).run();
		
		System.out.println("sent!");
	}
}
