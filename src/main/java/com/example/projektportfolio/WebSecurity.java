package com.example.projektportfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginHandler loginHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin").authenticated()
                .antMatchers("/admin/**").authenticated()
//                .antMatchers("admin").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(loginHandler)
//                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

//        httpSecurity.exceptionHandling().accessDeniedPage("/403")
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //zmienić na jdbc authentication
        auth.inMemoryAuthentication()
                .withUser("przemo").password("test1234").roles("ADMIN")
                .and()
                .withUser("parashut").password("1234test").roles("USER");
    }
}
