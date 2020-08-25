package com.tomq.kursspring.services;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.domain.repository.KnightRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KnightService {

    final KnightRepository knightRepository;

    public KnightService(KnightRepository knightRepository) {
        this.knightRepository = knightRepository;
    }

    public List<Knight> getAllKnights() {
        return new ArrayList<>(knightRepository.getAllKnights());
    }


}
