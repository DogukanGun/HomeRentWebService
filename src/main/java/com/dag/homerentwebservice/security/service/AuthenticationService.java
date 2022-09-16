package com.dag.homerentwebservice.security.service;

import com.dag.homerentwebservice.model.dto.UserDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxColorType;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxType;
import com.dag.homerentwebservice.model.entity.User;
import com.dag.homerentwebservice.model.enums.UserType;
import com.dag.homerentwebservice.model.request.user.CreateUserRequest;
import com.dag.homerentwebservice.model.request.user.UpdateUserRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.security.dto.LoginResponse;
import com.dag.homerentwebservice.security.dto.SecLoginRequestDto;
import com.dag.homerentwebservice.security.enums.EnumJwtConstant;
import com.dag.homerentwebservice.security.security.JwtTokenGenerator;
import com.dag.homerentwebservice.security.security.JwtUserDetails;
import com.dag.homerentwebservice.service.UserEntityService;
import com.dag.homerentwebservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityService cusCustomerEntityService;
    private final UserService cusCustomerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UserDto register(CreateUserRequest cusCustomerSaveRequestDto) {
        return cusCustomerService.createUser(cusCustomerSaveRequestDto, UserType.USER);
    }

    public UserDto registerAsAdmin(CreateUserRequest cusCustomerSaveRequestDto) {
        return cusCustomerService.createUser(cusCustomerSaveRequestDto, UserType.ADMIN);
    }

    public UserDto getProfile(UpdateUserRequest updateUserRequest){
        return cusCustomerService.getUserProfile(updateUserRequest);
    }

    public UserDto updateUser(UpdateUserRequest updateUserRequest){
        return cusCustomerService.updateUser(updateUserRequest);
    }

    public BaseResponse<LoginResponse> login(SecLoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getUsername(), secLoginRequestDto.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenGenerator.generateJwtToken(authentication);

            String bearer = EnumJwtConstant.BEARER.getConstant();
            User user = getCurrentCustomer();
            if (user == null){
                return getLoginResponseBaseResponseAsFailed();
            }
            LoginResponse loginResponse = LoginResponse.builder()
                    .username(secLoginRequestDto.getUsername())
                    .token(bearer+token)
                    .userType(user.getUserType())
                    .build();

            return BaseResponse
                    .<LoginResponse>builder()
                    .error(false)
                    .data(loginResponse)
                    .build();
        }catch (Exception e){
            return getLoginResponseBaseResponseAsFailed();
        }
    }

    private BaseResponse<LoginResponse> getLoginResponseBaseResponseAsFailed() {
        DialogBoxDto dialogBoxDto = DialogBoxDto.builder()
                .dialogBoxType(DialogBoxType.ERROR)
                .dialogBoxColorType(DialogBoxColorType.ORANGE)
                .cancelable(true)
                .message("")
                .title("")
                .build();

        return BaseResponse
                .<LoginResponse>builder()
                .dialogBoxDto(dialogBoxDto)
                .error(true)
                .build();
    }

    public User getCurrentCustomer() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        User cusCustomer = null;
        if (jwtUserDetails != null){
            cusCustomer = cusCustomerEntityService.getUserById(jwtUserDetails.getId());
        }

        return cusCustomer;
    }

    public Integer getCurrentCustomerId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        return jwtUserDetails.getId();
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
