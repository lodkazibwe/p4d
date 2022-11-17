package com.ruraaratech.p4dafrica.location.dao;

import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorDao extends JpaRepository<Sector, Long> {

    boolean existsByNameAndDistrict(String name, District district);

    List<Sector> findByDistrict(District district);

    @Query("select s from Sector s left join s.plans pl where s.district = ?1 and pl.year= ?2")
    List<Sector> findByDistrictAndYear(District district, int year);
}
