package com.ooredoo.projetfinetude.Controllers;

import com.ooredoo.projetfinetude.Entities.Role;
import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Services.Impl.RoleServiceImpl;
import com.ooredoo.projetfinetude.Services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2/role")
public class RoleControllers {
    @Autowired
    private RoleServiceImpl roleService;
    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody Role role) {return roleService.addRole(role);}
    @GetMapping("/allRole")
    public List<Role> allRoles() {return roleService.allRoles();}
    @DeleteMapping("/deleteRole/{id}")
    public  List<Role> deleteRoleById(@PathVariable("id") Long id) {roleService.deleteRole(id);return roleService.allRoles();}
    @PutMapping("/updateRole")
    public List<Role>updateRole(@RequestBody Role d){Role role = roleService.updateRole(d);return roleService.allRoles();}
    @GetMapping("get/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleByid(id);
    }
    }

