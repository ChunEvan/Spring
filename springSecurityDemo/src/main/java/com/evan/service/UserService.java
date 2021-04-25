package com.evan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map map = new HashMap();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        String password = bCryptPasswordEncoder.encode("123");
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("persission_A"));
        list.add(new SimpleGrantedAuthority("persission_B"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        User userDetails = new User(username,password,list);

        return userDetails;
    }
}
