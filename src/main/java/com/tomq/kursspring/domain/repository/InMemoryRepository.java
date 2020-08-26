package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.utils.Ids;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository implements KnightRepository {

    Map<Integer, Knight> knights = new HashMap<>();

    public InMemoryRepository() {

    }

    @Override
    public void createKnight(String name, int age) {

        Knight newKnight = new Knight(name, age);
        newKnight.setKnightId(Ids.getNewId(knights.keySet()));
        knights.put(newKnight.getKnightId(), newKnight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return knights.values();
    }

    @Override
    public Knight getKnightById(int id) {
        return knights.get(id);
    }

    @Override
    public Optional<Knight> getKnight(String name) {

        return knights.values().stream().filter(knight -> knight.getName().equals(name)).findAny();
    }

    @Override
    public void deleteKnight(int id) {
        knights.remove(id);
    }

    @PostConstruct
    public void build() {
        createKnight("Lancelot", 29);
        createKnight("Percival", 25);
    }

    @Override
    public void createKnight(Knight knight) {
        knight.setKnightId(Ids.getNewId(knights.keySet()));
        knights.put(knight.getKnightId(), knight);
    }

    @Override
    public String toString() {
        return "InMemoryRepository{" +
                "knights=" + knights +
                '}';
    }
}
