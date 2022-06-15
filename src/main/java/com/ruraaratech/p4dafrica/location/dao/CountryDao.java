package com.ruraaratech.p4dafrica.location.dao;

import com.ruraaratech.p4dafrica.location.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository<Country, Long> {
    boolean existsByName(String name);
}
