package com.example.demo.controller;

import com.example.demo.DTO.CounselForm;
import com.example.demo.domain.Counsel;
import com.example.demo.service.CounselService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class CounselController {
  private final CounselService counselService;

  @Autowired
  public CounselController(CounselService counselService) {
    this.counselService = counselService;
  }

  @PostMapping(value = "/counsels/new")
  public String create(@Valid CounselForm form, BindingResult result) {
    counselService.saveCounsel(form);
    return "redirect:/counsels";
  }

  @GetMapping("/counsels")
  public String showAllCounsels(Model model) {
    List<Counsel> allCounsels = counselService.findAll();
    model.addAttribute("counsels", allCounsels);
    return "counsels";
  }

  @GetMapping("/home")
  public String home(Model model) {
    // 여기에 모델에 데이터를 추가하거나 다른 로직을 추가할 수 있습니다.
    return "home"; // "home.html" 템플릿을 찾아 렌더링합니다.
  }
}
