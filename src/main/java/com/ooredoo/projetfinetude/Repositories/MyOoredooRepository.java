package com.ooredoo.projetfinetude.Repositories;

import com.ooredoo.projetfinetude.Entities.Rapport.MyOoredooApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyOoredooRepository extends JpaRepository<MyOoredooApp, Long> {
}
