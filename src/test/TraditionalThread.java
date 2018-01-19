package test;

public class TraditionalThread {
    public static boolean sShouldmain = true;

    public static void main(String[] args) {
        TraditionalThread business = new TraditionalThread();


        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (TraditionalThread.class) {
                    for (int i = 0; i < 3; i++) {
                        business.sub(i);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    business.father(i);
                }
            }
        }).start();
    }


    public synchronized void sub(int i) {
        if (!sShouldmain) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 3; j++) {
            System.out.println("这是子线程的第" + i + "轮的第" + j + "个数字");
        }
        sShouldmain = false;
        this.notify();
    }

    public synchronized void father(int i) {
        if (sShouldmain) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 3; j++) {
            System.out.println("这是主线程的第" + i + "轮的第" + j + "个数字");
        }
        sShouldmain = true;
        this.notify();
    }

}

