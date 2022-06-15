package com.ruraaratech.p4dafrica.sysUser.service;


import com.ruraaratech.p4dafrica.sysUser.model.SysUser;

import java.util.List;

public interface UserService {
    SysUser contactSignUp(String contact);
    SysUser emailSignUp(String email);
    SysUser addAdmin(SysUser sysUser);
    SysUser getUser(long userId);
    SysUser addRole(long userId, String role);
    List<SysUser> getAll();
    boolean existsByUserName(String userName);
}
