package com.z.util;

import com.z.bean.PO.UserPo;
import com.z.constant.RedisKey;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {

    public static UserPo getToken(HttpServletRequest request){
        String authorization=request.getHeader("Authorization");
        if(StringUtil.isNotEmpty(authorization)){
            RedisUtil redisUtil = (RedisUtil)SpringContextUtil.getBean("redisUtil");
            return redisUtil.get(RedisKey.ACCESS_TOKEN + authorization, UserPo.class);
        }else {
            return null;
        }
    }

}
