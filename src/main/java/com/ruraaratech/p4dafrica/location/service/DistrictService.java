package com.ruraaratech.p4dafrica.location.service;

import com.ruraaratech.p4dafrica.location.dto.DistrictRequest;
import com.ruraaratech.p4dafrica.location.dto.DistrictResponse;
import com.ruraaratech.p4dafrica.location.model.District;

import java.util.List;

public interface DistrictService {
     List<District> add(List<DistrictRequest> request, long countryId);
     DistrictResponse get(long districtId);
     District getById(long districtId);
     List<District> getByCountry(long countryId);
     List<DistrictResponse> getAll(long countryId);
     List<District> getAll();
     boolean existsById(long districtId);
}
