package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

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
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> list(){
        return departService.listAllDeparts();
    }

}
