package Class08_HuaDongChuangKouDanDiaoZhan;

import java.util.LinkedList;

/**
 * 题目：假设一个固定大小为W的窗口，依次划过arr。返回每一次滑出状况的最大值
 * 例如, arr = [4,3.5,4,3,3,6,7],W = 3
 * 返回:[5.5,5,4,6,7]
 */
public class Code01_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w){
        if (arr == null || arr.length == 0||w<=0) {
            return null;
        }
        int[] res=new int[arr.length-w+1];//长度为3对应1个结果，3-w+1
        int index=0;//专门给res数组用
        //这是一个双端队列。队头放大的值，需要谁就把谁放在队头。里面的值是下标
        LinkedList<Integer> qmax=new LinkedList<>();
        for (int R=0;R<arr.length;R++){//arr的每一个数一个一个进入窗口
            //不断的循环，直到当前队尾比R对应的值大，或者一直到队列为空。
            while (!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[R]){//把队列里面值比R小的先踢出去
                //比不过R就从尾巴出去，位置留给R
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (R>=w-1){//从窗口扩大到w开始之后。不仅要进去一个，还要过期一个。在过期一个之前顺便得到一个结果
                res[index++]=arr[qmax.peekFirst()];//先得到一个结果再去过期一个
                if (R-w+1==qmax.peekFirst()){//过期操作。R-w+1是过期的位置，去判断当前队头是不是过期了！
                    qmax.pollFirst();//过期就从队头出去
                }
            }
        }
        return res;
    }

    // for test
    public static int[] rightWay(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = rightWay(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
