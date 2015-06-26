package com.pier.exchange.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

public class ExecutorUtil {
	
	private Logger LOG = Logger.getLogger(ExecutorUtil.class);
	
	private static class InstanceHelper {
		private static final ExecutorUtil instance = new ExecutorUtil();
	}
	
	public static void execute(Runnable command){
		InstanceHelper.instance.threadPool.execute(command);
	}
	
	public static <T> Future<T> submit(Callable<T> command){
		return InstanceHelper.instance.threadPool.submit(command);
	}
	
	// ------- class definition --------------
	private ExecutorService threadPool;
	
	private ExecutorUtil(){
		this.threadPool =  Executors.newCachedThreadPool();
		
		// 绋嬪簭閫�鍑烘椂鍏抽棴绾跨▼姹�
		Runtime.getRuntime().addShutdownHook(new CleanHook());
	}
	
	private class CleanHook extends Thread {
		
		public void run(){
			try{
				threadPool.shutdown();
			}catch(Exception e){
				LOG.error("鍏抽棴绾跨▼姹犳椂鍙戠敓閿欒", e);
				threadPool.shutdownNow();
			}
		}
		
	}

}
