package com.ruraaratech.p4dafrica.location.service;

import com.ruraaratech.p4dafrica.location.dto.DistrictRequest;
import com.ruraaratech.p4dafrica.location.dto.DistrictResponse;
import com.ruraaratech.p4dafrica.location.model.District;

import java.util.List;

public interface DistrictService {
     List<District> add(List<DistrictRequest> request, long countryId);
     District get(long districtId);
     List<District> getAll(long countryId);
     List<District> getAll();
     boolean existsById(long districtId);
}
