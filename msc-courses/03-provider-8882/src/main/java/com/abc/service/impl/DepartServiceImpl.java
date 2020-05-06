package com.abc.service.impl;

import com.abc.bean.Depart;
import com.abc.repository.DepartRepository;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository departRepository;

    @Value("${server.port}")
    private int port;

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
        if (departRepository.existsById(id)){
            Depart depart = departRepository.getOne(id);
            depart.setName(depart.getName() + port);
            return  depart;
        }
        Depart depart = new Depart();
        depart.setName("No exist this depart" + port);
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        List<Depart> departList = departRepository.findAll();
        departList.forEach(depart -> depart.setName(depart.getName() + port));
        return departList;
    }
}
