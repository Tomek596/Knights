package com.tomq.kursspring.controllers;

import com.tomq.kursspring.components.TimeComponent;
import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.domain.PlayerInformation;
import com.tomq.kursspring.domain.repository.PlayerInformationRepository;
import com.tomq.kursspring.services.KnightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class KnightController {

    private final KnightService service;
    private final TimeComponent timeComponent;
    private final PlayerInformationRepository playerInformationRepository;

    @Autowired
    public KnightController(KnightService service, TimeComponent timeComponent, PlayerInformationRepository playerInformationRepository) {
        this.service = service;
        this.timeComponent = timeComponent;
        this.playerInformationRepository = playerInformationRepository;
    }

    @RequestMapping("/knights")
    public String getKnights(Model model) {
        List<Knight> allKnights = service.getAllKnights();
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knights", allKnights);
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("playerInformation", playerInformation);
        return "knights";
    }

    @RequestMapping(value = "/knights", method = RequestMethod.POST)
    public String saveKnight(@Valid Knight knight, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getObjectName() + " " + error.getDefaultMessage()));
            return "knightform";
        }
        service.saveKnight(knight);
        return "redirect:/knights";
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model) {
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knight", new Knight());
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("playerInformation", playerInformation);
        return "knightform";
    }

    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model) {
        Knight knight = service.getKnight(id);
        PlayerInformation playerInformation = playerInformationRepository.getFirst();
        model.addAttribute("knight", knight);
        model.addAttribute("timeComponent", timeComponent);
        model.addAttribute("playerInformation", playerInformation);
        return "knight";
    }

    @RequestMapping("/knight/delete/{id}")
    public String deleteKnight(@PathVariable("id") Integer id) {
        service.deleteKnight(id);
        return "redirect:/knights";
    }
}
