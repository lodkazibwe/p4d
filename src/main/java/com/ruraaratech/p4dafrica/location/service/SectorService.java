package com.ruraaratech.p4dafrica.location.service;

import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.dto.SectorResponse;
import com.ruraaratech.p4dafrica.location.model.Sector;

import java.util.List;

public interface SectorService {
    List<Sector>  add(List<SectorRequest> request, long districtId);
    Sector get(long sectorId);
    List<Sector> getByDistrict(long districtId);
    List<SectorResponse> getAll(long districtId);
    boolean existsById(long sectorId);

}
