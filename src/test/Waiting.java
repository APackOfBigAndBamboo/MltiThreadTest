package test;

public class Waiting {
    public static void main(String[] args) {
//        new Thread(new Waiting1("jie")).start();
//        new Thread(new Notifier("yajie")).start();
        Waiting waiting = new Waiting();

        Waiting1 waiting1 = new Waiting1(waiting);
        Notifier notifier = new Notifier(waiting);

        Thread thread = new Thread(waiting1);
        Thread thread1 = new Thread(notifier);
        new Thread(new Runnable() {
            @Override
            public void run() {
                thread.start();
                thread1.start();
            }
        }).start();
    }
}

class Waiting1 implements Runnable { // 实现了Runnable接口的类

    private Object waitObj; // 将在此对象上调用wait()方法。

    public Waiting1(Object waitObj) { // 构造方法，传入一个对象的引用
        this.waitObj = waitObj;
    }

    public void run() { // 覆盖抽象方法run()

//        String name = Thread.currentThread().getName();
        // 在调用wait()方法之前，向控制台输出一段文字
        System.out.println("挂起线程：线程将进入挂起状态，等待被别的线程唤醒……");
        try {
            synchronized (waitObj) {
                waitObj.wait(); // 调用wait方法
            }
        } catch (InterruptedException e) { // 捕捉异常并输出错误消息
            System.out.println("对不起，程序运行出错，错误信息为：" + e.getMessage());
            return; // 出错后线程将不再向下执行
        }
        System.out.println("挂起线程:线程被唤醒了");
    }
    /*System.out.println(name+":线程被唤醒了");*/
}


class Notifier implements Runnable {

    private Object waitObj; // 将在此对象上调用notify()方法。

    public Notifier(Object notifyObj) { // 构造方法，传入一个对象的引用
        this.waitObj = notifyObj;
    }

    public void run() {// 覆盖抽象方法run()

//        String name = Thread.currentThread().getName();
        // 为了让程序运行结果明显，让负责唤醒的线程先挂起5秒钟
        /*System.out.println(name + "：将挂起5秒钟");*/
        System.out.println("唤醒线程：唤醒线程将挂起5秒钟");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("对不起，程序运行出错，错误信息为：" + e.getMessage());
            return;
        }
        // 在调用notify ()方法之前，向控制台输出一段文字
        System.out.println("唤醒线程：开始notify线程");
        synchronized (waitObj) {
            waitObj.notify(); // 调用notify()方法，唤醒因为调用wait()而挂起的线程
        }
        // 唤醒结束后，向控制台输出下一行文字。
        System.out.println("唤醒线程：notify线程结束");
    }
}



