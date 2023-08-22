package com.etz.MPB.portal.dto.request;

import com.etz.MPB.portal.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private UserStatus status;
    private List<Long> roles;
    private LocalDateTime created_on;
    private Long created_by;
    private LocalDateTime updated_on;
    private Long updated_by;
}
