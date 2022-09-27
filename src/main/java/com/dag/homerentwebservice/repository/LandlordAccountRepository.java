package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandlordAccountRepository extends JpaRepository<LandlordAccount,Integer> {
}
