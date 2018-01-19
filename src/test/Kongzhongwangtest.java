package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Kongzhongwangtest {

    private static BlockingQueue<String> queue=new ArrayBlockingQueue(4);
    public static void main(String[] args){
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        //模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完毕；
        //修改程序代码。开4个线程让这16个对象在4秒钟打完
for (int i=0;i<4;i++){
  new Thread(new Runnable() {
        @Override
        public void run() {
           while (true){

               try {
                   String qq = queue.take();
                   parseLog(qq);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        }
    }).start();
}
        for(int i=0;i<16;i++){//不能改动
            final String log=""+(i+1);//不能改动
            {
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void parseLog(String log){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(log+":"+(System.currentTimeMillis()/1000));
    }
}
