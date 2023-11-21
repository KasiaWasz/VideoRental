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

    private static final String MOVIE_LIST_URL = "/movie-list";
    private static final String MOVIE_EDIT_URL = "/movie-edit";
    private static final String CLIENT_LIST_URL = "/client-list";
    private static final String CLIENT_DETAIL_URL = "/client-list/details";
    private static final String CLIENT_DETAILS_URL = "/client-list/clients-details";
    private static final String CLIENT_EDIT_URL = "/client-edit";
    private static final String SHIFT_EDIT_URL = "/shift-edit";
    private static final String SHIFT_LIST_URL = "/shift-list";
    private static final String EMPLOYEE_EDIT_URL = "/employee-edit";
    private static final String EMPLOYEE_LIST_URL = "/employee-list";
    private static final String EMPLOYEES_DETAILS_URL = "/employee-list/employees-details";
    private static final String EMPLOYEE_DETAIL_URL = "/employee-list/details";
    private static final String RENTAL_LIST_URL = "/rental-list";
    private static final String RENTAL_FEE_LIST_URL = "/rental-fee-list";
    private static final String RENTAL_EDIT_URL = "/rental-edit";
    private static final String PAYROLL_LIST_URL = "/payroll-list";

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
                .antMatchers(MOVIE_LIST_URL).permitAll()
                .antMatchers(EMPLOYEE_EDIT_URL, SHIFT_EDIT_URL, EMPLOYEES_DETAILS_URL, EMPLOYEE_DETAIL_URL,
                        PAYROLL_LIST_URL)
                .hasRole("MANAGER")
                .antMatchers(EMPLOYEE_LIST_URL, SHIFT_LIST_URL, CLIENT_LIST_URL, CLIENT_DETAIL_URL,
                        CLIENT_DETAILS_URL, CLIENT_EDIT_URL, MOVIE_EDIT_URL, RENTAL_EDIT_URL,
                        RENTAL_LIST_URL, RENTAL_FEE_LIST_URL)
                .hasAnyRole("MANAGER", "KASJER")
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
