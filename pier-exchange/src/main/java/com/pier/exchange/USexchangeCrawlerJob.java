package com.pier.exchange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.pier.exchange.util.ConfigConst;
import com.pier.exchange.util.ConfigUtil;
import com.pier.exchange.util.MailBean;
import com.pier.exchange.util.MailUtil;
import com.pier.exchange.util.StoredProcedure;
import com.pier.exchange.util.Type;


public class USexchangeCrawlerJob {
	
	//private static long ONEHOUR = 60*60*1000;
	private static long ONEDAY = 24*60*60*1000;
	private static long TENMINUTES = 10*60*1000;
	
	private static String TIME_M ="10:00:00";// execute for month

	protected static Logger logger = Logger.getLogger(USexchangeCrawlerJob.class);

	private static ScheduledExecutorService executor = Executors
			.newScheduledThreadPool(2);

	private static String toEmail = "eric7890@sina.com";
	private static String subject = "US_DOLLAR_EXCHANGE monitor";

	
	
	static class BOCDashBoardDayTask implements Runnable {
		
		public void run() {
			String msg = new String();
			String url = "http://srh.bankofchina.com/search/whpj/search.jsp";
			int count = 1;
			DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			
			
			//have a loop of 3 times every day, break the loop if the insertion is succeed
			while(count<=3) {
				try {
					
					//crawler job
					BOCcrawler l = new BOCcrawler();
					System.out.println(count+" try: "+df.format(new Date()));
					l.login(Type.BOC, url);
					l.crawl();
					
					//stored procedure
					StoredProcedure sp = new StoredProcedure();
					sp.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
					sp.insertInstance("US_DOLLAR_EXCHANGE_RATE", l.getMap());
					sp.close();
					msg += "\r\n"+df.format(new Date())+"\r\n"+count+" try: succeed insert into database";
					break;
					
				} catch(Exception e){
					e.printStackTrace();
					logger.error(e);
					
					
					//capture errors and sleep
					msg += "\r\n"+df.format(new Date())+"\r\n"+count+" try: Exception " 
							+ e.getMessage()+", fail to crawl, sleep 10 mins";
					count++;
					try {
						System.out.println("sleep 10 mins");
						Thread.sleep(TENMINUTES);
					} catch(Exception ee) {}
					
				} 
			}
			System.out.println(msg);
				
			// send email
			MailBean bean = new MailBean(toEmail, subject, msg);
			MailUtil.sendMail(bean);
		}
	}
	
	
	
	public static void executeDayJob() {
		long initDelay = getTimeMillis(TIME_M) - System.currentTimeMillis();
		initDelay = initDelay > 0 ? initDelay : ONEDAY + initDelay;
		System.out.println(initDelay);
		executor.scheduleAtFixedRate(new BOCDashBoardDayTask(), initDelay,
				ONEDAY, TimeUnit.MILLISECONDS);
	}

	
	private static long getTimeMillis(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " "
					+ time);
			return curDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
