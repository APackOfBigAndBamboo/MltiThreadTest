public class TraditionalThreadSynchronized {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    private void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output11("zhangxiaoxiang");
                }

            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outputer.output12("lihuoming");
                }

            }
        }).start();

    }

    static class Outputer{
        //使用类的字节码作为锁对象
        public void output11(String name){
            int len = name.length();
            synchronized (Outputer.class)
            {
                for(int i=0;i<len;i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }
        public static synchronized void output12(String name){
            int len = name.length();
            for(int i=0;i<len;i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        //使用方法作为锁对象
//        public void output21(String name){
//            int len = name.length();
//            synchronized (this)//使用类的字节码作为锁对象
//            {
//                for(int i=0;i<len;i++){
//                    System.out.print(name.charAt(i));
//                }
//                System.out.println();
//            }
//        }
//
//        public synchronized void output22(String name){
//            int len = name.length();
//            for(int i=0;i<len;i++){
//                System.out.print(name.charAt(i));
//            }
//            System.out.println();
//        }


    }
}