package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Verifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verifications,Long> {
@Query(value = "SELECT v FROM Verifications v WHERE v.pensionerId = :pensionerId")
List<Verifications> findByPensionerId(Long pensionerId);
}
