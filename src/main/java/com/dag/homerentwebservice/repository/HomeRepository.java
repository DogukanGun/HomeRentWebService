package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.home.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home,Integer> {}
