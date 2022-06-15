package com.ruraaratech.p4dafrica.sysUser.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    MyUserDetailsService myUserDetailsService;

   @Autowired
   AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("getToken")
    public ResponseEntity<AuthResponse> Authenticate(
            @RequestBody @Valid AuthRequest authRequest){
        return new ResponseEntity<>(authService.authenticate(authRequest), HttpStatus.OK);
    }

    }


