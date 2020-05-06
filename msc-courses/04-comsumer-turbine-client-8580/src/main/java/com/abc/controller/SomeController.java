package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
@RestController
@RequestMapping("/consumer/depart")
public class SomeController {

    @Autowired
    private DepartService departService;


    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean delete(@PathVariable("id") int id){
        return departService.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart){
        return departService.modifyDepart(depart);
    }

    @HystrixCommand(fallbackMethod = "getHystrixHandler",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"))
    @GetMapping("/get/{id}")
    public Depart getById(@PathVariable("id") int id){
        return departService.getDepartById(id);
    }

    public Depart getHystrixHandler(@PathVariable("id") int id){
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- method");
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> list(){
        return departService.listAllDeparts();
    }
}
