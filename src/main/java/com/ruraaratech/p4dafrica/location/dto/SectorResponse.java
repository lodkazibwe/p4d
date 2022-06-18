package com.ruraaratech.p4dafrica.location.dto;

import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorResponse {
    private long id;
    private String name;
    private boolean enabled;
    private long districtId;
    private List<Plan> plans;
    private List<Budget> budgets;
}
