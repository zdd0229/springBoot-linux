package com.z.designpatterns.structure.faced;

public class Main {

    /**
     * 外观模式(Facade Pattern)：外部与一个子系统的通信必须通过一个统一
     * 的外观对象进行，为子系统中的一组接口提供一个一致的界面，外观模式定
     * 义了一个高层接口，这个接口使得这一子系统更加容易使用。外观模式又称
     * 为门面模式，它是一种对象结构型模式。
     * @param args
     */
    public static void main(String[] args) {

        Faced faced = new Faced();
        faced.operation();

    }

}
