package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHomeRelationRepository extends JpaRepository<UserHomeRelation,Integer> {
    Optional<UserHomeRelation> findByHomeIdAndUserId(int homeId,int userId);
}
