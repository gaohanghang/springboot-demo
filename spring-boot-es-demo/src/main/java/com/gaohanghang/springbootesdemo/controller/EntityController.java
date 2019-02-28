package com.gaohanghang.springbootesdemo.controller;

import com.gaohanghang.springbootesdemo.entity.Entity;
import com.gaohanghang.springbootesdemo.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/27 16:49
 */
@RestController
@RequestMapping("/entityController")
public class EntityController {
    @Autowired
    TestService testService;

    @GetMapping(value = "/save" )
    public String save(long id, String name) {
        System.out.println("save 接口");
        if(id > 0 && StringUtils.isNotEmpty(name)) {
            Entity newEntiy = new Entity(id, name);
            testService.saveEntity(newEntiy);
            return "OK";
        } else {
            return "Bad input value";
        }
    }


    @GetMapping("/search")
    public List<Entity> search(String name) {
        List<Entity> entityList = null;
        if (StringUtils.isNotEmpty(name)) {
            entityList = testService.searchEntity(name);
        }
        return entityList;
    }
}
