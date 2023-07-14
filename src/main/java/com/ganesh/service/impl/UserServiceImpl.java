package com.ganesh.service.impl;

import com.ganesh.entity.Confirmation;
import com.ganesh.entity.User;
import com.ganesh.repository.ConfirmationRepository;
import com.ganesh.repository.UserRepository;
import com.ganesh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ConfirmationRepository confirmationRepository;

    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {throw new RuntimeException("Email already exists");}
           user.setEnabled(false);
           userRepository.save(user);
           Confirmation confirmation = new Confirmation(user);
           confirmationRepository.save(confirmation);

        /** To Send email to user with token **/
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}
