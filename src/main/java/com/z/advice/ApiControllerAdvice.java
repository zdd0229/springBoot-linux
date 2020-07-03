package com.z.advice;

import com.z.jsonres.GlobalReturnCode;
import com.z.jsonres.JsonResult;
import com.z.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@ControllerAdvice
public class ApiControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger("controllerLog");

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(Exception e,HttpServletRequest request, HttpServletResponse response){
        logger.error("系统异常",e);
        logException(request);
        if(e instanceof NoHandlerFoundException){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new JsonResult(false, GlobalReturnCode.SYSTEM_PATH_NOEXIST);
        }else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new JsonResult(false, GlobalReturnCode.SYSTEM_ERROR);
        }
    }

    /**
     * 取得header的所有属性
     *
     * @param headers 请求的headers
     * @param request request
     * @return 把header拼成字符串
     */
    private String getHeaderValue(Enumeration<String> headers, HttpServletRequest request) {
        String str = "";
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            str += header + "=" + request.getHeader(header) + "&";
        }
        return str;
    }

    /**
     * 打印所有异常信息
     *
     * @param request request
     */
    private void logException(HttpServletRequest request) {
        try {
            logger.error("请求路径："+request.getServletPath());
            logger.error("请求参数：" + request.getParameterMap().toString());
            logger.error("请求header:" + getHeaderValue(request.getHeaderNames(), request));
            logger.error("请求body:" + StringUtils.getBodyString(request.getReader()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
