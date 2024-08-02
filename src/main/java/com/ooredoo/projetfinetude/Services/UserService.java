package com.ooredoo.projetfinetude.Services;

import com.ooredoo.projetfinetude.Entities.User;

import java.util.List;

public interface UserService {
    public User inscription(User user);
    public List<User> allUsers();
    public User updateUser(User user );
    public void deleteUser(Long id );
    public User addUser(User user);
    public User getUserByid(long id );

}
