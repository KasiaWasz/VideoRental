package com.videorental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MOVIE_URL = "/movie-list";
    private static final String EMPLOYEE_EDIT_URL = "/employee-edit";
    private static final String SHIFT_EDIT_URL = "/shift-edit";
    private static final String EMPLOYEES_DETAILS_URL = "/employee-list/employees-details";
    private static final String EMPLOYEE_DETAIL_URL = "/employee-list/details";
    private final UserDetailsService customUserDetailService;


    @Autowired
    public SecurityConfig(UserDetailsService customUserDetailService) {

        Assert.notNull(customUserDetailService, "customUserDetailService must not be null");

        this.customUserDetailService = customUserDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(MOVIE_URL).permitAll()
                .antMatchers(EMPLOYEE_EDIT_URL, SHIFT_EDIT_URL, EMPLOYEES_DETAILS_URL, EMPLOYEE_DETAIL_URL).hasRole("MANAGER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
