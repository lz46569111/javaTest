package xxx;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: hh
 * @create: 2019/4/29 19:46
 **/

class Consumer{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void A5(){
            lock.lock();
                    try {
                        while (num != 1){
                            c1.await();
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println("a");
                        }
                        num = 2;
                        c2.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
    }

    public void B10(){

        lock.lock();
        try {
            while (num != 2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("b");
            }
            num = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void C15(){
        lock.lock();
        try {
            while (num != 3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("c");
            }
            num = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class LockDome {

    //线程操作资源类

    public static void main(String[] args) {

        Consumer consumer = new Consumer();
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    consumer.A5();
                }
                    },"A").start();

            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    consumer.B10();
                }
            },"B").start();

            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    consumer.C15();
                }
                },"C").start();
            }
}
