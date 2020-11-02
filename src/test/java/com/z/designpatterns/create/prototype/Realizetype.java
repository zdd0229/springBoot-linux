package com.z.designpatterns.create.prototype;

public class Realizetype implements Cloneable {

    Realizetype() {
        System.out.println("具体原型创建成功");
    }

    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功");
        return (Realizetype) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Realizetype r1 = new Realizetype();
        Realizetype r2 = (Realizetype) r1.clone();

        System.out.println("r1 == r2 ? " + (r1 == r2));

    }
}
