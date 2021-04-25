package com.evan.springcloud.controller;

import com.evan.springcloud.pojo.Dept;
import com.evan.springcloud.service.DeptClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept/")
public class DeptController {

    private static final String URL = "http://localhost:8001/dept";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeptClientService service;

    @RequestMapping("/add")
    public boolean addDept(Dept dept) throws JsonProcessingException {
//        String deptStr = new ObjectMapper().writeValueAsString(dept);
        String url = URL+"/add";
        return restTemplate.postForObject(url, dept, Boolean.class);
//        return this.service.addDept(dept);
    }

    @RequestMapping("/get/{id}")
    public Dept queryById(@PathVariable Long id) {
        String url = URL+"/get/"+id;
        return restTemplate.getForObject(url, Dept.class);
//        return this.service.queryById(id);
    }

    @RequestMapping("/getAll")
    public List<Dept> queryAll() {
        String url = URL+"/getAll";
        return restTemplate.getForObject(url,List.class);
//        return this.service.queryAll();
    }
}
