package com.z.designpatterns.create.prototype.example.prototypeManager;

import java.util.HashMap;
import java.util.Map;

public class PrototypeManager  {

    private static Map<String,Graph> all = new HashMap<>();

    static {
        all.put("square",new Square(0));
        all.put("circular",new Circular(0));
    }

    public static void add(String name,Graph Graph){
        all.put(name,Graph);
    }

    public static Graph get(String name){
        return all.get(name).clone();
    }

    public static void main(String[] args) {

        Circular c1 =(Circular) PrototypeManager.get("circular");
        Circular c2 =(Circular) PrototypeManager.get("circular");
        c1.setRadius(3);
        c2.setRadius(4);
        System.out.println(c1.getArea());
        System.out.println(c2.getArea());

        System.out.println(c1==c2);

    }

}
