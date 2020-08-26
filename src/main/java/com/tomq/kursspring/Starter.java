package com.tomq.kursspring;

import com.tomq.kursspring.domain.repository.KnightRepository;
import com.tomq.kursspring.domain.repository.QuestRepository;
import com.tomq.kursspring.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {


    KnightRepository knightRepository;

    QuestRepository questRepository;

    QuestService questService;

    @Autowired
    public Starter(KnightRepository knightRepository, QuestRepository questRepository, QuestService questService) {
        this.knightRepository = knightRepository;
        this.questRepository = questRepository;
        this.questService = questService;
    }

    @Override
    public void run(String... strings) throws Exception {

        questRepository.createRandomQuest();
        questRepository.createRandomQuest();
    }

}
