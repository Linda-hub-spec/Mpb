package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.PropertyConfig;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface PropertyConfigRepository extends JpaRepository<PropertyConfig, Long> {


}
