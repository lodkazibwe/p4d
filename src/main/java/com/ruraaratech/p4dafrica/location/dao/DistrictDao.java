package com.ruraaratech.p4dafrica.location.dao;

import com.ruraaratech.p4dafrica.location.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictDao extends JpaRepository<District, Long> {
    boolean existsByNameAndCountryId(String name, long countryId);

    List<District> findByCountryId(long countryId);

}
