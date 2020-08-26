package com.tomq.kursspring.controllers;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.services.KnightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KnightController {

    private final KnightService service;

    public KnightController(KnightService service) {
        this.service = service;
    }

    @RequestMapping("/knights")
    public String getKnights(Model model) {
        List<Knight> allKnights = service.getAllKnights();
        model.addAttribute("knights", allKnights);
        return "knights";
    }

    @RequestMapping(value = "/knights", method = RequestMethod.POST)
    public String getKnights(Knight knight) {
        service.saveKnight(knight);
        return "redirect:/knights";
    }

    @RequestMapping("/newknight")
    public String createKnight(Model model) {
        model.addAttribute("knight", new Knight());
        return "knightform";
    }

    @RequestMapping("/knight")
    public String getKnight(@RequestParam("id") Integer id, Model model) {
        Knight knight = service.getKnight(id);
        model.addAttribute("knight", knight);
        return "knight";
    }

    @RequestMapping("/knight/delete/{id}")
    public String deleteKnight(@PathVariable("id") Integer id) {
        service.deleteKnight(id);
        return "redirect:/knights";
    }
}
