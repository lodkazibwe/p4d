package com.ruraaratech.p4dafrica.Document.dao;

import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.location.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanDao extends JpaRepository<Plan, Long> {
    List<Plan> findBySector(Sector sector);

    Plan findBySectorAndYear(Sector sector, int year);

    List<Plan> findAllByOrderByDateCreatedDesc();

}
