package com.z.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    Map<String, Object> getSkuInfo(Long skuId);

    List<Map<String, Object>> getSkuImages(Long skuId);

    List<Map<String, Object>> getSaleAttr(Long spuId);

    Map<String, Object> getSpuDesc(Long spuId);

    List<Map<String, Object>> getGroupAttrs(@Param("spuId") Long spuId,@Param("catalogId") Long catalogId);
}
