package com.ruraaratech.p4dafrica.Document.dao;

import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetDao extends JpaRepository<Budget, Long> {
    List<Budget> findBySector(long sectorId);



   /* @Query("select b from Budget b "
            + "inner join b.sector w"
            + "where w.district=district "
            + "order by b.year asc")*/
   @Query("select u from Budget u inner join u.sector ar where ar.district = :district")
    List<Budget> findByDistrict(District district);

    Budget findBySectorAndYear(Sector sector, int year);
    List<Budget> findAllByOrderByDateCreatedDesc();
}
