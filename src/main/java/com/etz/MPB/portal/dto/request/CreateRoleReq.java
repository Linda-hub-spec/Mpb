package com.etz.MPB.portal.dto.request;

import com.etz.MPB.portal.entity.Permissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleReq {
    private String name;
    private String description;
    private Set<Long> permissions;

}
