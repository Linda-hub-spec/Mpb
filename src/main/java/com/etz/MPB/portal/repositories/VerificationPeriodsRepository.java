package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.VerificationPeriods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationPeriodsRepository extends JpaRepository<VerificationPeriods,Long> {

}
