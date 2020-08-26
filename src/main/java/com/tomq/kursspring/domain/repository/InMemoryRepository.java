package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;

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
        newKnight.setKnightId(getNewId());
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

        Optional<Knight> knightByName = knights.values().stream().filter(knight -> knight.getName().equals(name)).findAny();

        return knightByName;
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
        knight.setKnightId(getNewId());
        knights.put(knight.getKnightId(), knight);
    }

    @Override
    public String toString() {
        return "InMemoryRepository{" +
                "knights=" + knights +
                '}';
    }

    public int getNewId() {
        if (knights.isEmpty()) {
            return 0;
        } else {
            Integer integer = knights.keySet().stream().max((o1, o2) -> o1.compareTo(o2)).get();
            return integer + 1;
        }
    }
}
