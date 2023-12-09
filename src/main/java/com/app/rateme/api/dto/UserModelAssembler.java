package com.app.rateme.api.dto;

import org.springframework.stereotype.Component;

import com.app.rateme.model.User;

@Component
public class UserModelAssembler {

    public UserDto toModel(User userEntity) {
        
        UserDto userDto = new UserDto(
            userEntity.getUsername(),
            userEntity.getMail(),
            userEntity.getFirstname(),
            userEntity.getLastname(),
            userEntity.getStreet(),
            userEntity.getStreetNr(),
            userEntity.getZip(),
            userEntity.getCity()
        );

        return userDto;
    }

    public User toEntity(UserDto userDto){
        User user = new User(
            userDto.getCity(),
            userDto.getEmail(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getPassword(),
            userDto.getStreet(),
            userDto.getStreetNr(),
            userDto.getUserName(),
            userDto.getZip()
        );
        return user;
    }

    public UserResponseDto toModelResponse(User userEntity) {
        
        UserResponseDto userDto = new UserResponseDto(
            userEntity.getUsername(),
            userEntity.getMail(),
            userEntity.getFirstname(),
            userEntity.getLastname(),
            userEntity.getStreet(),
            userEntity.getStreetNr(),
            userEntity.getZip(),
            userEntity.getCity(),
            userEntity.getUserId()
        );

        return userDto;
    }

    

}
