package com.abc.service;

import com.abc.bean.Depart;

import java.util.List;

/**
 * Created by sunbc on 2020-02-25
 */
public interface DepartService {
    boolean saveDepart(Depart depart);
    boolean removeDepartById(Integer id);
    boolean modifyDepart(Depart depart);
    Depart getDepartById(int id);
    List<Depart> listAllDeparts();
}
