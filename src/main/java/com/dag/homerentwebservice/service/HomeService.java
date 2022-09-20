package com.dag.homerentwebservice.service;

import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.entity.home.Home;
import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import com.dag.homerentwebservice.model.enums.HomeStatus;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.model.request.home.CreateUserHomeRelation;
import com.dag.homerentwebservice.repository.HomeRepository;
import com.dag.homerentwebservice.repository.UserHomeRelationRepository;
import com.dag.homerentwebservice.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dag.homerentwebservice.model.mapper.HomeMapper.HOME_MAPPER;
import static com.dag.homerentwebservice.model.mapper.UserHomeRelationMapper.USER_HOME_RELATION_MAPPER;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;
    private final AuthenticationService authenticationService;
    private final UserHomeRelationRepository userHomeRelationRepository;

    public HomeDto createHome(CreateHomeRequest createHomeRequest){
        Home home = HOME_MAPPER.createHome(createHomeRequest);
        home = homeRepository.save(home);
        CreateUserHomeRelation createUserHomeRelation = CreateUserHomeRelation
                .builder()
                .homeId(home.getId())
                .userId(authenticationService.getCurrentCustomerId())
                .status(HomeStatus.Selling.name())
                .build();
        UserHomeRelation userHomeRelation = USER_HOME_RELATION_MAPPER.createUserHomeRelationMapper(createUserHomeRelation);
        userHomeRelationRepository.save(userHomeRelation);
        return HOME_MAPPER.convertToHomeDto(home);
    }
}
