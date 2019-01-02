package top.murphypen.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DemoInterceptor extends HandlerInterceptorAdapter {

    final static Logger logger = LoggerFactory.getLogger(DemoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info(">>>startTime:{}", startTime);
        request.setAttribute("startTime", startTime);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        Long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        logger.info(">>>本次请求处理时间为:{}ms", new Long(endTime - startTime));
        request.setAttribute("handlingTime", endTime - startTime);
    }
}
