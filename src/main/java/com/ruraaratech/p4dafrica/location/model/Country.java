package com.ruraaratech.p4dafrica.location.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    @JsonIgnoreProperties("country")
    @OneToMany(mappedBy = "country", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private List<District> districts;
    private boolean enabled;

}
