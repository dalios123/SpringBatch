package com.ooredoo.projetfinetude.Repositories;

import com.ooredoo.projetfinetude.Entities.Rapport.ReportedDataVol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportedDataVolRepository  extends JpaRepository<ReportedDataVol, Long> {
}
