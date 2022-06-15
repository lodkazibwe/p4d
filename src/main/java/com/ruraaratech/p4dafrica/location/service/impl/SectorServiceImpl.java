package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.SectorDao;
import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.model.Sector;
import com.ruraaratech.p4dafrica.location.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectorServiceImpl implements SectorService {
    @Autowired SectorDao sectorDao;

    @Transactional
    @Override
    public List<Sector> add(List<SectorRequest> requests, long districtId) {

        List<Sector> sectorList =new ArrayList<>();
        for(SectorRequest request: requests){
            String name =request.getName();
            boolean bool = sectorDao.existsByNameAndDistrictId(name, districtId);
            if(bool){
                throw new InvalidValuesException("sector already exists, name: "+name);
            }
            Sector sector =new Sector();
            sector.setEnabled(true);
            sector.setName(request.getName());
            sector.setDistrictId(districtId);
            sectorList.add(sector);
        }
        return sectorDao.saveAll(sectorList);
    }

    @Override
    public Sector get(long sectorId) {
        return sectorDao.findById(sectorId).orElseThrow(()->new ResourceNotFoundException("sector not found, ID: "+sectorId));
    }

    @Override
    public List<Sector> getAll(long districtId) {
        return sectorDao.findByDistrictId(districtId);
    }

    @Override
    public boolean existsById(long sectorId) {
        return sectorDao.existsById(sectorId);
    }
}
