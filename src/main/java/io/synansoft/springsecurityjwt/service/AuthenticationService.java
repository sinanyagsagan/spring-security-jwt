package io.synansoft.springsecurityjwt.service;

import io.synansoft.springsecurityjwt.dto.UserDto;
import io.synansoft.springsecurityjwt.dto.UserRequest;
import io.synansoft.springsecurityjwt.dto.UserResponse;

public interface AuthenticationService {
    UserResponse save(UserDto userDto);
    UserResponse auth(UserRequest userRequest);
}
