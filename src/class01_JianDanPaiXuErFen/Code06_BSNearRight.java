package class01_JianDanPaiXuErFen;

import java.util.Arrays;

import static class01_JianDanPaiXuErFen.Code01_SelectSort.generateArray;

public class Code06_BSNearRight {

    private static int nearestIndex(int[] sortedArray,int num){//小于num的最大数
        if (sortedArray==null&&sortedArray.length==0)
            return -1;
        int left=0,right=sortedArray.length-1,mid=0,index=-1;
        while(left<=right){
            mid=((right-left)>>1)+left;
            if (sortedArray[mid]<num){
                index=mid;//更新index
                left=mid+1;//往右边继续去找
            }
            else
                right=mid-1;//右边找不到，往左边去去找
        }
        return index;//如果没有找到那么index就是等于-1
    }

    private static int Test(int[] sortedArray,int checkNum){
        if (sortedArray==null&&sortedArray.length==0)
            return -1;
        int index;
        for (index=sortedArray.length-1;index>=0&&sortedArray[index]>=checkNum;index--);
        if (index<0)
            return -1;
        else return index;
    }

    public static void main(String[] args) {
        int testTimes=1000;
        int maxSize=30;
        int maxValue=200;
        boolean isSuccess=true;

        for (int i=0;i<testTimes;i++){
            int [] arr=generateArray(maxSize,maxValue);
            Arrays.sort(arr);
            int checkNum=(int)((maxValue+1)*Math.random());
            if (Test(arr,checkNum)!=nearestIndex(arr,checkNum)){//只要有一次测试不成功就退出
                isSuccess=false;
                break;
            }
        }
        System.out.println(isSuccess?"Nice!":"Fucked!");
    }
}
