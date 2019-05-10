package suanfa;

/**
 * @author: hh
 * @create: 2019/5/7 8:41
 **/

import java.util.Arrays;
import java.util.Comparator;

/**
 * 直接插入排序
 */
public class charupaixu {

    public static void main(String[] args) {
        int[] a = {1,8,6,5,3,7,10};
        sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    /**
     * 直接插入排序
     * 相当于暴力法
     * @param a
     */
    public static void sort(int[] a){
        for(int i=0; i<a.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(a[j] < a[j-1]){
                    int temp = a[j];
                    a[j]=a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }


    /**
     * 希尔排序
     */
    public static void sort1(int[] a){
        int length = a.length;
        int h =1;
        while(h < length/3){
            h = 3*h+1;
        }
        for(;h>=1;h/=3){
            for(int i=0;i<a.length-h;i +=h){
                for(int j=i+h;j>0;j -= h){
                    if(a[j] < a[j-h]){
                        int temp = a[j];
                        a[j] = a[j-h];
                        a[j-h] = temp;
                    }
                }
            }
        }
    }

    /**
     * 简单选择排序
     */

    public static void sort2(int[] a){
        for(int i=0;i<a.length;i++){
            int min=i;
            for(int j =i+1;j<a.length;j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    /**
     * 堆排序
     */
    public static void sort3(int[] a) {

        for (int i = a.length - 1; i > 0; i--) {
            max_heapify(a, i);

            //堆顶元素(第一个元素)与Kn交换
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
        }
    }

    public static void max_heapify(int[] a, int n) {
        int child;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            //左子节点位置
            child = 2 * i + 1;
            //右子节点存在且大于左子节点，child变成右子节点
            if (child != n && a[child] < a[child + 1]) {
                child++;
            }
            //交换父节点与左右子节点中的最大值
            if (a[i] < a[child]) {
                int temp = a[i];
                a[i] = a[child];
                a[child] = temp;
            }
        }
    }

    /**
     * 冒泡排序
     * 每一次都少循环一次
     */
    public static void sort4(int[] a){
        for(int i=0; i<a.length -1;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static void sort5(int[] a, int low, int high) {
        //已经排完
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;

        //保存基准值
        int pivot = a[left];
        while (left < right) {
            //从后向前找到比基准小的元素
            while (left < right && a[right] >= pivot)
                right--;
            a[left] = a[right];
            //从前往后找到比基准大的元素
            while (left < right && a[left] <= pivot)
                left++;
            a[right] = a[left];
        }
        // 放置基准值，准备分治递归快排
        a[left] = pivot;
        sort5(a, low, left - 1);
        sort5(a, left + 1, high);
    }

    /**
     * 并归排序
     */
    public class Merge {

        //归并所需的辅助数组
        private  int[] aux;

        public  void sort(int[] a) {
            //一次性分配空间
            aux = new int[a.length];
            sort(a, 0, a.length - 1);
        }

        public  void sort(int[] a, int low, int high) {
            if (low >= high) {
                return;
            }
            int mid = (low + high) / 2;
            //将左半边排序
            sort(a, low, mid);
            //将右半边排序
            sort(a, mid + 1, high);
            merge(a, low, mid, high);
        }

        public  void merge(int[] a, int low, int mid, int high) {
            //将a[low..mid]和a[mid+1..high]归并
            int i = low, j = mid + 1;
            for (int k = low; k <= high; k++) {
                aux[k] = a[k];
            }

            for (int k = low; k <= high; k++) {
                if (i > mid) {
                    a[k] = aux[j++];
                } else if (j > high) {
                    a[k] = aux[i++];
                } else if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                } else {
                    a[k] = aux[i++];
                }
            }
        }
    }

    /**
     * 基数排序
     */
    public static void sort6(int[] arr) {
        if (arr.length <= 1) return;

        //取得数组中的最大数，并取得位数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxDigit = 1;
        while (max / 10 > 0) {
            maxDigit++;
            max = max / 10;
        }
        //申请一个桶空间
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //从低位到高位，对每一位遍历，将所有元素分配到桶中
        for (int i = 0; i < maxDigit; i++) {
            int[] bktLen = new int[10];        //存储各个桶中存储元素的数量

            //分配：将所有元素分配到桶中
            for (int j = 0; j < arr.length; j++) {
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            //收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            int k = 0;
            for (int b = 0; b < buckets.length; b++) {
                for (int p = 0; p < bktLen[b]; p++) {
                    arr[k++] = buckets[b][p];
                }
            }
            System.out.println("Sorting: " + Arrays.toString(arr));
            base *= 10;
        }
    }

    /**
     *
     */

}
