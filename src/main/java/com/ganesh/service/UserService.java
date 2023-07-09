package com.ganesh.service;

import com.ganesh.entity.User;

public interface UserService {

    User saveUser(User user);

    Boolean verifyToken(String token);
}
