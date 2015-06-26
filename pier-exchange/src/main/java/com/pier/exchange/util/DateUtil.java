package com.pier.exchange.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

public class DateUtil {

	private static final String[] SUPPORTED_FORMAT = new String[] {
			"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd",
			"HH:mm:ss", "MM/dd/yyyy" };

	private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	private static final ThreadLocal<DateFormat> compactFormatters = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	

	public static long parse(String value) throws ParseException {
		Date date = DateUtils.parseDate(value, SUPPORTED_FORMAT);
		return date.getTime();
	}

	public static Date parseDate(String value) throws ParseException {
		return DateUtils.parseDate(value, SUPPORTED_FORMAT);
	}

	public static String format(Date date) {
		return formatters.get().format(date);
	}
	
	public static String formatCompact(Date date) {
		return compactFormatters.get().format(date);
	}

	public static Date lastMonth(Date date) {
		return DateUtils.addMonths(new Date(), -1);
	}

	public static Date nextMonth(Date date) {
		return DateUtils.addMonths(new Date(), 30);
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 
	*@Title: getCurrentDate
	* @Description: 鑾峰彇褰撳墠鏃ユ湡锛屾椂锛屽垎锛岀鍏ㄩ儴涓�0
	* @return
	* @return Date 
	* @throws
	 */
	public static Date getCurrentDate()
	{
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 
	*@Title: getDate
	* @Description: 鏍规嵁杈撳叆鍊艰幏鍙栨棩鏈燂紝鏃讹紝鍒嗭紝绉掑叏閮ㄤ负0
	* @param value
	* @return
	* @return Date 
	* @throws
	 */
	public static Date getDate(String value)throws ParseException
	{
		Date date=parseDate(value);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static int monthsBetween(String start,String end) throws ParseException{
		 Calendar c = Calendar.getInstance();
	        c.setTime(parseDate(end));
	        int year1 = c.get(Calendar.YEAR);
	        int month1 = c.get(Calendar.MONTH);
	         
	        c.setTime(parseDate(start));
	        int year2 = c.get(Calendar.YEAR);
	        int month2 = c.get(Calendar.MONTH);
	         
	        int result;
	        if(year1 == year2) {
	            result = month1 - month2;
	        } else {
	            result = 12*(year1 - year2) + month1 - month2;
	        }
			return result;
	}
	
	public static int daysBetween(String s, String e) throws ParseException {
		Date start=parseDate(s), end=parseDate(e);
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(end);
		cReturnDate.setTime(start);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}
	
	public static int daysBetween(Date start, Date end) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(end);
		cReturnDate.setTime(start);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}
	
	public static Set<String> get24HoursOneDay(String date) throws ParseException{
		Set<String> times=new HashSet<String>(24);
		Date d=parseDate(date);
		java.util.Calendar c=java.util.Calendar.getInstance();
		c.setTime(d);
		for(int i=0;i<24;i++){
		    c.add(Calendar.HOUR,1);
		    times.add(String.valueOf(c.getTime().getTime()));
		}
		return times;
	}
	
	public static Set<String> getEachDay(String start,int duration) throws ParseException{
		Set<String> times=new HashSet<String>();
		Date d=parseDate(start);
		java.util.Calendar c=java.util.Calendar.getInstance();
		c.setTime(d);
		times.add(DateUtil.formatCompact(c.getTime()));
		for(int i=0;i<duration;i++){
		    c.add(Calendar.DATE,1);
		    times.add(DateUtil.formatCompact(c.getTime()));
		}
		return times;
	}
	
	public static Set<String> getEachMonth(String start,int duration) throws ParseException{
		Set<String> times=new HashSet<String>();
		Date d=parseDate(start);
		java.util.Calendar c=java.util.Calendar.getInstance();
		c.setTime(d);
		times.add(DateUtil.formatCompact(c.getTime()).substring(0,6));
		for(int i=0;i<duration;i++){
		    c.add(Calendar.MONTH,1);
		    times.add(DateUtil.formatCompact(c.getTime()).substring(0,6));
		}
		return times;
	}
	
	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}
	
	/**
	 * 杈撳叆鍘嬬缉鐨勬棩鏈熸牸寮忥紝yyyyMMddHHmmss,鑾峰彇鎸囧畾鍩熺殑鍊硷紱
	 * 
	 * @param compactDateStr
	 * @return
	 * @throws ParseException 
	 */
	public static String getField(String compactDateStr, int field) throws Exception{
		if(StringUtils.isEmpty(compactDateStr)){
			return null;
		}
		
		switch(field){
		case Calendar.YEAR:
			return compactDateStr.substring(0, 4);
		
		case Calendar.MONTH:
			return compactDateStr.substring(4, 6);
			
		case Calendar.DAY_OF_MONTH:
			return compactDateStr.substring(6, 8);
			
		case Calendar.HOUR:
		case Calendar.HOUR_OF_DAY:
			return compactDateStr.substring(8, 10);
			
		case Calendar.MINUTE:
			return compactDateStr.substring(10, 12);
			
		case Calendar.SECOND:
			return compactDateStr.substring(12);
		
		default:
			throw new Exception("Unsupported filed in Calendar "+field);
		}
	}
	
	private DateUtil() {

	}

	public static void main(String args[]) throws ParseException {
		Date start=parseDate("2014-01-05"), end=parseDate("2015-02-05");
		System.out.println(daysBetween(start,end));
		System.out.println(monthsBetween("2014-01-05","2015-01-05"));
				
		Set<String> list=get24HoursOneDay("2012-12-01");
		
		for(String s:list){
			System.out.println(s);
		}
		
		//test months
		System.out.println("======================================test months===============================");
		list=getEachMonth("2012-12-01",5);
		
		for(String s:list){
			System.out.println(s);
		}
				
		//test days
		System.out.println("======================================test day===============================");
		list=getEachDay("2012-12-01",5);
		
		for(String s:list){
			System.out.println(s);
		}
		
		//test get current date
		System.out.println(getCurrentDate());
		
		//test getDate
		System.out.println(getDate("2014-12-25 15:24:56"));
	}
}
