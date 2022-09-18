package com.dag.homerentwebservice.security.service;

import com.dag.homerentwebservice.base.DialogBoxButtonGenerator;
import com.dag.homerentwebservice.model.dto.UserDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxColorType;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxType;
import com.dag.homerentwebservice.model.dto.dialogbox.button.ButtonActionType;
import com.dag.homerentwebservice.model.dto.dialogbox.button.DialogBoxButton;
import com.dag.homerentwebservice.model.entity.User;
import com.dag.homerentwebservice.model.enums.UserType;
import com.dag.homerentwebservice.model.request.user.CreateUserRequest;
import com.dag.homerentwebservice.model.request.user.UpdateUserRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.security.dto.DialogBoxButtonContent;
import com.dag.homerentwebservice.security.dto.DialogBoxContent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityService cusCustomerEntityService;
    private final UserService cusCustomerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public BaseResponse<UserDto> register(CreateUserRequest cusCustomerSaveRequestDto, String userType) {
        try {
            return handleBaseResponseForRegister(cusCustomerService.createUser(cusCustomerSaveRequestDto, UserType.valueOf(userType)));
        }catch (IllegalArgumentException illegalArgumentException){}
        return handleBaseResponseForRegister(null);
    }

    public BaseResponse<UserDto> registerAsAdmin(CreateUserRequest cusCustomerSaveRequestDto) {
        return handleBaseResponseForRegister(cusCustomerService.createUser(cusCustomerSaveRequestDto, UserType.ADMIN));
    }

    private BaseResponse<UserDto> handleBaseResponseForRegister(UserDto userDto) {
        if (userDto == null) {
            DialogBoxButtonContent dialogBoxButtonContent = DialogBoxButtonContent.builder()
                    .buttonText("Try Again")
                    .buttonActionType(ButtonActionType.DISMISS)
                    .build();
            List<DialogBoxButtonContent> dialogBoxButtonArrays = new ArrayList<>();
            dialogBoxButtonArrays.add(dialogBoxButtonContent);
            return handleBaseResponse(
                    DialogBoxContent.builder()
                            .message("Email,phone or username are used therefore please try different information.")
                            .title("Oppss!")
                            .dialogBoxButtonContents(dialogBoxButtonArrays)
                            .build()
            );
        }
        return BaseResponse.<UserDto>builder()
                .data(userDto)
                .error(false)
                .build();
    }

    private <T> BaseResponse<T> handleBaseResponse(DialogBoxContent dialogBoxContent) {
        ArrayList<DialogBoxButton> dialogBoxButtonArrays = new ArrayList<>();
        for (DialogBoxButtonContent dialogBoxButtonContent : dialogBoxContent.getDialogBoxButtonContents()
        ){
            DialogBoxButton dialogBoxButton = null;
            if (dialogBoxButtonContent.getButtonActionType() == ButtonActionType.DISMISS){
                 dialogBoxButton = DialogBoxButtonGenerator.getInstance()
                        .generateDismissButton(dialogBoxButtonContent.getButtonText());
            }
            else if (dialogBoxButtonContent.getButtonActionType() == ButtonActionType.GO_BACK){
                dialogBoxButton = DialogBoxButtonGenerator.getInstance()
                        .generateGoBackButton(dialogBoxButtonContent.getButtonText());
            }
            else if (dialogBoxButtonContent.getButtonActionType() == ButtonActionType.REQUEST){
                dialogBoxButton = DialogBoxButtonGenerator.getInstance()
                        .generateRequestButton(dialogBoxButtonContent.getButtonText());
            }
            dialogBoxButtonArrays.add(dialogBoxButton);
        }

        DialogBoxDto dialogBoxDto = DialogBoxDto.builder()
                .dialogBoxType(DialogBoxType.ERROR)
                .dialogBoxColorType(DialogBoxColorType.ORANGE)
                .cancelable(true)
                .message(dialogBoxContent.getMessage())
                .title(dialogBoxContent.getTitle())
                .buttonList(dialogBoxButtonArrays)
                .build();
        return BaseResponse.<T>builder()
                .data(null)
                .error(true)
                .dialogBoxDto(dialogBoxDto)
                .build();

    }

    public BaseResponse<LoginResponse> login(SecLoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getUsername(), secLoginRequestDto.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenGenerator.generateJwtToken(authentication);

            String bearer = EnumJwtConstant.BEARER.getConstant();
            User user = getCurrentCustomer();
            if (user == null) {
                return getLoginResponseBaseResponseAsFailed();
            }
            LoginResponse loginResponse = LoginResponse.builder()
                    .username(secLoginRequestDto.getUsername())
                    .token(bearer + token)
                    .userType(user.getUserType())
                    .build();

            return BaseResponse
                    .<LoginResponse>builder()
                    .error(false)
                    .data(loginResponse)
                    .build();
        } catch (Exception e) {
            return getLoginResponseBaseResponseAsFailed();
        }
    }

    private BaseResponse<LoginResponse> getLoginResponseBaseResponseAsFailed() {
        DialogBoxButtonContent dialogBoxButtonContent = DialogBoxButtonContent.builder()
                .buttonText("Try Again")
                .buttonActionType(ButtonActionType.DISMISS)
                .build();
        List<DialogBoxButtonContent> dialogBoxButtonArrays = new ArrayList<>();
        dialogBoxButtonArrays.add(dialogBoxButtonContent);
        return handleBaseResponse(
                DialogBoxContent.builder()
                        .message("We couldn't find you, sorry.")
                        .title("Oppss!")
                        .dialogBoxButtonContents(dialogBoxButtonArrays)
                        .build()
        );
    }

    public User getCurrentCustomer() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        User cusCustomer = null;
        if (jwtUserDetails != null) {
            cusCustomer = cusCustomerEntityService.getUserById(jwtUserDetails.getId());
        }

        return cusCustomer;
    }

    public Integer getCurrentCustomerId() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        return jwtUserDetails.getId();
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }

    public UserDto getProfile(UpdateUserRequest updateUserRequest) {
        return cusCustomerService.getUserProfile(updateUserRequest);
    }

    public UserDto updateUser(UpdateUserRequest updateUserRequest) {
        return cusCustomerService.updateUser(updateUserRequest);
    }
}
