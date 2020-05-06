package com.abc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by sunbc on 2020-03-02
 */
@Component
public class GrayFilter2 extends ZuulFilter {

    private AtomicBoolean flag = new AtomicBoolean(true);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        // 所有请求都通过zuul过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 根据布尔变量的值的不同，路由到不同的主键，然后在将布尔值取反
        if (flag.get()) {
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "running-host");
            flag.set(false);
        }else {
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "gray-host");
            flag.set(true);
        }

        return null;
    }
}
