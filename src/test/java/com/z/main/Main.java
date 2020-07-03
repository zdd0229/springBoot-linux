package com.z.main;

import com.z.util.StringUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        String note="";
//
//        System.out.println(note.length());
//        if (StringUtils.isNotEmpty(note)&&note.length()>10){
//            note=note.substring(0,10);
//        }
//        System.out.println(note);
//        System.out.println(note.length());

        Scanner in = new Scanner(System.in);
        int x = 0;//记录正数的个数
        int y = 0;//记录负数的个数

        while(true){
            System.out.println("请输入一个数：");
            int num = in.nextInt();
            if(num > 0){
                x++;
            }else if(num < 0){
                y++;
            }else{
                break;
            }
        }
        System.out.println("正数的个数为：" + x + "个");
        System.out.println("负数的个数为：" + y + "个");

    }

}
