package com.dag.homerentwebservice.security.dto;

import lombok.Data;


@Data
public class SecLoginRequestDto {

    private String username;
    private String password;
}
