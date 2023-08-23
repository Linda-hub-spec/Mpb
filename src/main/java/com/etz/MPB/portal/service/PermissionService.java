package com.etz.MPB.portal.service;
import org.springframework.security.core.Authentication;

public interface PermissionService {
    boolean hasPermission(String permission);
}
