package com.mt.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author itw_matian
 * @Desc 自定义线程池
 */
public class ThreadPool {
	
	private int corePoolSize = 8;
	
	private int maxPoolSize = 20;
	
	private int queueCapacity = 20;
	
	private int keepAliveSeconds = 2;
	
	
	public ThreadPoolExecutor getTaskThreadPool(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				corePoolSize, 
				maxPoolSize, 
				keepAliveSeconds, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(queueCapacity),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

}
