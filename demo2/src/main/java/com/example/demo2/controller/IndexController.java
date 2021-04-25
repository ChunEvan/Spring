package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into user (name, age, sex) values ('evan', 30, 1)";
        int update = jdbcTemplate.update(sql);
        return Integer.toString(update);
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update user set name=?, age=? where id="+id;
        int update = jdbcTemplate.update(sql, "evan02", 13);
        return Integer.toString(update);
    }

    @GetMapping("/deleteUser/{id}")
    @Transactional
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from user where id=";
        int update = jdbcTemplate.update(sql, id);
        return Integer.toString(update);
    }
}
