package com.ooredoo.projetfinetude.Services;

import com.ooredoo.projetfinetude.Entities.Role;
import com.ooredoo.projetfinetude.Entities.User;

import java.util.List;

public interface RoleService {
    public List<Role> allRoles();
    public Role updateRole(Role role );
    public void deleteRole(Long id );
    public Role addRole(Role role );

}
