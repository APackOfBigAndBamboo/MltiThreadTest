import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
    private static int count=0;
    public static void main(String[] args) {

        //第一次10秒后炸，然后每三秒炸一次
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("booming!");
//            }
//        }, 10000,3000);


//循环炸（未实现）
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("booming!");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("booming11");
//                    }
//                },2000);
//            }
//        }, 2000);




//循环花式炸
        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                count=(count+1)%2;
                System.out.println("booming!");
                new Timer().schedule(
//                   new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("booming11");
//                     }
//                  }
                        new MyTimerTask(), 2000+2000*count);
            }
        }
        new Timer().schedule(new MyTimerTask(), 2000);


        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

