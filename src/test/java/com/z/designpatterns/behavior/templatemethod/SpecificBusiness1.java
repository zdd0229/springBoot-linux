package com.z.designpatterns.behavior.templatemethod;

public class SpecificBusiness1 extends SpecificBusiness {
    @Override
    public void dataTrans() {
        System.out.println("场景1的数据抽取方法");
    }
}
