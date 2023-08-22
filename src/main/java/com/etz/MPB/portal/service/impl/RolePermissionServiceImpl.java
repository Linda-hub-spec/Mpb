package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.dto.request.CreateRoleReq;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.dto.response.ResponseConstants;
import com.etz.MPB.portal.entity.Permissions;
import com.etz.MPB.portal.entity.Roles;
import com.etz.MPB.portal.repositories.PermissionRepository;
import com.etz.MPB.portal.repositories.RoleRepository;
import com.etz.MPB.portal.service.RolePermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.etz.MPB.portal.exceptions.Exception;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public BaseResponse createRole(CreateRoleReq createRoleReq, HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        if (roleRepository.existsByDescription(createRoleReq.getDescription())) {
            baseResponse.setResponseCode(String.valueOf(ResponseConstants.EXIST_CODE));
            baseResponse.setResponseMessage("Role description already exist");
            return baseResponse;
        }
        if (roleRepository.existsByName(createRoleReq.getName())) {
            baseResponse.setResponseCode(String.valueOf(ResponseConstants.EXIST_CODE));
            baseResponse.setResponseMessage("Role name already exist");
            return baseResponse;
        }
        Roles role = new Roles();
        role.setName(createRoleReq.getName());
        role.setDescription(createRoleReq.getDescription());
        role.setCreatedOn(LocalDateTime.now());
        role.setCreatedBy(0L);
        Set<Permissions> permissions = new HashSet<>();
        if (createRoleReq.getPermissions() != null) {
            createRoleReq.getPermissions().forEach(i -> {
                Optional<Permissions> permission = permissionRepository.findById(i);
                if (permission.isPresent()) permissions.add(permission.get());
            });
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
        baseResponse.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        baseResponse.setResponseMessage("Role has been created successfully.");
        return baseResponse;
    }

    @Override
    @Transactional
    public BaseResponse updateRole(Long id, CreateRoleReq createRoleRequest, HttpServletRequest request) {
        try {
            BaseResponse baseResponse = new BaseResponse();
            Optional<Roles> role = roleRepository.findById(id);
            if (!role.isPresent()) throw new Exception("Role not found");

            role.get().setName(createRoleRequest.getName());
            role.get().setDescription(createRoleRequest.getDescription());
            role.get().setUpdatedOn(LocalDateTime.now());
            Set<Permissions> permissions = new HashSet<>();
            role.get().setPermissions(permissions);

            createRoleRequest.getPermissions().forEach(i -> {
                Optional<Permissions> permOpt = permissionRepository.findById(i);
                if (permOpt.isPresent()) permissions.add(permOpt.get());
            });
            role.get().setPermissions(permissions);
            roleRepository.save(role.get());
            baseResponse.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
            baseResponse.setResponseMessage("Successfully processed");
            return baseResponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setResponseCode(String.valueOf(500));
            baseResponse.setResponseMessage("Update Failed");
            return baseResponse;
        }
    }

    @Override
    public BaseResponse queryRole(Long id, String name, Integer status, int number, int size, HttpServletRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        Pageable paging = PageRequest.of(number - 1,size);
        Page<Roles> rolesPage = roleRepository.queryRoles(id,name,status,paging);
        data.put("content", rolesPage.getContent());
        data.put("page", rolesPage.getNumber() + 1);
        data.put("size", rolesPage.getSize());
        data.put("totalElements", rolesPage.getTotalElements());
        data.put("totalPages", rolesPage.getTotalPages());
        data.put("first", rolesPage.isFirst());
        data.put("last", rolesPage.isLast());

        Long endTime = System.currentTimeMillis();
        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Successfully processed");
        response.setExecTime(endTime - startTime);
        response.setData(data);
        return response;
    }

    @Override
    public BaseResponse queryPermissions( int number, int size, HttpServletRequest request) {
        Long startTime = System.currentTimeMillis();
        BaseResponse<Object> response = new BaseResponse<>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        Pageable paging = PageRequest.of(number - 1,size);
        Page<Permissions> permissionsPage = permissionRepository.queryPermissions(paging);
        data.put("content", permissionsPage.getContent());
        data.put("page", permissionsPage.getNumber() + 1);
        data.put("size", permissionsPage.getSize());
        data.put("totalElements", permissionsPage.getTotalElements());
        data.put("totalPages", permissionsPage.getTotalPages());
        data.put("first", permissionsPage.isFirst());
        data.put("last", permissionsPage.isLast());

        Long endTime = System.currentTimeMillis();
        response.setResponseCode(String.valueOf(ResponseConstants.SUCCESS_CODE));
        response.setResponseMessage("Successfully processed");
        response.setExecTime(endTime - startTime);
        response.setData(data);
        return response;
    }
}
