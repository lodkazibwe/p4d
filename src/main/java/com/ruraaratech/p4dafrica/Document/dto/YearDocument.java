package com.ruraaratech.p4dafrica.Document.dto;

import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.location.model.Sector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearDocument {
    private Sector sector;
    private Plan plan;
    private Budget budget;
}
