package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Knight;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

public class DBKnightRepository implements KnightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createKnight(String name, int age) {

        Knight knight = new Knight(name, age);
        entityManager.persist(knight);
    }

    @Override
    public Collection<Knight> getAllKnights() {
        return entityManager.createQuery("FROM Knight", Knight.class).getResultList();
    }

    @Override
    public Optional<Knight> getKnight(String name) {
        Knight knight = entityManager.createQuery("FROM Knight k WHERE k.name=:name", Knight.class)
                .setParameter("name", name).getSingleResult();
        return Optional.ofNullable(knight);
    }

    @Override
    public Knight getKnightById(int id) {
        return entityManager.find(Knight.class, id);
    }

    @Override
    @Transactional
    public void deleteKnight(int id) {
        entityManager.remove(id);
    }

    @Override
    @Transactional
    public void createKnight(Knight knight) {
        entityManager.persist(knight);
    }

    @Override
    @Transactional
    public void updateKnight(int knightId, Knight knight) {
        entityManager.merge(knight);
    }
}
