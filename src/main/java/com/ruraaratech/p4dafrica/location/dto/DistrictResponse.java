package com.ruraaratech.p4dafrica.location.dto;

import com.ruraaratech.p4dafrica.location.model.Sector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponse {
    private long districtId;
    private String name;
    private long countryId;
    private boolean enabled;
    List<Sector> sectors;
}
