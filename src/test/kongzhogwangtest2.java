package test;


import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class kongzhogwangtest2 {

    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<>();
        final Semaphore semaphore = new Semaphore(1);
        //        Lock lock = new ReentrantLock();
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

//                    lock.lock();
                    String input = null;
                    try {
                        semaphore.acquire();
                        input = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String output = TestDo.doSome(input);
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                    semaphore.release();
// lock.unlock();
                }

            }).start();
        }
        for (int i = 0; i < 10; i++) {//不能改动
            String input = i + " ";//不能改动
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TestDo {
    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }


}
