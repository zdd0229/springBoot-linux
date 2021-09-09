package com.z.bean.VO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class OrderConfirmVo {

    @Getter @Setter
    private List<MemberAddressVo> address;

    @Getter @Setter
    private List<OrderItemVo> items;

    @Getter @Setter
    Map<Long,Boolean> stocks;

    @Getter
    @Setter
    /** 优惠券（会员积分） **/
    private Integer integration;

    @Getter @Setter
    /** 防止重复提交的令牌 **/
    private String orderToken;

}
