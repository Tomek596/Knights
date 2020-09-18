package com.tomq.kursspring.controllers;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.domain.Quest;
import com.tomq.kursspring.services.KnightService;
import com.tomq.kursspring.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestController {

    private KnightService knightService;

    private QuestService questService;

    @Autowired
    public QuestController(KnightService knightService, QuestService questService) {
        this.knightService = knightService;
        this.questService = questService;
    }

    @RequestMapping("/assignQuest")
    public String assingQuest(@RequestParam("knightId") Integer id, Model model) {
        Knight knight = knightService.getKnight(id);
        List<Quest> notStartedQuests = questService.getAllNotStartedQuests();
        model.addAttribute("knight", knight);
        model.addAttribute("notStartedQuests", notStartedQuests);
        return "assignQuest";
    }

    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assingQuest(Knight knight) {
        knightService.updateKnight(knight);
        Quest quest = knight.getQuest();
        questService.update(quest);
        knight.getQuest().setStarted(true);
        return "redirect:knights";
    }

    @RequestMapping(value = "/checkQuests")
    public String checkQuest() {
        knightService.getMyGold();
        return "redirect:/knights";
    }
}
