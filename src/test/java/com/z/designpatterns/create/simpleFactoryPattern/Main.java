package com.z.designpatterns.create.simpleFactoryPattern;

/**
 *  在日常开发中，凡是需要生成复杂对象的地方，都可以尝试考虑使用工厂模式来代替。
 *  注意：上述复杂对象指的是类的构造函数参数过多等对类的构造有影响的情况，因为类的构造过于复杂，如果直接在其他业务类内使用，
 * 则两者的耦合过重，后续业务更改，就需要在任何引用该类的源代码内进行更改，光是查找所有依赖就很消耗时间了，更别说要一个一
 * 个修改了。
 */

public class Main {
    public static void main(String[] args) {
        Product product =ProductFactory.getProduct("a");
        product.use();
    }
}
