package com.z.designpatterns.structure.decorator;

public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("具体操作");
    }
}
