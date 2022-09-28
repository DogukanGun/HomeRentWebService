package com.dag.homerentwebservice.repository.home;

import com.dag.homerentwebservice.model.entity.home.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeImageRepository extends JpaRepository<HomeImage,Integer> {
}
