package com.tomq.kursspring.services;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.domain.PlayerInformation;
import com.tomq.kursspring.domain.repository.KnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KnightService {

    final KnightRepository knightRepository;

    final PlayerInformation playerInformation;

    @Autowired
    public KnightService(KnightRepository knightRepository, PlayerInformation playerInformation) {
        this.knightRepository = knightRepository;
        this.playerInformation = playerInformation;
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
                .forEach(knight -> knight.setQuest(null));
        return sum;
    }

    public void getMyGold() {
        List<Knight> allKnights = getAllKnights();
        allKnights.forEach(knight -> {
            if (knight.getQuest() != null) {
                knight.getQuest().isCompleted();
            }
        });

        int currentGold = playerInformation.getGold();
        playerInformation.setGold(currentGold + collectReward());
    }
}
