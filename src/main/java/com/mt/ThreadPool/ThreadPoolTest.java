package com.mt.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {
	
	public static void main(String[] args) {
		
		/************准备工作**************/
		//获取数据
		int num = 1000000;
		List<String> data = getData(num);
		int size = data.size();//总条数
		
		//接收各段返回结果
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		
		//数据集合切割份数（2倍cpu）
		int subSize = 2*Runtime.getRuntime().availableProcessors();
//		int subSize = 4*Runtime.getRuntime().availableProcessors();//加多线程测试
		//数据总数不足切割份数 取数据总数
		if(subSize>size){
			subSize=size;
		}
		/************准备结束**************/
		
		/************切割集合执行任务****************/
		long startTime = System.currentTimeMillis();//计时开始
		int start,end;
		ThreadPoolExecutor taskThreadPool = new ThreadPool().getTaskThreadPool();//创建线程池
		CountDownLatch countDownLatch = new CountDownLatch(subSize);//监控线程是否完成
		for(int i=0;i<subSize;i++){
			start = size/subSize*i;
			end = size/subSize *(i+1);
			if(i+1==subSize){//最后一段处理
				end = size;
			}
			//切割工作
			List<String> subList = data.subList(start, end);
			int pageIndex = start+1;
			Future<String> future = taskThreadPool.submit(new CallPayThread(pageIndex, subList,countDownLatch));
			futureList.add(future);
		}
		//判断线程是否全部执行  还是countdownlatch更好用
//		boolean allThreadIsDone = taskThreadPool.getTaskCount()==taskThreadPool.getCompletedTaskCount();
//		while(!allThreadIsDone){
//			allThreadIsDone = taskThreadPool.getTaskCount()==taskThreadPool.getCompletedTaskCount();
//		}
		try {
			countDownLatch.await();//等待所有线程结束
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		taskThreadPool.shutdown();//关闭线程池
		long endTime = System.currentTimeMillis();//计时结束
		System.out.println("所有任务结束，线程池关闭！执行时间："+(endTime-startTime));
		
		/************任务结束****************/
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("==============================================");
		
		//打印所有线程执行结果
		for (Future<String> future : futureList) {
			
				try {
					if(future!=null){
						String result = future.get();
						System.out.println(future.toString()+"线程"+result);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		}
		
		
	}
	
	//准备数据
	public static List<String> getData(int num){
		List<String> list = new ArrayList<String>();
		for(int i=0;i<num;i++){
			String data="data_"+i;
			list.add(data);
		}
		return list;
	}
}
