package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.DistrictDao;
import com.ruraaratech.p4dafrica.location.dto.DistrictRequest;
import com.ruraaratech.p4dafrica.location.dto.DistrictResponse;
import com.ruraaratech.p4dafrica.location.model.Country;
import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.service.CountryService;
import com.ruraaratech.p4dafrica.location.service.DistrictService;
import com.ruraaratech.p4dafrica.location.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired DistrictDao districtDao;
    @Autowired
    CountryService countryService;

    @Transactional
    @Override
    public List<District> add(List<DistrictRequest> requests, long countryId) {
        Country country =countryService.getCountry(countryId);
        List<District> districtList =new ArrayList<>();
        for(DistrictRequest request:requests){
            String name =request.getName();
            boolean bool = districtDao.existsByNameAndCountry(name, country);
            if(bool){
                throw new InvalidValuesException("district already exists, name: "+name);
            }
            District district =new District();
            district.setName(request.getName());
            district.setEnabled(true);
            district.setCountry(country);
            districtList.add(district);
        }
        return districtDao.saveAll(districtList);
    }

    @Override
    public District get(long districtId) {
        return districtDao.findById(districtId).orElseThrow(()->new ResourceNotFoundException("district not found, ID: "+districtId));

    }

    @Override
    public List<District> getAll(long countryId) {
        Country country =countryService.getCountry(countryId);
        return districtDao.findByCountry(country);
    }


    @Override
    public boolean existsById(long districtId) {
        return districtDao.existsById(districtId);
    }

    @Override
    public List<District> getAll() {
        return districtDao.findAll();
    }
}
