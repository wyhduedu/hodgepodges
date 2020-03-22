package com.wy.hodgepodges.service.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wy
 * @version V1.0
 * @desc 拦截器
 * @date 2020-03-21 18:16
 */
@Slf4j
public class MvcIntercept extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTIme",System.currentTimeMillis());
       return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime  =System.currentTimeMillis();
        log.info("处理时间->{}ms",new Long(endTime-startTime));
        request.setAttribute("handleTime",endTime-startTime);
    }

}
