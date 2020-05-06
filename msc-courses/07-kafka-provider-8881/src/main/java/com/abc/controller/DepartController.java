package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sunbc on 2020-02-25
 */
@Slf4j
@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

    @Autowired //声明服务发现客户端
    private DiscoveryClient client;

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return departService.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart){
        return departService.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getOne(@PathVariable("id") Integer id){
        log.info("调用provider的getOne()方法");
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> list(){
        return departService.listAllDeparts();
    }

    @GetMapping("/discovery")
    public List<String> discovery(){
        List<String> services = client.getServices();
        for (String name : services){
            // 获取当前微服务服务名称的所有提供者主机
            List<ServiceInstance> instances = client.getInstances(name);
            // 遍历所有提供者主机的详情
            for (ServiceInstance instance : instances){
                // 获取当前提供者的唯一标识, service id
                String serviceId = instance.getServiceId();
                String instanceId = instance.getInstanceId();
                // 获取当前提供者主机的host
                String host = instance.getHost();
                Map<String, String> metadata = instance.getMetadata();
                System.out.println("serviceId = " + serviceId);
                System.out.println("instanceId = " + instanceId);
                System.out.println("host = " + host);
                System.out.println("metadata = " + metadata);
            }
        }
        return services;
    }
}
