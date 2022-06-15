package com.ruraaratech.p4dafrica.sysUser.service.impl;

import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.sysUser.dao.UserDAO;
import com.ruraaratech.p4dafrica.sysUser.model.Role;
import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import com.ruraaratech.p4dafrica.sysUser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public SysUser contactSignUp(String contact) {
        logger.info("saving and sending email..");
        return genNewSysUser(contact);

    }

    @Override
    public SysUser emailSignUp(String email) {
        logger.info("saving and sending sms..");
        return genNewSysUser(email);
    }

    @Override
    public SysUser addAdmin(SysUser sysUser) {
        sysUser.setRoles(setRole("ADMIN"));
        sysUser.setPassword(passwordEncoder.encode("admin@P4D"));
        return userDAO.save(sysUser);
    }

    private SysUser genNewSysUser(String userName) {
        SysUser sysUser =new SysUser();
        sysUser.setEmail(userName);
        sysUser.setUserName(userName);
        sysUser.setUserStatus("NEW");
        sysUser.setDateAdded(new Date());
        sysUser.setRoles(setRole("BUYER"));
        sysUser.setPassword(passwordEncoder.encode("SH001"));
        return userDAO.save(sysUser);
    }

    @Override
    public SysUser getUser(long userId) {
        return userDAO.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No such user With ID: " +userId));
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userDAO.existsByUserName(userName);
    }

    @Override
    public List<SysUser> getAll() {
        return userDAO.findAll();
    }

    @Override
    public SysUser addRole(long userId, String role) {
        logger.info("getting user ..."+ userId);
        SysUser sysUser=getUser(userId);
        logger.info("adding role ..."+ role);
        sysUser.getRoles().add(new Role(role));
        logger.info("saving...");
        return userDAO.save(sysUser);
    }

    private Set<Role> setRole(String rol){
        logger.info("adding Role ..."+ rol);
        Role role = new Role(rol);
        return new HashSet<>(Collections.singletonList(role));
    }

}
