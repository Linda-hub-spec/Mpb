package com.etz.MPB.portal.service;

import com.etz.MPB.portal.dto.request.CreateRoleReq;
import com.etz.MPB.portal.dto.response.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public interface RolePermissionService {
    BaseResponse createRole(CreateRoleReq createRoleReq, HttpServletRequest request);

    BaseResponse updateRole(Long id, CreateRoleReq createRoleReq, HttpServletRequest request);

    BaseResponse queryRole(Long id, String name, Integer status, int number, int size, HttpServletRequest request);

    BaseResponse queryPermissions( int number, int size, HttpServletRequest request);
}
