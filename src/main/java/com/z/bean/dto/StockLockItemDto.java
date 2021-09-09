package com.z.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 哪个订单锁了哪个sku的多少库存
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLockItemDto implements Serializable {
    private Long orderId;
    private Long wareId;
    private Long skuId;
    private Integer num;
}
