package com.gaohanghang.springbootsynchronizedtest.service;

import com.gaohanghang.springbootsynchronizedtest.dao.EmployeeReporsitory;
import com.gaohanghang.springbootsynchronizedtest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/23 16:29
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeReporsitory employeeReporsitory;

    @Transactional
    public void addEmployeeAge() {
        // 查出ID为8的记录，然后每次将年龄增加一
        Employee employee = employeeReporsitory.getOne(8);
        System.out.println(employee);
        Integer age = employee.getAge();
        employee.setAge(age + 1);

        employeeReporsitory.save(employee);
    }
}
