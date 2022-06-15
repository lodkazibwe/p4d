package com.ruraaratech.p4dafrica.sysUser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    @Id
    @GeneratedValue
    private long userId;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    private String userStatus;
    @OneToMany(targetEntity = Role.class,cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn()
    private Set<Role> roles;
    private String updatedBy;
    private String addedBy;
    private Date dateAdded;
    private Date dateUpdated;
       }
