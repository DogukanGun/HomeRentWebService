package com.dag.homerentwebservice.repository.home;

import com.dag.homerentwebservice.model.entity.home.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Integer> {
    Optional<List<Facility>> findAllByHomeId(int homeId);
}
