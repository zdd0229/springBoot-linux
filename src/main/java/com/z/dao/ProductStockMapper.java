package com.z.dao;

import com.z.bean.PO.ProductStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockMapper {

    Integer deductStock(@Param("pid")Integer pid,@Param("num") Integer num);

    ProductStock getStock(Integer pid);

    Integer addStock(@Param("pid")Integer pid,@Param("num") Integer num);
}
