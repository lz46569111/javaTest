package xxx;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: hh
 * @create: 2019/4/29 10:04
 **/

class MyCache {

    //模拟redis缓存
    private volatile Map<String, Object> map = new HashMap();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
                    //try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                    map.put(key, value);
                    System.out.println(Thread.currentThread().getName() + "\t 写入完成");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rwlock.writeLock().unlock();
                }
    }

    public void get(String key) {
        rwlock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t 正在读取");
                    //try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                    Object value = map.get(key);
                    System.out.println(Thread.currentThread().getName() + "\t 读取完成" + value);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rwlock.readLock().unlock();
                }
    }
}

public class readwriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
                    },String.valueOf(i)).start();

        }

        for (int i = 0; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
