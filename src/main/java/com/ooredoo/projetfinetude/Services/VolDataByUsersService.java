package com.ooredoo.projetfinetude.Services;

import com.ooredoo.projetfinetude.Entities.Rapport.MyOoredooApp;
import com.ooredoo.projetfinetude.Entities.Rapport.VolDataByUsers;
import com.ooredoo.projetfinetude.Repositories.VolDataByUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolDataByUsersService {
    @Autowired
    private VolDataByUsersRepository volDataByUsersRepository;
    public VolDataByUsers createRapportVolData(VolDataByUsers volDataByUsers) {
        return volDataByUsersRepository.save(volDataByUsers);
    }
    public List<VolDataByUsers> createList(List<VolDataByUsers> volDataByUsers){
        return volDataByUsersRepository.saveAll(volDataByUsers);
    }

    public Page<VolDataByUsers> findAllVolData(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return volDataByUsersRepository.findAll(pageable);
    }
    public Page<VolDataByUsers> getEntitiesVolData(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return volDataByUsersRepository.findAll(pageable);
    }
}
