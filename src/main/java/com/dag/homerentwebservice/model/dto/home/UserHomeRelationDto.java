package com.dag.homerentwebservice.model.dto.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserHomeRelationDto {

    private int homeId;

    private int userId;

    private String status;

}
