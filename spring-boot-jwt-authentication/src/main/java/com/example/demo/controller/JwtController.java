package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.UserModel;
import com.example.demo.service.CustomUserDetailService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserModel userModel){
      UserModel userModel1 = customUserDetailService.register(userModel);
      ResponseEntity<UserModel> re = new ResponseEntity<>(userModel1, HttpStatus.CREATED);
      return re;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest){

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword());
        //authenticate the user
        authenticationManager.authenticate(upat);

        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        String jwtToken = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(jwtToken);
        //return ResponseEntity.ok(jwtResponse);
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);

    }

    @GetMapping("/currentUser")
    public UserModel getCurrentUser(Principal principal) {
        UserDetails userDetails =  this.customUserDetailService.loadUserByUsername(principal.getName());
        return (UserModel) userDetails;
    }
}