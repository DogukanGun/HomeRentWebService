

package com.dag.homerentwebservice.model.dto.home;

import com.dag.homerentwebservice.model.dto.account.LandlordAccountDto;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeDto {

    private Integer id;

    private String homeName;

    @Column(length = 1350000)
    private String homePhoto;

    private double price;

    private int bedroomCount;

    private String propertyType;

    private double size;

    private String facilities;

    private LandlordAccountDto landlordAccountDto;

}
