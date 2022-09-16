package com.dag.homerentwebservice.security.controller;


import com.dag.homerentwebservice.model.dto.UserDto;
import com.dag.homerentwebservice.model.request.user.CreateUserRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.security.dto.LoginResponse;
import com.dag.homerentwebservice.security.dto.RestResponse;
import com.dag.homerentwebservice.security.dto.SecLoginRequestDto;
import com.dag.homerentwebservice.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody SecLoginRequestDto secLoginRequestDto){
        return authenticationService.login(secLoginRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserRequest createUserRequest){

        UserDto cusCustomerDto =authenticationService.register(createUserRequest);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @PostMapping("/registerasadmin")
    public ResponseEntity registerAsAdmin(@RequestBody CreateUserRequest createUserRequest){

        UserDto cusCustomerDto =authenticationService.registerAsAdmin(createUserRequest);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }


}
