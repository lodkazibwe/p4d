package com.ruraaratech.p4dafrica.sysUser.dao;

import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<SysUser, Long> {

    SysUser findByUserName(String userName);

    boolean existsByUserName(String userName);
}
