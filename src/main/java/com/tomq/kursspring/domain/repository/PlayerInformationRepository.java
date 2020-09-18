package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.PlayerInformation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PlayerInformationRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createPlayerInformation(PlayerInformation playerInformation) {
        PlayerInformation pi = new PlayerInformation();
        em.persist(pi);
    }

    public PlayerInformation getFirst() {
        return (PlayerInformation) em.createQuery("FROM PlayerInformation", PlayerInformation.class).getResultList().get(0);
    }
}
