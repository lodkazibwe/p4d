package com.ruraaratech.p4dafrica.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sector {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    private boolean enabled;
    private long districtId;
}
