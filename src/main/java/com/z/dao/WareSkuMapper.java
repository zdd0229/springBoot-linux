package com.z.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WareSkuMapper {

    int lockStock(@Param("wareId") Long wareId,
                  @Param("goodsId") Long goodsId,
                  @Param("amount") Integer amount);

    int unlockStock(@Param("wareId") Long l,
                    @Param("skuId")  Long skuId,
                    @Param("num")  Integer num);
}
