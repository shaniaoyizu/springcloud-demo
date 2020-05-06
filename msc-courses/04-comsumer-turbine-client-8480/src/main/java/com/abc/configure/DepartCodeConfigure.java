package com.abc.configure;

import com.abc.balcance.CustomRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
@Configuration
public class DepartCodeConfigure {

    // 修改负载均衡策略为轮询
//    @Bean
//    public IRule loadBalance(){
//        return new RoundRobinRule();
//    }

    @Bean
    public IRule loadBalance(){
        List<Integer> excludePorts = new ArrayList<>();
        excludePorts.add(8883);
        return new CustomRule(excludePorts);
    }
}
