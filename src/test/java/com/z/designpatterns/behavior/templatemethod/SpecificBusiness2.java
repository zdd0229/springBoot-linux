package com.z.designpatterns.behavior.templatemethod;

public class SpecificBusiness2 extends SpecificBusiness {
    @Override
    public void dataTrans() {
        System.out.println("场景2的数据抽取方法");
    }
}
