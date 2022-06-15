package com.ruraaratech.p4dafrica.location.service.impl;

import com.ruraaratech.p4dafrica.exceptions.InvalidValuesException;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
import com.ruraaratech.p4dafrica.location.dao.CountryDao;
import com.ruraaratech.p4dafrica.location.dto.CountryRequest;
import com.ruraaratech.p4dafrica.location.dto.CountryResponse;
import com.ruraaratech.p4dafrica.location.model.Country;
import com.ruraaratech.p4dafrica.location.service.CountryService;
import com.ruraaratech.p4dafrica.location.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired CountryDao countryDao;
    @Autowired DistrictService districtService;

    @Transactional
    @Override
    public List<Country> addCountry(List<CountryRequest> countryRequests) {
        List<Country> countryList =new ArrayList<>();
        for(CountryRequest request: countryRequests){
            String name =request.getName();
            boolean bool = countryDao.existsByName(name);
            if(bool){
                throw new InvalidValuesException("country already exists, name: "+name);
            }
            Country country =new Country();
            country.setEnabled(true);
            country.setName(request.getName());
            countryList.add(country);
        }
        return countryDao.saveAll(countryList);
    }

    @Override
    public CountryResponse getCountry(long countryId) {
        CountryResponse countryResponse =new CountryResponse();
        Country country =countryDao.findById(countryId).orElseThrow(()->new ResourceNotFoundException("country not found, ID: "+countryId));
        countryResponse.setEnabled(country.isEnabled());
        countryResponse.setCountryId(country.getId());
        countryResponse.setName(country.getName());
        countryResponse.setDistricts(districtService.getAll(countryId));
        return countryResponse;
    }

    @Override
    public List<Country> getAll() {
        return countryDao.findAll();
    }

    @Override
    public boolean existsById(long countryId) {
        return countryDao.existsById(countryId);
    }
}
