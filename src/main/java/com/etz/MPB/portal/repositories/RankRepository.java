package com.etz.MPB.portal.repositories;


import com.etz.MPB.portal.entity.Ranks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankRepository extends JpaRepository<Ranks, Long> {
    @Query(
            value = "SELECT r FROM Ranks r WHERE " +
                    "(r.id = :id OR (:id IS NULL)) AND " +
                    "(r.name = :name OR (:name IS NULL)) AND "  +
                    "(r.code = :code OR (:code IS NULL))"
    )
    Page<Ranks> findRanks(Long id, String name,String code,Pageable paging);
}
