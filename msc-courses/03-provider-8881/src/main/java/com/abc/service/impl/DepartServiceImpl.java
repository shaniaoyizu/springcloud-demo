package com.abc.service.impl;

import com.abc.bean.Depart;
import com.abc.repository.DepartRepository;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunbc on 2020-02-25
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository departRepository;

    @Override
    public boolean saveDepart(Depart depart) {
        Depart obj = departRepository.save(depart);
        return obj != null ? true : false;
    }

    @Override
    public boolean removeDepartById(Integer id) {
        if(departRepository.existsById(id)){
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        Depart obj = departRepository.save(depart);
        return obj != null ? true : false;
    }

    @Override
    public Depart getDepartById(int id) {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (departRepository.existsById(id)){
            return  departRepository.getOne(id);
        }
        Depart depart = new Depart();
        depart.setName("No exist this depart");
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        return departRepository.findAll();
    }
}
