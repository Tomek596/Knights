package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Optional;

public interface KnightRepository {

    void createKnight(String name, int age);

    Collection<Knight> getAllKnights();

    Knight getKnightById(int id);

    Optional<Knight> getKnight(String name);

    void deleteKnight(int id);

    void createKnight(Knight knight);

    default void updateKnight(int knightId, Knight knight) {
        throw new NotImplementedException();
    }
}
