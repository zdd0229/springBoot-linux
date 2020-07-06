package com.z.config.interceptor;

import com.z.bean.jsonres.GlobalReturnCode;
import com.z.bean.jsonres.JsonResult;
import com.z.util.JsonUtil;
import com.z.util.StringUtil;
import com.z.util.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(StringUtil.isEmpty(request.getHeader("Authorization"))|| TokenUtil.getToken(request)==null){
            //设置为未授权
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            StringUtil.out(response, JsonUtil.toStr(new JsonResult(false, GlobalReturnCode.NO_AUTH)));
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
