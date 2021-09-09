package com.z.controller;

import com.z.bean.VO.SkuItemVo;
import com.z.bean.jsonres.JsonResult;
import com.z.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 商品详情信息获取
     */
    @GetMapping("detail/{skuId}/multiple-thread/{thread}")
    public JsonResult productDetail(@PathVariable Long skuId, @PathVariable int thread)   {
        SkuItemVo res;
        if (thread==1){
            res = productService.productDetail1(skuId);
        }else {
            res = productService.productDetail2(skuId);
        }
        return JsonResult.success(res);
    }

}
