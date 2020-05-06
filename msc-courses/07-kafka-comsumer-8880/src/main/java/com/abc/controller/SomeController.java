package com.abc.controller;

import com.abc.bean.Depart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
@Slf4j
@RestController
@RequestMapping("/consumer/depart")
public class SomeController {

    @Autowired
    public RestTemplate restTemplate;

    // 使用微服务名称来从eureka server查找提供者
    private static final String SERVICE_PROVIDE = "http://abcmsc-provider-depart";

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        String url = SERVICE_PROVIDE + "/provider/depart/save";
        return restTemplate.postForObject(url,depart,Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") int id){
        String url = SERVICE_PROVIDE + "/provider/depart/del/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void update(@RequestBody Depart depart){
        String url = SERVICE_PROVIDE + "/provider/depart/update";
        restTemplate.put(url,depart);
    }

    @GetMapping("/get/{id}")
    public Depart getById(@PathVariable("id") int id){
        log.info("调用consumer的getById()方法");
        String url = SERVICE_PROVIDE + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url,Depart.class);
    }

    @GetMapping("/list")
    public List<Depart> list(){
        String url = SERVICE_PROVIDE + "/provider/depart/list";
        return restTemplate.getForObject(url,List.class);
    }
}
