package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Pensioners;
import com.etz.MPB.portal.entity.Ranks;
import com.etz.MPB.portal.enums.PensionerStatus;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@JaversSpringDataAuditable
public interface PensionerRepository extends JpaRepository<Pensioners, Long> {
    @Query(
            value = "SELECT p FROM Pensioners p WHERE " +
                    "(p.id = :id OR (:id IS NULL)) AND " +
                    "(p.authorized = :authorized OR (:authorized IS NULL)) AND "  +
                    "(p.createdOn >= :createdDateFrom OR (:createdDateFrom IS NULL)) AND "+
            "(p.createdOn <= :createdDateTo OR (:createdDateTo IS NULL)) AND "+
                    "('%:name%' IS NULL OR (CONCAT(p.lastName,' ',p.firstName,' ',p.otherName) LIKE '%:name%')) AND "+
                    "('%:email%' IS NULL OR (p.email LIKE '%:email%')) AND "+
                    "('%:phone%' IS NULL OR (p.phone LIKE '%:phone%')) AND "+
                    "('%:status%' IS NULL OR (p.status LIKE '%:status%')) "
    )
    Page<Pensioners> findPensioner(Boolean authorized, Date createdDateFrom, Date createdDateTo, String name, String email,
                                   String phone, PensionerStatus status, Long id, Pageable paging);
}
