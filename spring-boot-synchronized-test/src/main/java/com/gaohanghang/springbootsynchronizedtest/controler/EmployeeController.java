package com.gaohanghang.springbootsynchronizedtest.controler;

import com.gaohanghang.springbootsynchronizedtest.service.EmployeeService;
import com.gaohanghang.springbootsynchronizedtest.service.SynchronizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/23 16:29
 */
@RestController
public class EmployeeController {

    @Autowired
    private SynchronizedService synchronizedService;

    @RequestMapping("/add")
    public void addEmplyee() {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> synchronizedService.synchronizedAddEmployee()).start();
        }
    }
}
