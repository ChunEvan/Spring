package com.evan.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.evan.service.HelloService;
import org.springframework.transaction.annotation.Transactional;

//@Service(protocol = "dubbo")
//@Service
//@Service(loadbalance = "random")
@Service(interfaceClass = HelloService.class, loadbalance = "random")
@Transactional
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello "+ name;
    }
}
