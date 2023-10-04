package com.example.demo.controller;

import com.example.demo.domain.Plan;
import com.example.demo.domain.SpotDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class ScheduleControllerBeta {

    @GetMapping("/setting")
    public String settingDay() {
        return "settingDayBeta";
    }

    @GetMapping("/schedule")
    public String scheduleForm(Model model) {
        return "makeScheduleBeta";
    }

    @PostMapping("/schedule")
    public String makeSchedule(@RequestBody List<SpotDTO> content) {
        for (SpotDTO spotDTO : content) {
            System.out.println("spotDTO = " + spotDTO);
        }
        return "redirect:/";
    }
}
