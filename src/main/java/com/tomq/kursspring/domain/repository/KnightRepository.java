package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;

import java.util.Collection;
import java.util.Optional;

public interface KnightRepository {

    void createKnight(String name, int age);

    Collection<Knight> getAllKnights();

    Knight getKnightById(int id);

    Optional<Knight> getKnight(String name);

    void deleteKnight(int id);

    void createKnight(Knight knight);
}
