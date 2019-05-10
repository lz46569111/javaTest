package xxx;

import org.junit.Test;


import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;


/**
 * @author: hh
 * @create: 2019/4/26 15:40
 **/

public class timeOutDome {

    private Product product = new Product("iPhone");

    @Test
    public void strongRef(){
        Map<Product,Integer> map = new HashMap<>();
        map.put(product,111);
        product=null;
        System.gc();
        map.keySet().forEach(System.out::println);
    }

    @Test
    public void weakRef_1(){
        ReferenceQueue<Product> rq = new ReferenceQueue<Product>();
        WeakReference<Product> wr= new WeakReference<Product>(product,rq);
        Map<WeakReference<Product>,Integer> map = new HashMap<>();
        System.out.println("wr是否已被添加至引用队列:" + wr.isEnqueued());
        map.put(wr,111);
        product = null;
        System.gc();
        map.keySet().forEach(e -> System.out.println(e.get()));
        System.out.println("wr是否已被添加至引用队列:" + wr.isEnqueued());
    }


    @Test
    public void weakRef_2(){
        WeakHashMap<Product,Integer> map = new WeakHashMap<>();
        map.put(product,111);
        product=null;
        System.gc();
        map.keySet().forEach(System.out::println);
    }


    @Test
    public void weakRef_3(){
        Integer[] i = {1,2,3};
        List<Integer>list= Arrays.asList(i);
        ReferenceQueue<List> rq = new ReferenceQueue<>();
        WeakReference<List> wr = new WeakReference<List>(list,rq);
        Map<WeakReference<List>,Integer> map = new HashMap<>();
        System.out.println("wr是否已被添加至引用队列:" + wr.isEnqueued());
        map.put(wr,111);
        list = null;
        System.gc();
        map.keySet().forEach(e -> System.out.println(e.get()));
        System.out.println("wr是否已被添加至引用队列:" + wr.isEnqueued());
    }

    @Test
    public void rigth(){
        int i = 8;
        int j = i >> 3;
        int maxValue = Integer.MAX_VALUE-8;
        System.out.println(maxValue);
        System.out.println(j);
    }


    @Test
    public void ListDome(){
        List<Integer> list = new ArrayList<>(20);
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(System.out::println);
        TreeMap map = new TreeMap();
    }

    @Test
    public void StringTest(){

        String str = "c";
        String str_1="ab" + str;
        //System.out.println(str_1.hashCode());
        String str_2="abc";
        //System.out.println(str_2.hashCode());
        boolean b = str_1.equals(str_2);
        System.out.println(b);

    }


    @Test
    public void SumDome(){
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        },"AAA").start();
    }
}


