package com.z.designpatterns.structure.adapter;

public class Main {

    /**
     * 在软件开发中采用类似于电源适配器的设计和编码技巧被称为适配器模式。
     * 通常情况下，客户端可以通过目标类的接口访问它所提供的服务。有时，现有的类可以满足客户类的功能需要，
     * 但是它所提供的接口不一定是客户类所期望的，这可能是因为现有类中方法名与目标类中定义的方法名不一致
     * 等原因所导致的。
     * 在这种情况下，现有的接口需要转化为客户类期望的接口，这样保证了对现有类的重用。如果不进行这样的转
     * 化，客户类就不能利用现有类所提供的功能，适配器模式可以完成这样的转化。
     * 在适配器模式中可以定义一个包装类，包装不兼容接口的对象，这个包装类指的就是适配器(Adapter)，它所
     * 包装的对象就是适配者(Adaptee)，即被适配的类。
     * 适配器提供客户类需要的接口，适配器的实现就是把客户类的请求转化为对适配者的相应接口的调用。也就是
     * 说：当客户类调用适配器的方法时，在适配器类的内部将调用适配者类的方法，而这个过程对客户类是透明的，
     * 客户类并不直接访问适配者类。因此，适配器可以使由于接口不兼容而不能交互的类可以一起工作。这就是适
     * 配器模式的模式动机。
     * @param args
     */

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target= new Adapter(adaptee);
        target.request("666");
    }
}
