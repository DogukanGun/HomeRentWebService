package com.dag.homerentwebservice.model.mapper;

import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import com.dag.homerentwebservice.model.request.home.CreateUserHomeRelation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserHomeRelationMapper {
    UserHomeRelationMapper USER_HOME_RELATION_MAPPER = Mappers.getMapper(UserHomeRelationMapper.class);

    UserHomeRelation createUserHomeRelationMapper(CreateUserHomeRelation createUserHomeRelation);


}
