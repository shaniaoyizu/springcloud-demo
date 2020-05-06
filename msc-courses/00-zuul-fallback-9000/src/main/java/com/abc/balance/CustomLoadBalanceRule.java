package com.abc.balance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunbc on 2020-03-02
 */
@Configuration
public class CustomLoadBalanceRule {
    // 设置zuul对Consumer的负载均衡策略为“随机策略”
    @Bean
    public IRule loadBalanceRule(){
        return new RandomRule();
    }
}
