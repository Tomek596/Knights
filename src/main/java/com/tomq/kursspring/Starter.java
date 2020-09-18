package com.tomq.kursspring;

import com.tomq.kursspring.domain.PlayerInformation;
import com.tomq.kursspring.domain.repository.KnightRepository;
import com.tomq.kursspring.domain.repository.PlayerInformationRepository;
import com.tomq.kursspring.domain.repository.QuestRepository;
import com.tomq.kursspring.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {


    KnightRepository knightRepository;

    QuestRepository questRepository;

    QuestService questService;

    PlayerInformationRepository playerInformationRepository;

    @Autowired
    public Starter(KnightRepository knightRepository, QuestRepository questRepository, QuestService questService, PlayerInformationRepository playerInformationRepository) {
        this.knightRepository = knightRepository;
        this.questRepository = questRepository;
        this.questService = questService;
        this.playerInformationRepository = playerInformationRepository;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {

        questRepository.createRandomQuest();
        questRepository.createRandomQuest();
        knightRepository.createKnight("Percival", 32);
        playerInformationRepository.createPlayerInformation(new PlayerInformation());

        questService.assignRandomQuest("Percival");
    }

}
