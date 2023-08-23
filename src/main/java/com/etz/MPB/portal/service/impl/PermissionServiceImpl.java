package com.etz.MPB.portal.service.impl;

import com.etz.MPB.portal.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(authority -> permission.equals(authority.getAuthority()));
    }
}
