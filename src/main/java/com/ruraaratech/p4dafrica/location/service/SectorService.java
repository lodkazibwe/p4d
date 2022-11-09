package com.ruraaratech.p4dafrica.location.service;

import com.ruraaratech.p4dafrica.Document.dto.YearDocument;
import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.model.Sector;

import java.util.List;

public interface SectorService {
    List<Sector>  add(List<SectorRequest> request, long districtId);
    Sector get(long sectorId);
    List<Sector> getAll(long districtId);
    List<YearDocument> getAll(long districtId, int year);
    List<Sector> getAll();
    boolean existsById(long sectorId);

}
