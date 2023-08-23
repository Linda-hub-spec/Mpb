package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Roles;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Roles, Long> {
    boolean existsByName(String name);

    boolean existsByDescription(String name);


    @Query(
            value = "SELECT t FROM Roles t WHERE (t.id =:id OR :id IS NULL) AND (t.name LIKE %:name% OR :name IS NULL) AND (t.status =:status OR :status IS NULL)"
    )
    Page<Roles> queryRoles(Long id, String name, Integer status, Pageable paging);
}