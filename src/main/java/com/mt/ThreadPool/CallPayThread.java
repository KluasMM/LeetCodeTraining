package com.mt.ThreadPool;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallPayThread implements Callable<String>{
	
	private int pageIndex;
	
	private List<String> taskList;
	
	CountDownLatch c;
	
	
	public CallPayThread(int pageIndex, List<String> taskList,CountDownLatch countDownLatch) {
		super();
		this.pageIndex = pageIndex;
		this.taskList = taskList;
		this.c=countDownLatch;
	}

	@Override
	public String call() throws Exception { 
		
		String result = null;
		if(null!=taskList&&taskList.size()>0){
			try {
				System.out.println(Thread.currentThread().getName()+"线程，开始工作--------------------");
				StringBuilder builder = new StringBuilder();
				for (String task:taskList) {
					TestData testData = new TestData();
					testData.setDataName(task);
					builder.append(task);
//					Thread.sleep(1);//模拟处理工作
				}
				//阻断测试（3线程异常）
				if(pageIndex==500001){
					int a =1/0;
				}
				result = builder.toString();
//				System.out.println(Thread.currentThread().getName()+"线程，工作结果："+result);
				System.out.println(Thread.currentThread().getName()+"线程，工作结束");
//				c.countDown();//要写在finally里，异常也能减一
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName()+"线程，工作异常");
				return "0";
			}finally{
				c.countDown();
			}
		}
//		return result; 
		return "1";//返回result数据量太大   返回1 代表成功
	}

}
