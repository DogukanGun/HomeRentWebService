package com.dag.homerentwebservice.model.request.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserHomeRelation {

    private Integer homeId;

    private Integer userId;

    private String status;

}
