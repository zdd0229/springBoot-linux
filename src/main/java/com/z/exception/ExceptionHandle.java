package com.z.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException e,HttpServletRequest request){

        boolean ifAjax= request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");

        printHeadersInfo(request);

        if(!ifAjax){
            ModelAndView mav = new ModelAndView("error");
            return mav;
        }else{
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject("id", e.getId());
            mav.addObject("msg", e.getMsg());
            return mav;
        }
    }

    private void printHeadersInfo(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key+"  "+value);
        }
    }

}
