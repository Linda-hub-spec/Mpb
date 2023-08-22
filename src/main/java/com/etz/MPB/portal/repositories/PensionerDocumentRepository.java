package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.PensionerDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PensionerDocumentRepository extends JpaRepository<PensionerDocuments,Long> {
    @Query(value = "select p.url from PensionerDocuments where p.pensionerId = :pensionerId and (p.createdOn between :startDate and :endDate) and p.documentType = 3 ")
    String findUrl(Date startDate, Date endDate,Long pensionerId);
}
