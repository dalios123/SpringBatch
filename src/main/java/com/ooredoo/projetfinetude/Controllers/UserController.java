package com.ooredoo.projetfinetude.Controllers;

import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/inscription")
    public User inscription(@RequestBody User user) {return userService.inscription(user);}
    @PostMapping("/saveU")
    public User saveUser(@RequestBody User user) {return userService.addUser(user);}
    @GetMapping("/all")
    public List<User> allUsers() {
        return userService.allUsers();
    }
    @DeleteMapping("/delete/{id}")
    public  List<User> deleteUserById(@PathVariable("id") Long id) {userService.deleteUser(id);return userService.allUsers();}
    @PutMapping("/updateUser")
    public List<User>updateUser(@RequestBody User d){User user = userService.updateUser(d);return userService.allUsers();}
    @GetMapping("get/{id}")
    public User getUserById(@PathVariable Long id) {
       return userService.getUserByid(id);
    }

}



