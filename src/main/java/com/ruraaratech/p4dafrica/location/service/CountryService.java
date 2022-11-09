package com.ruraaratech.p4dafrica.location.service;

import com.ruraaratech.p4dafrica.location.dto.CountryRequest;
import com.ruraaratech.p4dafrica.location.model.Country;

import java.util.List;

public interface CountryService {
   List<Country> addCountry(List<CountryRequest> countryRequests);
   Country getCountry(long countryId);
   List<Country> getAll();
   boolean existsById(long countryId);
}
