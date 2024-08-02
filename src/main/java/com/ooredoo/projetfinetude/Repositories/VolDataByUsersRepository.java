package com.ooredoo.projetfinetude.Repositories;

import com.ooredoo.projetfinetude.Entities.Rapport.VolDataByUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolDataByUsersRepository  extends JpaRepository<VolDataByUsers, Long> {
}
