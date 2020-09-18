package com.z.designpatterns.structure.proxy;

public class Main {

    /**
     * 静态代理
     * <p>
     * 在某些情况下，一个客户不想或者不能直接引用一个对 象，此时可以通过一个称之
     * 为“代理”的第三者来实现 间接引用。代理对象可以在客户端和目标对象之间起到
     * 中介的作用，并且可以通过代理对象去掉客户不能看到 的内容和服务或者添加客户
     * 需要的额外服务。
     * <p>
     * 通过引入一个新的对象（如小图片和远程代理 对象）来实现对真实对象的操作或者
     * 将新的对 象作为真实对象的一个替身，这种实现机制即 为代理模式，通过引入代
     * 理对象来间接访问一 个对象，这就是代理模式的模式动机。
     *
     * @param args
     */
    public static void main(String[] args) {

        Subject realSubject = new RealSubject();

        Subject subject = new Proxy(realSubject);

        subject.request();

    }

}
