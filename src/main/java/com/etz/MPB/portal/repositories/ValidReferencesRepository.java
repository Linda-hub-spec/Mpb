package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Users;
import com.etz.MPB.portal.entity.ValidReferences;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ValidReferencesRepository extends JpaRepository<ValidReferences, Long> {
    @Query("SELECT v FROM ValidReferences v WHERE v.verificationReference = :reference")
    Optional<ValidReferences> findByReference(@Param("reference") String reference);
}
