package com.z.suanfa.paixu;

import sun.net.www.protocol.http.HttpURLConnection;

public class Paixu {
    //冒泡：起始时，从最后一个数字开始逐个与前面的数字比较（小则替换，大则不替换），
    //直至确定当前的索引号的位置；（外循环确定当前索引号，内循环执行冒泡比较）
    public int[] maoPao (int a[]){

        //通过冒泡似的最后一位最大
        for (int i = a.length-1; i >0; i--) {
            for (int j=0;j<i;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                }
            }
        }
        return a;
    }

    //冒泡排序，设置flag字段，如果上一层排序未发生交换，直接退出
    public int[] maoPaoPlus (int a[]){

        for (int i = a.length-1; i >0; i--) {
            boolean swap= false;
            for (int j=0;j<i;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                    swap= true;
                }
            }
            if (!swap) {
                break;
            }
        }
        return a;
    }

    //选择排序
    public int[] xuanZe (int a[]){

        for (int i = a.length-1; i >0; i--) {
            for (int j=0;j<i;j++){
                if(a[j]>a[i]){
                    int temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }
        return a;
    }

}
