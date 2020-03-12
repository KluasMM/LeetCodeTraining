package com.mt.lockTest;

/**
 * Created by Klaus on 2020/3/12.
 */
public class LockTest {
    public static void main(String[] args) {
        phone phone = new phone();


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    phone.sendMsm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"T"+i).start();
        }
    }

}

class phone{
    public synchronized  void sendMsm() throws  Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendMsm");
        Thread.sleep(1000);
        call();
    }
    public    void call() throws  Exception {
        System.out.println(Thread.currentThread().getName()+"\t invoke call");
    }
}
