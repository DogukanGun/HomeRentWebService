package com.dag.homerentwebservice.model.entity.account;

import com.dag.homerentwebservice.model.entity.home.Home;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "landlord_accounts")
public class LandlordAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String accountNumber;
    private String bankName;
    private String walletNumber;

    @Column(name = "landlord_home_id")
    private int homeId;

    @OneToOne()
    @JoinColumn(name = "landlord_home_id",updatable = false,insertable = false,nullable = false)
    private Home home;

}
