package com.ooredoo.projetfinetude.Services;
import com.ooredoo.projetfinetude.Entities.Rapport.MyOoredooApp;
import com.ooredoo.projetfinetude.Repositories.MyOoredooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class MyooredooAppService {
    @Autowired
    MyOoredooRepository myOoredooRepository;

    public MyOoredooApp createRapportMyOoredoo(MyOoredooApp myOoredooApp) {
        return myOoredooRepository.save(myOoredooApp);
    }
    public List<MyOoredooApp> createListMyOoredoo(List<MyOoredooApp> myOoredooApps){
        return myOoredooRepository.saveAll(myOoredooApps);
    }

    public Page<MyOoredooApp> findAllMyOoredoo(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return myOoredooRepository.findAll(pageable);
    }
    public Page<MyOoredooApp> getEntitiesMyOoredoo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return myOoredooRepository.findAll(pageable);
    }
}
