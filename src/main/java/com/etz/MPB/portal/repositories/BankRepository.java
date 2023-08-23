package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Banks;
import com.etz.MPB.portal.entity.Ranks;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface BankRepository extends JpaRepository<Banks, Long> {
    @Query(
            value = "SELECT r FROM Banks r WHERE " +
                    "(r.id = :id OR (:id IS NULL)) AND " +
                    "(r.name = :name OR (:name IS NULL)) AND "  +
                    "(r.code = :code OR (:code IS NULL))"
    )
    Page<Banks> findBanks(Long id, String name, String code, Pageable paging);
}
