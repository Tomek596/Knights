package com.tomq.kursspring.controllers;

import com.tomq.kursspring.domain.Knight;
import com.tomq.kursspring.services.KnightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
