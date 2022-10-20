package org.application.repo;


import org.application.entity.UserBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserBasicInfo,Integer> {
    Optional<UserBasicInfo> findByUserId(String userId);

    // save(entity_without_primary key)--->  created entity with primary key
    // saveAll(List<entity_without_primary key>)--->  List<created entity with primary key>
    //  findAll()  ->  List<Entity>
    //  findById(primaryKey)  ---> Optional<Entity>
    //  delete(entity) ---- void
    //  deleteAll(List<Entity>  --->void




}
