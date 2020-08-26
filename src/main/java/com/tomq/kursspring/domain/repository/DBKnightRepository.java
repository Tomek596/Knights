package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Optional;

public class DBKnightRepository implements KnightRepository {

    @Override
    public void createKnight(String name, int age) {

        System.out.println("uzywam bazy danych");
        throw new NotImplementedException();
    }

    @Override
    public Collection<Knight> getAllKnights() {
        System.out.println("uzywam bazy danych");
        throw new NotImplementedException();
    }

    @Override
    public Knight getKnightById(int id) {
        System.out.println("uzywam bazy danych");
        throw new NotImplementedException();
    }

    @Override
    public Optional<Knight> getKnight(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteKnight(int id) {
        System.out.println("Usuwam rycerza o id:");
    }

    @Override
    public void createKnight(Knight knight) {
        System.out.println("towrzenie rycerza w bazie danych");
        throw new NotImplementedException();
    }


}
