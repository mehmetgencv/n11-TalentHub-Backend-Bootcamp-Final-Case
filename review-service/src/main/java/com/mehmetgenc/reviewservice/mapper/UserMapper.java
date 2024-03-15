package com.mehmetgenc.reviewservice.mapper;


import com.mehmetgenc.reviewservice.dto.UserDTO;
import com.mehmetgenc.reviewservice.dto.UserLocationDTO;
import com.mehmetgenc.reviewservice.entity.User;
import com.mehmetgenc.reviewservice.request.UserSaveRequest;
import com.mehmetgenc.reviewservice.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User convertToUser(UserSaveRequest userSaveRequest);
    User convertToUser(UserUpdateRequest userUpdateRequest);

    UserDTO convertToUserDto(User user);

    List<UserDTO> convertToUserDTOs(List<User> users);

    @Mapping(target = "id", ignore = true)
    User updateCustomerFields(@MappingTarget User user, UserUpdateRequest request);

    UserLocationDTO convertToUserLocationDTO(User user);

    List<User> convertToUsers(List<UserSaveRequest> userSaveRequestList);
}
