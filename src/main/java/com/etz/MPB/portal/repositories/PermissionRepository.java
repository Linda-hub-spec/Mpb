package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Permissions;
import com.etz.MPB.portal.entity.Roles;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
    @Query(value = "SELECT * FROM permissions WHERE status = 0", nativeQuery = true)
    Page<Permissions> queryPermissions(Pageable paging);
}
