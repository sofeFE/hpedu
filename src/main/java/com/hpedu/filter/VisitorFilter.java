package com.hpedu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
//@ConditionalOnBean(VisitorListener.class)
public class VisitorFilter implements Filter {

    /*方案1 使用上下文 来保存访问量*/
    private ServletContext context;
    /* 使用 原子数 来记录 计数*/
    private AtomicInteger counter = new AtomicInteger(0);
    /*使用线程池*/
    private ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(10);
    private Object lock = new Object();
    private Boolean done = false ;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 访问特定网页 增加访客数量
     * 保证原子操作,线程安全.
     * <p>
     * 应该改为异步操作, 保证访问流畅
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (context == null) {
            context = req.getServletContext();
            Object visitorNum = context.getAttribute("visitorNum");
            counter.set((int) visitorNum);
        }
        if (req.getRequestURI().contains("classindex.html") || req.getRequestURI().contains("index.html")) {
            asynTask(req);
        }
        chain.doFilter(request, response);
    }

    /**
     * 异步 无锁 更新上下文对象中的访问量.
     * TODO delete one
     *
     * @param request
     */
    private void asynTask(ServletRequest request) {
        scheduExec.submit(new Runnable() {
            @Override
            public void run() {
                boolean flag = false;
                do {
                    int value = counter.intValue();
                    flag = counter.compareAndSet(value, value + 1);

                } while (!flag);
                //TODO  会不会出现多线程同时 调用context摄入 属性值  visitorNum ，是否安全？
                context.setAttribute("visitorNum", counter.get());
            }
        });

    }



    @Override
    public void destroy() {
//        //将访问量 存入 redis
//        redisUtil.set("visitorNum",counter.get());
    }


}
