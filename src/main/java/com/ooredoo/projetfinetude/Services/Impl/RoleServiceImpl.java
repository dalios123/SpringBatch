package com.ooredoo.projetfinetude.Services.Impl;

import com.ooredoo.projetfinetude.Entities.Role;
import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Repositories.RoleRepository;
import com.ooredoo.projetfinetude.Repositories.UserRepository;
import com.ooredoo.projetfinetude.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> allRoles() {return roleRepository.findAll();}
    @Override
    public Role updateRole(Role role) {roleRepository.save(role);return role;}
    @Override
    public void deleteRole(Long id) {roleRepository.deleteById(id);}
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);}
     public Role getRoleByid(long id) {
        return roleRepository.findById(id).orElse(null);}



    }

