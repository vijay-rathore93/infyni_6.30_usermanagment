package org.application.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Data
@Entity//mandatory annotation
@Builder
@Table(name = "user_info") //optional annotation
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "userId", unique = true, length = 200, nullable = false)
    private String userId;
    @Column(name = "name", length = 10, nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "contact", unique = true, nullable = false)
    private Long contact;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "birthDate", nullable = false)
    private Date birthDate;


    @Column(name = "token",unique = true)
    private String token;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   // @JoinColumn(name = "deptId")
    @PrimaryKeyJoinColumn
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id")//it will be name of column with foreign key
    private Set<Role> roles;




}


//one to one--Eager
//many to one-->Eager
//one to many-->lazy
//many to many-->lazy

//One to one can be achieved by 3 ways
//--Primarykey join column(2)(no column will generate, primary key column of associated
// entity will be used for other one )
//--foreign join column(2)(new column will be generated)
//--  joinTable                  (3)
//One to many can be achieved by 2 ways
//--foreign join column(2)
//--                    (3)
//many to many can be achieved by 1 ways
//--                    (3)
