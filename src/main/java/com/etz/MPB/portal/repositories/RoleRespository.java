package com.etz.MPB.portal.repositories;

import com.etz.MPB.portal.entity.Roles;

import org.javers.spring.annotation.JaversSpringDataAuditable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface RoleRespository extends JpaRepository<Roles, Long> {

}