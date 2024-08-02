package com.ooredoo.projetfinetude.Repositories;

import com.ooredoo.projetfinetude.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
