package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.Document.service.BudgetService;
import com.ruraaratech.p4dafrica.Document.service.PlanService;
import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.SectorDao;
import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.dto.SectorResponse;
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
    //@Autowired PlanService planService;
    //@Autowired BudgetService budgetService;

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
            sector.setName(name);
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
    public List<SectorResponse> getAll(long districtId) {
        List<Sector> sectors =sectorDao.findByDistrictId(districtId);
        List<SectorResponse> responseList =new ArrayList<>();
        for(Sector sector: sectors){
            SectorResponse response =new SectorResponse();
            response.setDistrictId(sector.getDistrictId());
            response.setEnabled(sector.isEnabled());
            response.setId(sector.getId());
            response.setName(sector.getName());
            //response.setBudgets(budgetService.getAll(sector.getId()));
            //response.setPlans(planService.getAll(sector.getId()));
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public List<Sector> getByDistrict(long districtId) {
        return sectorDao.findByDistrictId(districtId);
    }

    @Override
    public boolean existsById(long sectorId) {
        return sectorDao.existsById(sectorId);
    }

    @Override
    public List<Sector> getAll() {
        return sectorDao.findAll();
    }
}
