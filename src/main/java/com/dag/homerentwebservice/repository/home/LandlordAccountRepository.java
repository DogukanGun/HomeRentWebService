package com.dag.homerentwebservice.repository.home;

import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandlordAccountRepository extends JpaRepository<LandlordAccount,Integer> {
    Optional<LandlordAccount> findByHomeId(int homeId);
}
