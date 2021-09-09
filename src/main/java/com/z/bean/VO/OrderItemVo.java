package com.z.bean.VO;

import lombok.Data;

@Data
public class OrderItemVo {

    private Long goodsId;

    private String goodsName;

    private String price;

    private Integer amount;

}
