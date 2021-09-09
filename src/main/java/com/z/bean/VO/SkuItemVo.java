package com.z.bean.VO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SkuItemVo {

    //1、sku基本信息的获取  pms_sku_info
    private Map<String,Object> info;

    private boolean hasStock = true;

    //2、sku的图片信息    pms_sku_images
    private List<Map<String,Object>> images;

    //3、获取spu的销售属性组合
    private List<Map<String,Object>> saleAttr;

    //4、获取spu的介绍
    private Map<String,Object> desc;

    //5、获取spu的规格参数信息
    private List<Map<String,Object>> groupAttrs;


}
