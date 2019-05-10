package xxx;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: hh
 * @create: 2019/4/29 11:42
 **/

public class SemaphoreDome {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 10 ; i++) {

            final int temp = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t停车1秒后离去");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
