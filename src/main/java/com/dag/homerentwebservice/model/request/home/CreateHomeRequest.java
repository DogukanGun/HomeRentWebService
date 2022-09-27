package com.dag.homerentwebservice.model.request.home;

import com.dag.homerentwebservice.model.request.landlordaccount.CreateLandlordAccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHomeRequest {

    private String homeName;

    private String homePhoto;

    private double price;

    private int bedroomCount;

    private String propertyType;

    private double size;

    private String facilities;

    private Integer userId;

    private CreateLandlordAccountRequest createLandlordAccountRequest;
}
