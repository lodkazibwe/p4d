package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.DistrictDao;
import com.ruraaratech.p4dafrica.location.dto.DistrictRequest;
import com.ruraaratech.p4dafrica.location.dto.DistrictResponse;
import com.ruraaratech.p4dafrica.location.model.District;
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
    SectorService sectorService;

    @Transactional
    @Override
    public List<District> add(List<DistrictRequest> requests, long countryId) {

        List<District> districtList =new ArrayList<>();
        for(DistrictRequest request:requests){
            String name =request.getName();
            boolean bool = districtDao.existsByNameAndCountryId(name, countryId);
            if(bool){
                throw new InvalidValuesException("district already exists, name: "+name);
            }
            District district =new District();
            district.setName(request.getName());
            district.setEnabled(true);
            district.setCountryId(countryId);
            districtList.add(district);
        }
        return districtDao.saveAll(districtList);
    }

    @Override
    public DistrictResponse get(long districtId) {
        District district =getById(districtId);
         return getDistrictResponse(district);
    }

    @Override
    public District getById(long districtId) {
        return districtDao.findById(districtId).orElseThrow(()->new ResourceNotFoundException("district not found, ID: "+districtId));
    }

    @Override
    public List<DistrictResponse> getAll(long countryId) {
        List<District> districtList =districtDao.findByCountryId(countryId);
        List<DistrictResponse> districtResponseList =new ArrayList<>();
        for(District district: districtList){
            districtResponseList.add(getDistrictResponse(district));
        }
        return districtResponseList;
    }

    private DistrictResponse getDistrictResponse(District district){
        DistrictResponse districtResponse =new DistrictResponse();
        districtResponse.setCountryId(district.getCountryId());
        districtResponse.setEnabled(district.isEnabled());
        districtResponse.setName(district.getName());
        districtResponse.setDistrictId(district.getId());
        districtResponse.setSectors(sectorService.getAll(district.getId()));
        return districtResponse;
    }

    @Override
    public boolean existsById(long districtId) {
        return districtDao.existsById(districtId);
    }
}
