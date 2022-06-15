package com.ruraaratech.p4dafrica.location.dao;

import com.ruraaratech.p4dafrica.location.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorDao extends JpaRepository<Sector, Long> {

    boolean existsByNameAndDistrictId(String name, long districtId);

    List<Sector> findByDistrictId(long districtId);

}
