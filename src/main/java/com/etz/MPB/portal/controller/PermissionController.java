package com.etz.MPB.portal.controller;

import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/apis/permissions")
public class PermissionController {

    @Autowired
    RolePermissionService rolePermissionService;

    @GetMapping("/")
    public ResponseEntity<?> queryPermission(@RequestParam Long id,
                                       @RequestParam String name,
                                       @RequestParam int status,
                                       @RequestParam(required = false, defaultValue = "1") int number,
                                       @RequestParam(required = false, defaultValue = "10") int size,
                                       HttpServletRequest request) throws Exception {

        BaseResponse response = rolePermissionService.queryPermissions(number,size,request);
        return ResponseEntity.ok(response);
    }
}
