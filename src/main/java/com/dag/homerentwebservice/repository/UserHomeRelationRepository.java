package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHomeRelationRepository extends JpaRepository<UserHomeRelation,Integer> { }
