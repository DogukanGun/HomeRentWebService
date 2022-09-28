

package com.dag.homerentwebservice.model.dto.home;

import com.dag.homerentwebservice.model.dto.account.LandlordAccountDto;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import com.dag.homerentwebservice.model.entity.home.HomeImage;
import com.dag.homerentwebservice.model.entity.home.facility.Facility;
import com.dag.homerentwebservice.model.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

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

    private PropertyType propertyType;

    private double size;

    private List<FacilityDto> facilities;

    private LandlordAccountDto landlordAccountDto;

    private List<HomeImageDto> homeImages;

}
