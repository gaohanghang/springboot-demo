package com.gaohanghang.springbootsynchronizedtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/23 19:42
 */
@Service
public class SynchronizedService {

    @Autowired
    private EmployeeService employeeService;

    // 同步
    public synchronized void synchronizedAddEmployee() {
        employeeService.addEmployeeAge();
    }
}
