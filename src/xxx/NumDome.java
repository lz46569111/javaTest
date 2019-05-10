package xxx;



import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: hh
 * @create: 2019/4/27 11:25
 **/

public class NumDome {

    Map map = new HashMap();

    Lock lock = new ReentrantLock();

    BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

     int a;
    boolean b = false;

    AtomicInteger integer = new AtomicInteger(5);

    public  void method(){
        synchronized (lock){}
            integer.compareAndSet(5,10);

        a = 1;
        this.b = true;
    }


    public  void method1() {
        if(b == true){
            a = a + 5;
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread);
    }
}
