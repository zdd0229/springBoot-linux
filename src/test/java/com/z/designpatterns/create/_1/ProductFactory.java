package com.z.designpatterns.create._1;

import com.z.util.StringUtil;

public class ProductFactory {
    public static Product getProduct(String a){
        if (StringUtil.isEmpty(a)){
            return null;
        }
        if (a.equals("a")){
            return new ProductA();
        }
        if (a.equals("b")){
            return new ProductB();
        }
        return null;
    }
}
