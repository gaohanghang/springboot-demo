package cn.itcast.web;

import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2018/12/02 3:30 PM
 */
@Slf4j
@RestController
@RequestMapping("user")
public class HelloController {

    // @Autowired
    // private DataSource dataSource;

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User hello(@PathVariable("id") Long id) {
        System.out.println("hello method is running");
        return userService.queryById(id);
    }
}
