package com.evan.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvanRule {

    public IRule myRule(){
        return new EvanRandomRule();
    }
}
