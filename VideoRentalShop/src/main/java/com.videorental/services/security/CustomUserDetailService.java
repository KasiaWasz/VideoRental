package com.videorental.services.security;

import com.videorental.entities.employee.Employee;
import com.videorental.queries.employee.EmployeeQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final EmployeeQueries employeeQueries;


    @Autowired
    public CustomUserDetailService(EmployeeQueries employeeQueries) {

        Assert.notNull(employeeQueries, "employeeQueries must not be null");

        this.employeeQueries = employeeQueries;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Assert.notNull(email, "email must not be null");

        Optional<Employee> employeeOptional = employeeQueries.findByEmail(email);

        Employee employee = employeeOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email)
        );

//        String employeeRole = "ROLE_" + employee.getRole().toString();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .roles(employee.getRole().toString())
                .build();

        System.out.println("User roles: " + userDetails.getAuthorities());

        return userDetails;
    }
}
