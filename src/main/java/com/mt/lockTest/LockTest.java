package com.mt.lockTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Klaus on 2020/3/12.
 */
public class LockTest {
    public static void main(String[] args) {
//        phone phone = new phone();

//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                try {
//                    phone.sendMsm();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            },"T"+i).start();
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        phone phone = new phone();

        for (int i = 0; i <10; i++) {
            FutureTask futureTask = new FutureTask<Integer>(phone);
            executorService.execute(futureTask);
//            try {
//                System.out.println("------------get()会阻塞------------"+futureTask.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
        executorService.shutdown();
    }

}

class phone implements  Callable<Integer>{
    AtomicInteger a = new AtomicInteger();
//      int i = 0;
    public synchronized  void sendMsm() throws  Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendMsm");
        Thread.sleep(1000);
        callphone();
    }
    public void callphone() throws  Exception {
        System.out.println(Thread.currentThread().getName()+"\t invoke call");
    }

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();
        char c = name.charAt(name.length() - 1);
        System.out.println(c+"线程 \t 开始正在执行任务");
        a.incrementAndGet();
        int i = a.intValue();
//        i++;
        Thread.sleep(1000);
        System.out.println(c+"线程 \t 结果"+i);
        return i;
    }
}
