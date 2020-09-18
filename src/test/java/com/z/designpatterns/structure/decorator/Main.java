package com.z.designpatterns.structure.decorator;

public class Main {
    /**
     * 一般有两种方式可以实现给一个类或对象增加行为：
     *
     * 继承机制，使用继承机制是给现有类添加功能的一种有效途径，通过继承一个现有类
     * 可以使得子类在拥有自身方法的同时还拥有父类的方法。但是这种方法是静态的，用
     * 户不能控制增加行为的方式和时机。
     * 关联机制，即将一个类的对象嵌入另一个对象中，由另一个对象来决定是否调用嵌入
     * 对象的行为以便扩展自己的行为，我们称这个嵌入的对象为装饰器(Decorator)
     * 装饰模式以对客户透明的方式动态地给一个对象附加上更多的责任，换言之，客户端
     * 并不会觉得对象在装饰前和装饰后有什么不同。装饰模式可以在不需要创造更多子类
     * 的情况下，将对象的功能加以扩展。这就是装饰模式的模式动机。
     * @param args
     */
    public static void main(String[] args) {

        Component component = new ConcreteComponent();
        Component Dcomponent = new ConcreteDecoratorA(component);
        Dcomponent.operation();

    }
}
