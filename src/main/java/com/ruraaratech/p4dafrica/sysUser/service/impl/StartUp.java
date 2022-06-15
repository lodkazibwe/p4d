package com.ruraaratech.p4dafrica.sysUser.service.impl;

import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import com.ruraaratech.p4dafrica.sysUser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;


@Component
public class StartUp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired UserService userService;
    private final Logger logger = LoggerFactory.getLogger(StartUp.class);
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("system Started...");
        logger.info("checking user...");
        boolean bool =userService.existsByUserName("P4D");
        if(bool){

            logger.info("system already initiated...");
        }else{
            SysUser sysUser =new SysUser();
            sysUser.setDateAdded(new Date());
            sysUser.setEmail("secretariat@p4dafrica.com");
            sysUser.setUserName("P4D");
            sysUser.setUserStatus("Active");
            sysUser.setAddedBy("sys");
            sysUser.setContact("255685068666");
            sysUser.setDateUpdated(new Date());
            sysUser.setUpdatedBy("sys");
            userService.addAdmin(sysUser);
            logger.info("user added...");
        }
        logger.info("system ready...");
    }
}
