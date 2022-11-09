package com.ruraaratech.p4dafrica.location.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sector {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean enabled;
    @ManyToOne
    private District district;
    @JsonIgnoreProperties("sector")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sector", cascade= CascadeType.ALL)
    private List<Plan> plans;
    @JsonIgnoreProperties("sector")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sector", cascade= CascadeType.ALL)
    private List<Budget> budgets;
}
