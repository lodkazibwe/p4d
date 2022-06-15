package com.ruraaratech.p4dafrica.Document.dao;

import com.ruraaratech.p4dafrica.Document.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetDao extends JpaRepository<Budget, Long> {
    List<Budget> findBySector(long sectorId);

}
