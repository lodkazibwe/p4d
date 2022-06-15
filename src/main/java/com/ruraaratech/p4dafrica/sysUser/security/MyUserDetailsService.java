package com.ruraaratech.p4dafrica.sysUser.security;

import com.ruraaratech.p4dafrica.sysUser.dao.UserDAO;
import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user =getUser(userName);
        MyUserDetails userDetails=new MyUserDetails(user);
        if(user !=null){
            return userDetails;

        }else{
            throw new UsernameNotFoundException("User does not exist : " + userName);
        }
    }

    public SysUser getUser(String userName){
        //if username=email find by email, else =contact, else userName
        return userDAO.findByUserName(userName);
    }


    public SysUser currentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName=auth.getName();
        return  getUser(userName);
    }
}
