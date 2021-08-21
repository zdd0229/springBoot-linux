package com.z.bean.entity;

import lombok.Data;

@Data
public class Category {

    private Long catId;
    private String name;
    private Long parentCid;
    private Long catLevel;
    private Long showStatus;
    private Long sort;
    private String icon;
    private String productUnit;
    private Long productCount;

}
