package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;

import java.util.Collection;

public interface KnightRepository {

    void createKnight(String name, int age);

    Collection<Knight> getAllKnights();

    Knight getKnight(String name);

    void deleteKnight(String name);

    void build();
}
