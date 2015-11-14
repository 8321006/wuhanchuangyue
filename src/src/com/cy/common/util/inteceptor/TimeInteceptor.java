package com.cy.common.util.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* @ClassName: TimeInteceptor
* @Description: 记录每个请求花费的时间
* @author James Yang  
* @date 2015年6月29日 上午9:48:16
*
 */
public class TimeInteceptor implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(TimeInteceptor.class);
	 
    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request, 
        HttpServletResponse response, Object handler)throws Exception {
        	long startTime = System.currentTimeMillis();
        	request.setAttribute("startTime", startTime);
        	return true;
    }
 
    //after the handler is executed
    public void postHandle(
        HttpServletRequest request, HttpServletResponse response, 
        Object handler, ModelAndView modelAndView)throws Exception {
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        //modelAndView.addObject("executeTime",endTime-startTime);
        if(logger.isDebugEnabled()){
        	//logger.debug("================>> [" + handler + "] executeTime : " + (endTime-startTime) + "ms");
        	logger.error("================>> [" + request.getRequestURI() + "] executeTime : " + (endTime-startTime) + "ms");
        	if((endTime-startTime) > 200){
        		logger.error("================>> [" + request.getRequestURI() + "] 请求响应时间太长,请注意优化性能。。。");
        	}
        }
    }
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
}
