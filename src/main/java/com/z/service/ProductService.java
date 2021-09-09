package com.z.service;

import com.z.bean.VO.SkuItemVo;
import com.z.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ProductService {

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private ProductMapper productMapper;

    public SkuItemVo productDetail1(Long skuId) {
        SkuItemVo itemVo = new SkuItemVo();

        //1、sku基本信息的获取  pms_sku_info
        Map<String, Object> skuInfo = productMapper.getSkuInfo(skuId);
        itemVo.setInfo(skuInfo);

        //2、sku的图片信息    pms_sku_images
        itemVo.setImages(productMapper.getSkuImages(skuId));

        //3、获取spu的销售属性组合
        itemVo.setSaleAttr(productMapper.getSaleAttr((Long) skuInfo.get("spu_id")));

        //4、获取spu的介绍
        itemVo.setDesc(productMapper.getSpuDesc((Long) skuInfo.get("spu_id")));

        //5、获取spu的规格参数信息
        itemVo.setGroupAttrs(productMapper.getGroupAttrs((Long) skuInfo.get("spu_id"), (Long) skuInfo.get("catalog_id")));

        return itemVo;
    }

    /**
     * 多线程版本
     *
     * @param skuId
     * @return
     */
    public SkuItemVo productDetail2(Long skuId) {
        SkuItemVo itemVo = new SkuItemVo();

        CompletableFuture<Map<String, Object>> getSkuInfo = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息的获取  pms_sku_info
            Map<String, Object> skuInfo = productMapper.getSkuInfo(skuId);
            itemVo.setInfo(skuInfo);
            return skuInfo;
        }, executor);

        CompletableFuture<Void> getSkuImages = CompletableFuture.runAsync(() -> {
            //2、sku的图片信息    pms_sku_images
            itemVo.setImages(productMapper.getSkuImages(skuId));
        }, executor);

        CompletableFuture<Void> getSaleAttr = getSkuInfo.thenAcceptAsync((skuInfo) -> {
            //3、获取spu的销售属性组合
            itemVo.setSaleAttr(productMapper.getSaleAttr((Long) skuInfo.get("spu_id")));
        }, executor);

        CompletableFuture<Void> getSpuDesc = getSkuInfo.thenAcceptAsync((skuInfo) -> {
            //4、获取spu的介绍
            itemVo.setDesc(productMapper.getSpuDesc((Long) skuInfo.get("spu_id")));
        }, executor);

        CompletableFuture<Void> getGroupAttrs = getSkuInfo.thenAcceptAsync((skuInfo) -> {
            //5、获取spu的规格参数信息
            itemVo.setGroupAttrs(productMapper.getGroupAttrs((Long) skuInfo.get("spu_id"), (Long) skuInfo.get("catalog_id")));
        }, executor);

        try {
            CompletableFuture.allOf(getSkuImages, getSaleAttr, getSpuDesc, getGroupAttrs).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return itemVo;
    }
}
