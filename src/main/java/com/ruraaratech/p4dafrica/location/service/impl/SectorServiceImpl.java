package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.Document.dto.YearDocument;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.SectorDao;
import com.ruraaratech.p4dafrica.location.dto.SectorRequest;
import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.model.Sector;
import com.ruraaratech.p4dafrica.location.service.DistrictService;
import com.ruraaratech.p4dafrica.location.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectorServiceImpl implements SectorService {
    @Autowired SectorDao sectorDao;
    @Autowired
    DistrictService districtService;

    @Transactional
    @Override
    public List<Sector> add(List<SectorRequest> requests, long districtId) {
        District district =districtService.get(districtId);
        List<Sector> sectorList =new ArrayList<>();
        for(SectorRequest request: requests){
            String name =request.getName();
            boolean bool = sectorDao.existsByNameAndDistrict(name, district);
            if(bool){
                throw new InvalidValuesException("sector already exists, name: "+name);
            }
            Sector sector =new Sector();
            sector.setEnabled(true);
            sector.setName(name);
            sector.setDistrict(district);
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
        District district =districtService.get(districtId);
        return sectorDao.findByDistrict(district);
    }

    @Override
    public boolean existsById(long sectorId) {
        return sectorDao.existsById(sectorId);
    }

    @Override
    public List<Sector> getAll() {
        return sectorDao.findAll();
    }

    @Override
    public List<YearDocument> getAll(long districtId, int year) {
        List<YearDocument> yearDocuments =new ArrayList<>();
        District district =districtService.get(districtId);
        List<Sector> sectorList= sectorDao.findByDistrict(district);
        for(Sector sector: sectorList){
            YearDocument yearDocument =new YearDocument();
            yearDocument.setSector(sector);
            Plan plan =sector.getPlans().stream().filter(p->p.getYear()==year).findFirst().orElse(new Plan());
            Budget budget =sector.getBudgets().stream().filter(p->p.getYear()==year).findFirst().orElse(new Budget());
            yearDocument.setBudget(budget);
            yearDocument.setPlan(plan);
            yearDocuments.add(yearDocument);
        }
        return yearDocuments;
    }
}
