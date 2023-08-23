package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.request.CreateRoleReq;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.service.RolePermissionService;
import com.etz.MPB.portal.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RolePermissionService rolePermissionService;

    @PostMapping("")
    @PreAuthorize("@permissionService.hasPermission('CREATE ROLES')")
    public ResponseEntity<?> createRole(@RequestBody CreateRoleReq createRoleReq,  HttpServletRequest request) throws Exception {
        BaseResponse response = rolePermissionService.createRole(createRoleReq, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@permissionService.hasPermission('UPDATE ROLES')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody CreateRoleReq createRoleReq,  HttpServletRequest request) throws Exception {
        BaseResponse response = rolePermissionService.updateRole(id, createRoleReq, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@permissionService.hasPermission('VIEW ROLES')")
    @GetMapping("")
    public ResponseEntity<?> queryRole(@RequestParam(required = false) Long id,
                                       @RequestParam (required = false) String name,
                                       @RequestParam(required = false) Integer status,
                                       @RequestParam(required = false, defaultValue = "1") int number,
                                       @RequestParam(required = false, defaultValue = "10") int size,
                                       HttpServletRequest request) throws Exception {

        BaseResponse response = rolePermissionService.queryRole(id, name,status ,number,size,request);
        return ResponseEntity.ok(response);
    }




}
