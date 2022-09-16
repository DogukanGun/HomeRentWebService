package com.dag.homerentwebservice.service;



import com.dag.homerentwebservice.model.dto.UserDto;
import com.dag.homerentwebservice.model.entity.User;
import com.dag.homerentwebservice.model.enums.UserType;
import com.dag.homerentwebservice.model.request.user.CreateUserRequest;
import com.dag.homerentwebservice.model.request.user.SaveUserPhotoRequest;
import com.dag.homerentwebservice.model.request.user.UpdateUserRequest;
import com.dag.homerentwebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import static com.dag.homerentwebservice.model.mapper.UserMapper.USER_MAPPER;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDto createUser(CreateUserRequest createUserRequest, UserType userType){
        User user = USER_MAPPER.createUser(createUserRequest);
        user.setUserType(userType.getLabel());
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return USER_MAPPER.convertToUserDto(userRepository.save(user));
    }

    public UserDto saveUserPhoto(SaveUserPhotoRequest saveUserPhotoRequest){
        User user = userRepository.findByUsernameEquals(saveUserPhotoRequest.getUsername())
                .orElseThrow(()->new NotFoundException("User not found"));
        user.setUserPhoto(saveUserPhotoRequest.getUserPhoto());
        return USER_MAPPER.convertToUserDto(userRepository.save(user));
    }

    public UserDto getUserProfile(UpdateUserRequest updateUserRequest){
        return USER_MAPPER.convertToUserDto(userRepository.findByUsernameEquals(updateUserRequest.getUsername())
                .orElseThrow(()->new NotFoundException("User Not Found")));
    }

    public UserDto updateUser(UpdateUserRequest updateUserRequest){
        if (!updateUserRequest.getUsername().equals("")){
            User user = userRepository.findByUsernameEquals(updateUserRequest.getUsername())
                    .orElseThrow(()->new NotFoundException("User Not Found"));
            if (!updateUserRequest.getPhoneNumber().equals("")){
                user.setPhoneNumber(updateUserRequest.getPhoneNumber());
            }
            if (!updateUserRequest.getEmail().equals("")){
                user.setEmail(updateUserRequest.getEmail());
            }
            return USER_MAPPER.convertToUserDto(userRepository.save(user));
        }
        throw new NotFoundException("User not found");
    }



}
