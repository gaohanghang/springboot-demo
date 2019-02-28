package com.gaohanghang.springbootesdemo.entity;

import java.io.Serializable;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/27 16:50
 */
public class Entity implements Serializable {

    private static final long serialVersionUID = -763638353551774166L;

    public static final String INDEX_NAME = "index_entity";

    public static final String TYPE = "tstype";

    private Long id;

    private String name;

    public Entity() {
        super();
    }

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
