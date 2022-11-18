package org.application.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BaseDTO{
    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private Set<PermissionDTO> permissions;
}
