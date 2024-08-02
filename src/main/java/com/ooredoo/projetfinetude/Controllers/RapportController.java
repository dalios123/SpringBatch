package com.ooredoo.projetfinetude.Controllers;
import com.ooredoo.projetfinetude.Entities.Rapport.MyOoredooApp;
import com.ooredoo.projetfinetude.Entities.Rapport.ReportedDataVol;
import com.ooredoo.projetfinetude.Entities.Rapport.VolDataByUsers;
import com.ooredoo.projetfinetude.Services.MyooredooAppService;
import com.ooredoo.projetfinetude.Services.ReportedDataVolService;
import com.ooredoo.projetfinetude.Services.VolDataByUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/rapport")
public class RapportController {
    @Autowired
    private MyooredooAppService myooredooAppService;
    @Autowired
    private ReportedDataVolService reportedDataVolService;
    @Autowired
    VolDataByUsersService volDataByUsersService;

    @PostMapping("/save_vol_data_byuser")
    public VolDataByUsers createRapportVolData(@RequestBody VolDataByUsers volDataByUsers) {
        return volDataByUsersService.createRapportVolData(volDataByUsers);
    }

    @PostMapping("/save_reported_data")
    public ReportedDataVol createRapportReportedData(@RequestBody ReportedDataVol reportedDataVol) {
        return reportedDataVolService.createRapportReported(reportedDataVol);
    }

    @PostMapping("/save_my_ooredoo")
    public MyOoredooApp createRapportMyOoredoo(@RequestBody MyOoredooApp myOoredooApp) {
        return myooredooAppService.createRapportMyOoredoo(myOoredooApp);
    }
    @PostMapping("/save_all_my_ooredoo")
    public List<MyOoredooApp> createAllRapprtsMyOoredoo(@RequestBody List<MyOoredooApp> myOoredooApps) {return myooredooAppService.createListMyOoredoo(myOoredooApps);}


    @GetMapping("/myooredoo")
    public List<MyOoredooApp> findAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<MyOoredooApp> result = myooredooAppService.findAllMyOoredoo(pageNo, pageSize, sortBy, sortDirection);
        return result.getContent();

    }
    @GetMapping("/entities")
    public Page<MyOoredooApp> getEntities(@RequestParam int page, @RequestParam int size) {
        return myooredooAppService.getEntitiesMyOoredoo(page, size);
    }

    @GetMapping("/reportedData")
    public List<ReportedDataVol> findAllReporteddata(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<ReportedDataVol> result = reportedDataVolService.findAllReportedData(pageNo, pageSize, sortBy, sortDirection);
        return result.getContent();

    }
    @GetMapping("/entitiesRD")
    public Page<ReportedDataVol> getEntitiesReportedDtata(@RequestParam int page, @RequestParam int size) {
        return reportedDataVolService.getEntitiesReportedData(page, size);
    }
    @GetMapping("/volData")
    public List<VolDataByUsers> findAllVolData(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<VolDataByUsers> result = volDataByUsersService.findAllVolData(pageNo, pageSize, sortBy, sortDirection);
        return result.getContent();

    }
    @GetMapping("/entitiesVolD")
    public Page<VolDataByUsers> getEntitiesVolData(@RequestParam int page, @RequestParam int size) {
        return volDataByUsersService.getEntitiesVolData(page, size);
    }
}



