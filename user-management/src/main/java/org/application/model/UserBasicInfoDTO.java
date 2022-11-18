package org.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoDTO extends BaseDTO{
    private Integer id;
    private String userId;
    private String name;
    private String email;
    private Long contact;
    private boolean isActive;
    private Date birthDate;
    private String token;
    private DepartmentDTO department;
    private Set<RoleDTO> roles;
}
