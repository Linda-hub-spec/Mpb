package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.PropertyConfig;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PropertyConfigRepository extends JpaRepository<PropertyConfig, Long> {


}
