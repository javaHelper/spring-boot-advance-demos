package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")//both admin and user is allowed to access resource
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasAnyAuthority('DELETE_AUTHORITY', 'UPDATE_AUTHORITY')")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from HomeController";
    }

}