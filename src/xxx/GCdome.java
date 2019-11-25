package xxx;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: hh
 * @create: 2019/4/26 11:15
 **/

public class GCdome {

    private static final int _1m= 1024 * 1024;



    public static void testAllocation(){
        byte[] allocationl,allocation2,allocation3,allocation4;
        allocationl=new byte[2*_1m];
        allocation2=new byte[2*_1m];
        allocation3=new byte[2*_1m];
        allocation4=new byte[4*_1m];

}

    public static void main(String[] args) {
        long round = Math.round(-11.6); //正数四舍五入；负数四平五降
        System.out.println(round);

    }
}
