package com.tomq.kursspring.domain.repository;

import com.tomq.kursspring.domain.Quest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class QuestRepository {


    Random rand = new Random();

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createQuest(String description) {
        Quest newQuest = new Quest(description);
        entityManager.persist(newQuest);
    }

    public List<Quest> getAll() {
        return entityManager.createQuery("FROM Quest", Quest.class).getResultList();
    }

    @Transactional
    public void deleteQuest(Quest quest) {
        entityManager.remove(quest);
    }

    @Scheduled(fixedDelayString = "${questCreationDelay}")
    @Transactional
    public void createRandomQuest() {
        List<String> descriptions = new ArrayList<>();

        descriptions.add("Uratuj ksiezniczke");
        descriptions.add("Wez udzial w turnieju");
        descriptions.add("Zabij bande goblinow");
        descriptions.add("Zabij smoka");

        String description = descriptions.get(rand.nextInt(descriptions.size()));
        createQuest(description);
    }

    @Transactional
    public void update(Quest quest) {
        entityManager.merge(quest);
    }

    public Quest getQuest(Integer id) {
        return entityManager.find(Quest.class, id);
    }
}
