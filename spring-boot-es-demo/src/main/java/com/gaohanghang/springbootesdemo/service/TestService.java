package com.gaohanghang.springbootesdemo.service;

import com.gaohanghang.springbootesdemo.entity.Entity;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/27 16:50
 */
public interface TestService {
    void saveEntity(Entity entity);

    void saveEntity(List<Entity> entityList);

    List<Entity> searchEntity(String searchContent);
}
