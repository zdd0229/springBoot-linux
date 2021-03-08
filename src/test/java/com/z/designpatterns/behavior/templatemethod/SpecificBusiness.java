package com.z.designpatterns.behavior.templatemethod;

public abstract class SpecificBusiness {

    public abstract void dataTrans();

    public void dataCal(){
        System.out.println("数据计算");
    }

    public void dataResult(){
        System.out.println("数据结果处理");
    }

    public void exec(){
        dataTrans();
        dataCal();
        dataResult();
    }

}
