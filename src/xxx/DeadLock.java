package xxx;

import java.util.concurrent.TimeUnit;

class ThreadDome implements Runnable{

    private String lockA;
    private String lockB;

    public ThreadDome(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 尝试获得"+lockB);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 尝试获得" +lockA);
            }
        }
    }
}

/**
 * @author: hh
 * @create: 2019/4/30 14:40
 **/

public class DeadLock {


    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new ThreadDome(lockA,lockB),"AAA").start();
        new Thread(new ThreadDome(lockB,lockA),"BBB").start();
    }
}
