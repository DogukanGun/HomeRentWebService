package com.dag.homerentwebservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String userType;

    private String userPhoto;

}
