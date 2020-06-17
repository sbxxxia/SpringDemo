package com.sbxxxia.web.service;

import com.sbxxxia.web.entity.RandNumMessage;
import com.sbxxxia.web.repository.RandNumRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RandNumService {
    // 저장소
    private RandNumRepository repository;

    public RandNumService(RandNumRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public RandNumMessage getRandom(){
        return repository.getRandom();
    }
}
