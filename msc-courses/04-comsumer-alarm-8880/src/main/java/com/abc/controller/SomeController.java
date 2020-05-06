package com.abc.controller;

import com.abc.bean.Depart;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunbc on 2020-02-25
 */
@RestController
@RequestMapping("/consumer/depart")
public class SomeController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ForkJoinPool pool = new ForkJoinPool(5);

    // 使用微服务名称来从eureka server查找提供者
    private static final String SERVICE_PROVIDE = "http://abcmsc-provider-depart";

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart) {
        String url = SERVICE_PROVIDE + "/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDE + "/provider/depart/del/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void update(@RequestBody Depart depart) {
        String url = SERVICE_PROVIDE + "/provider/depart/update";
        restTemplate.put(url, depart);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandler")
    @GetMapping("/get/{id}")
    public Depart getById(@PathVariable("id") int id, HttpServletRequest request) {
        String url = SERVICE_PROVIDE + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }

    public Depart getHystrixHandler(@PathVariable("id") int id, HttpServletRequest request) {
        //向管理员发出警报
        String ip = request.getLocalAddr();
        String key = ip + "_getHystrixHandler";
        // 指定存放Redis中的key为“ip_发生降级的方法名”
        alarm(key);
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

    // 降级发生后的警报
    private void alarm(String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        String value = ops.get();
        if (value == null) {
            synchronized (this) {
                value = ops.get();
                if (value == null) {
                    // 发送短信
                    sendMsg(key);
                    value = "已发生服务降级";
                    ops.set(value,10, TimeUnit.SECONDS);
                }
            }
        }
    }

    private void sendMsg(String key) {
        pool.submit(() -> System.out.println("发送服务降级警报：" + key));
    }

    @GetMapping("/list")
    public List<Depart> list() {
        String url = SERVICE_PROVIDE + "/provider/depart/list";
        return restTemplate.getForObject(url, List.class);
    }
}
