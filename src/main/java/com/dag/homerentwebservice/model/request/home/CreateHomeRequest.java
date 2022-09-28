package com.dag.homerentwebservice.model.request.home;

import com.dag.homerentwebservice.model.enums.PropertyType;
import com.dag.homerentwebservice.model.request.home.facility.CreateFacilityRequest;
import com.dag.homerentwebservice.model.request.landlordaccount.CreateLandlordAccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHomeRequest {

    private String homeName;

    private String homePhoto;

    private double price;

    private int bedroomCount;

    private PropertyType propertyType;

    private double size;

    private CreateLandlordAccountRequest createLandlordAccountRequest;

    private List<String> homeImagesAsBase64;

    private List<CreateFacilityRequest> createFacilityRequests;
}
