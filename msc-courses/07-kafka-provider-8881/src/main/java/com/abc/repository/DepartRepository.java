package com.abc.repository;

import com.abc.bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sunbc on 2020-02-25
 */
public interface DepartRepository extends JpaRepository<Depart,Integer> {
}
