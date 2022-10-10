package org.application.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity//mandatory annotation
@Table(name = "user_info") //optional annotation
public class UserBasicInfo extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "userId",unique = true,length = 20,nullable = false)
    private String userId;
    @Column(name = "name",length = 10,nullable = false)
    private String name;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "contact",unique = true,nullable = false)
    private Long contact;

}
