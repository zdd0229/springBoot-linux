package com.z.config.advice;

import com.z.bean.jsonres.GlobalReturnCode;
import com.z.bean.jsonres.JsonResult;
import com.z.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@ControllerAdvice
public class ApiControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger("controllerLog");


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public JsonResult customErrorHandler(BusinessException e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("自定义异常", e);
        return new JsonResult(false, "-1", e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("系统异常", e);
        this.logException(request);
        if (e instanceof NoHandlerFoundException) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new JsonResult(false, GlobalReturnCode.SYSTEM_PATH_NOEXIST);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new JsonResult(false, GlobalReturnCode.SYSTEM_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Order(0)
    public JsonResult argumentNotValid(HttpServletRequest request, MethodArgumentNotValidException e) {

        logger.info("请求参数不正确", e);
        this.logException(request);

        String validation_message;
        BindingResult bindingResult = e.getBindingResult();

        if (bindingResult != null && bindingResult.getFieldError() != null) {
            validation_message = bindingResult.getFieldError().getDefaultMessage();
        } else {
            validation_message = e.getMessage();
        }

        logger.info("参数错误信息：" + validation_message);

        return new JsonResult(false, GlobalReturnCode.PARAM_ERROR, validation_message);
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
            logger.error("请求路径：" + request.getServletPath());
            logger.error("请求参数：" + request.getParameterMap().toString());
            logger.error("请求header:" + getHeaderValue(request.getHeaderNames(), request));
//            logger.error("请求body:" + StringUtil.getBodyString(request.getReader()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
