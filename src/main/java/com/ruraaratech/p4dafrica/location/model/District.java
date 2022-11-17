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
public class District {
    @Id
    @GeneratedValue
    private long Id;
    private String name;
    @JsonIgnoreProperties("districts")
    @ManyToOne
    private Country country;
    @JsonIgnoreProperties("district")
    @OneToMany(mappedBy = "district", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Sector> sectors;
    private boolean enabled;
}
