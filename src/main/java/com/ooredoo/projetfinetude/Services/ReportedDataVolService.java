package com.ooredoo.projetfinetude.Services;

import com.ooredoo.projetfinetude.Entities.Rapport.MyOoredooApp;
import com.ooredoo.projetfinetude.Entities.Rapport.ReportedDataVol;

import com.ooredoo.projetfinetude.Repositories.ReportedDataVolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportedDataVolService  {
    @Autowired
    ReportedDataVolRepository reportedDataVolRepository;
    public ReportedDataVol createRapportReported(ReportedDataVol reportedDataVol) { return reportedDataVolRepository.save(reportedDataVol);}
    public List<ReportedDataVol> createListReported(List<ReportedDataVol> reportedDataVols){return reportedDataVolRepository.saveAll(reportedDataVols);}

    public Page<ReportedDataVol> findAllReportedData(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return reportedDataVolRepository.findAll(pageable);
    }
    public Page<ReportedDataVol> getEntitiesReportedData(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportedDataVolRepository.findAll(pageable);
    }
}

