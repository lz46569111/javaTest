package xxx;

import java.sql.Time;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: hh
 * @create: 2019/4/29 19:15
 **/


//资源类
class Dome{

    private int num=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void set(){

        lock.lock();
                try {
                    while(num != 0){
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    num++;
                    System.out.println(Thread.currentThread().getName() + "\t" + num);
                    condition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
    }

    public void get(){

        lock.lock();
        try {
            while(num == 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者与消费者
 */
public class SandxDome {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Dome dome = new Dome();
        new Thread(()->{
            for (int i = 0; i < 1000000; i++) {
                dome.set();
            }
                },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 1000000; i++) {
                dome.get();
            }
        },"BB").start();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
