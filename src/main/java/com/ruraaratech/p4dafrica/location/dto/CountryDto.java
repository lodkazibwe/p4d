package com.ruraaratech.p4dafrica.location.dto;

import com.ruraaratech.p4dafrica.location.model.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private long id;
    private String name;
    private boolean enabled;
    private List<District> districts;
}
