package com.ooredoo.projetfinetude.Services.Impl;

import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Exception.ResourceNotFoundException;
import com.ooredoo.projetfinetude.Repositories.UserRepository;
import com.ooredoo.projetfinetude.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public User inscription(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @Override

    public User updateUser(User u ) {
        userRepository.save(u);
        return u;
    }

    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user  = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(user);
    }



    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);}

    @Override
    public User getUserByid(long id) {
        return userRepository.findById(id).orElse(null);}


}
