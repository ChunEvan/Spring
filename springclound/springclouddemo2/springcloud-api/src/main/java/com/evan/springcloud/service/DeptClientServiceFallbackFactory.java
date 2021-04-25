package com.evan.springcloud.service;

import com.evan.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id).setDname("not fund").setDb_source("no database");
            }

            @Override
            public Dept queryAll() {
                return null;
            }

            @Override
            public Dept addDept(Dept dept) {
                return null;
            }
        };
    }
}
