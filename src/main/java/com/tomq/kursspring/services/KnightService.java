package com.tomq.kursspring.services;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.domain.PlayerInformation;
import com.tomq.kursspring.domain.repository.KnightRepository;
import com.tomq.kursspring.domain.repository.PlayerInformationRepository;
import com.tomq.kursspring.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KnightService {

    final KnightRepository knightRepository;

    final PlayerInformationRepository playerInformationRepository;

    final QuestRepository questRepository;

    @Autowired
    public KnightService(KnightRepository knightRepository, PlayerInformationRepository playerInformationRepository, QuestRepository questRepository) {
        this.knightRepository = knightRepository;
        this.playerInformationRepository = playerInformationRepository;
        this.questRepository = questRepository;
    }

    public List<Knight> getAllKnights() {
        return new ArrayList<>(knightRepository.getAllKnights());
    }

    public void saveKnight(Knight knight) {
        knightRepository.createKnight(knight);
    }

    public Knight getKnight(int id) {
        return knightRepository.getKnightById(id);
    }

    public void deleteKnight(int id) {
        knightRepository.deleteKnight(id);
    }

    public void updateKnight(Knight knight) {
        knightRepository.updateKnight(knight.getKnightId(), knight);
    }

    public int collectReward() {
        Predicate<Knight> knightPredicate = knight -> {
            if (knight.getQuest() != null) {
                return knight.getQuest().isCompleted();
            } else {
                return false;
            }
        };
        int sum = knightRepository.getAllKnights().stream().filter(knightPredicate)
                .mapToInt(knight -> knight.getQuest().getReward())
                .sum();
        knightRepository.getAllKnights().stream().filter(knightPredicate)
                .forEach(knight -> {
                    knight.setQuest(null);
                });
        return sum;
    }

    @Transactional
    public void getMyGold() {
        List<Knight> allKnights = getAllKnights();
        allKnights.forEach(knight -> {
            if (knight.getQuest() != null) {
                boolean completed = knight.getQuest().isCompleted();
                if (completed) {
                    questRepository.update(knight.getQuest());
                }
            }
        });

        PlayerInformation first = playerInformationRepository.getFirst();
        int currentGold = first.getGold();
        first.setGold(currentGold + collectReward());

    }
}
