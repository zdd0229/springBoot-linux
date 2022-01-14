package com.z.core.exception;

public class PrintException {

    public static void main(String[] args) {
        try{
            int a = 1/0;
        }catch (Exception e){
            System.out.println(printException(e));
        }
    }

    //打印异常信息
    public static String printException(Exception e){

        String wrap = "\n";

        StringBuilder res = new StringBuilder();
        res.append(e.toString()).append(wrap);

        for (StackTraceElement i : e.getStackTrace()){
            res.append(i.toString()).append(wrap);
        }

        return res.toString();
    }
}
