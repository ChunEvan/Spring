package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
//                .and()
//                .withUser("vip").password("1234").roles("vip1")
//                .and()
//                .withUser("guest").password("123").roles("guest");
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
//                .and()
//                .withUser("vip").password("1234").roles("vip1")
//                .and()
//                .withUser("guest").password("123").roles("guest");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        System.out.println(http);
//        http.authorizeRequests()
//                .antMatchers("/").permitAll();
//                .antMatchers("/level1/**").hasRole("vip1")
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3");

//        http.csrf().disable();
//        http.rememberMe();
//        http.formLogin().loginPage("/toLogin");

//        http.logout().deleteCookies("cookie").logoutSuccessUrl("/") ;


//        http.apply(new AbstractHttpConfigurer<T, HttpSecurity>() {
//            private boolean flag;
//
//            @Override
//            public void init(HttpSecurity builder) throws Exception {
//                super.init(builder);
//            }
//
//            @Override
//            public void configure(HttpSecurity builder) throws Exception {
//                super.configure(builder);
//            }
//            public AbstractHttpConfigurer flag(boolean flag){
//                this.flag=flag;
//                return this;
//            }
//        }).and();



    }
}
