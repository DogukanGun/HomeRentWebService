package com.dag.homerentwebservice.model.request.home.facility;

import com.dag.homerentwebservice.model.entity.home.facility.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFacilityRequest {
    private FacilityType facilityType;
}
