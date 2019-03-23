package com.gaohanghang.springbootsynchronizedtest.dao;

import com.gaohanghang.springbootsynchronizedtest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/23 16:31
 */
@Repository
public interface EmployeeReporsitory extends JpaRepository<Employee,Integer> {
}
