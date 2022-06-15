package com.ruraaratech.p4dafrica.Document.dao;

import com.ruraaratech.p4dafrica.Document.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanDao extends JpaRepository<Plan, Long> {
}
