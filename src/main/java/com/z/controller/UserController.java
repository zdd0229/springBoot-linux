package com.z.controller;

import com.z.bean.PO.UserPo;
import com.z.bean.VO.UserVo;
import com.z.constant.RedisKey;
import com.z.jsonres.GlobalReturnCode;
import com.z.jsonres.JsonResult;
import com.z.service.UserService;
import com.z.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/checkLogin")
    public JsonResult login(HttpServletRequest request,@RequestBody UserVo userVo){

        UserPo user= userService.getUserByName(userVo.getUsername());
        if(user!=null && user.getPassword().equals(userVo.getPassword())){
            //登陆成功
            String accessToken= UUID.randomUUID().toString();
            //放入redis
            redisUtil.set(RedisKey.ACCESS_TOKEC+accessToken,user,30*60);

            //UserPo userPo = redisUtil.get(RedisKey.ACCESS_TOKEC+accessToken,UserPo.class);

            Map<String,String> res= new HashMap<String,String>();
            res.put("accessToken",accessToken);
            return new JsonResult(true, GlobalReturnCode.OPERA_SUCCESS,res);
        }else {
            return new JsonResult(false,"20102");
        }

    }

}
