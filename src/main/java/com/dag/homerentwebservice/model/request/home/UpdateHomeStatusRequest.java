package com.dag.homerentwebservice.model.request.home;

import com.dag.homerentwebservice.model.enums.HomeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateHomeStatusRequest {
    private int homeId;

    private HomeStatus status;
}
