package com.app.rateme.api;

import com.app.rateme.api.dto.UserDto;
import com.app.rateme.model.User;

public class userModelAssembler {

    public static UserDto toModel(User userEntity) {
        
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

    public static User toEntity(UserDto userDto){
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

    

}
