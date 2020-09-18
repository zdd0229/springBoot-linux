package com.z.designpatterns.structure.faced;

public class Faced {

    private SubSystem1 s1 = new SubSystem1();
    private SubSystem2 s2 = new SubSystem2();
    private SubSystem3 s3 = new SubSystem3();

    public void operation(){
        s1.step();
        s2.step();
        s3.step();
    }

}
