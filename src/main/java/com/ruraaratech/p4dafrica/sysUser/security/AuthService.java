package com.ruraaratech.p4dafrica.sysUser.security;

import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    public String getJwt(AuthRequest authRequest){
        try {
            logger.info("authenticating....");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception e) {
            logger.info("invalid user name or pass....");
            throw new InvalidValuesException("Incorrect name or password");

        }
        logger.info("getting user details....");
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
        logger.info("getting token....");
        logger.info("authenticated....");
        return jwtUtil.generateToken(userDetails);

    }

    public boolean checkUser(AuthRequest authRequest){
        try {
            logger.info("checking auth....");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception e) {
            logger.info("invalid pass....");
            throw new InvalidValuesException("Incorrect password");

        }
        return true;
    }

    public AuthResponse authenticate(AuthRequest authRequest){
        String jwt =getJwt(authRequest);
        SysUser user =myUserDetailsService.getUser(authRequest.getUserName());
         return new AuthResponse(jwt, user.getUserId(), user.getUserName());
    }

}
