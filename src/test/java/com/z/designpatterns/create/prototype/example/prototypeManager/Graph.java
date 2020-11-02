package com.z.designpatterns.create.prototype.example.prototypeManager;

public abstract class Graph implements Cloneable{
    public abstract double getArea();
    public Graph clone(){
        Graph graph = null;

        try {
            graph = (Graph)super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("图形克隆失败");
        }

        return graph;
    };
}
