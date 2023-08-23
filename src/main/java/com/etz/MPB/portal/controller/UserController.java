package com.etz.MPB.portal.controller;


import com.etz.MPB.portal.dto.request.UserReq;
import com.etz.MPB.portal.dto.response.BaseResponse;
import com.etz.MPB.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("@permissionService.hasPermission('CREATE USERS')")
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserReq userReq) throws Exception {
        BaseResponse res = userService.createUser(userReq);
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("@permissionService.hasPermission('UPDATE USERS')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserReq userReq) {
        BaseResponse res = userService.updateUser(id, userReq);
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("@permissionService.hasPermission('VIEW USERS')")
    @GetMapping("")
    public ResponseEntity<?> queryUser(@RequestParam(required = false, defaultValue = "1") int number,
                                       @RequestParam(required = false, defaultValue = "10") int size) {
        BaseResponse res = userService.queryUser(number, size);
        return ResponseEntity.ok(res);
    }

}
