package com.ruraaratech.p4dafrica.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class District {
    @Id
    @GeneratedValue
    private long Id;
    @Column(unique = true)
    private String name;
    private long countryId;
    private boolean enabled;
}
