package com.z.designpatterns.structure.adapter.example.electric;

public class Computer implements Voltage {
    @Override
    public void work(int voltage) {
        if(voltage==100){
            System.out.println("正常工作");
        }else {
            System.out.println("电压异常");
        }
    }
}
