package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.ValidVerifications;
import com.etz.MPB.portal.entity.Verifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidVerificationRepository extends JpaRepository<ValidVerifications,Long> {
    @Query(value = "SELECT v FROM ValidVerifications v WHERE v.verificationId = :id")
    ValidVerifications findByVerificationId(Long id);
}
