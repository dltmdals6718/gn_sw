package com.example.demo.controller;

import com.example.demo.domain.Spot;
import com.example.demo.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SpotController {

    @Autowired
    private SpotService spotService;

    @GetMapping("/spotView")
    public String viewSpotList(Model model) {
        try {
            spotService.DataAndSaveToDB();
            List<Spot> spots = spotService.getAllSpots();
            model.addAttribute("spots", spots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "spotList";
    }
}
