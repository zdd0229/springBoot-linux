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
    //积极主动 以终为始 要事第一 双赢思维 知彼解己 统合综合 不断更新
}
