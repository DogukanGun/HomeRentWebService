package com.dag.homerentwebservice.model.request.home;

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

    private int status;
}
