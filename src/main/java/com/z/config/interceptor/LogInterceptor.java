package com.z.config.interceptor;

import com.z.bean.PO.UserPo;
import com.z.util.StringUtil;
import com.z.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger("operationLog");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //权限校验
        String path = request.getServletPath();
        String contentType=request.getContentType();
        if("application/json".equals(contentType)){
            String parameters = StringUtil.getBodyString(request.getReader());
            UserPo userPo = TokenUtil.getToken(request);
            this.logOperation(path,parameters,userPo);
        }

        return true;
    }

    private void logOperation(String path, String parameters, UserPo user) {
        String log = "";
        if(user==null){
            user = new UserPo();
        }
        log = "[OPERALOG-操作日志]" + "-[" + user.getUsername() + "]" + "-[" + getSystemTime() + "]-" + "[INFO]-" + "-" + parameters;
        logger.info(log);
    }

    public static String getSystemTime() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strTime = df.format(new Date());
        return strTime;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
