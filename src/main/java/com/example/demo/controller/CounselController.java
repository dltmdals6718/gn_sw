package com.example.demo.controller;

import com.example.demo.DTO.CounselForm;
import com.example.demo.domain.Counsel;
import com.example.demo.repository.CounselRepository;
import com.example.demo.service.CounselService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CounselController {
  private final CounselService counselService;
  private final CounselRepository counselRepository;

  @Autowired
  public CounselController(CounselService counselService, CounselRepository counselRepository) {
    this.counselService = counselService;
    this.counselRepository = counselRepository;
  }

  @PostMapping(value = "/counsels/new")
  public String create(@Valid CounselForm form, BindingResult result) {
    counselService.saveCounsel(form);
    return "redirect:/counsels";
  }

  @GetMapping("/counsels")
  public String showAllCounsels(Model model, @PageableDefault(sort = "id", value = 9) Pageable pageable){

    // 총 문의글 수를 모델에 추가
    Long totalCounsels = counselService.getTotalCounselsCount();
    model.addAttribute("totalCounsels", totalCounsels);


    Page<Counsel> pageList = counselService.pageList(pageable);

    model.addAttribute("counsels", pageList);
    model.addAttribute("counselSize", pageList.isEmpty()); // 데이터가 비었는지 체크용

    /* 이전 및 다음 페이지 넘버 가져옴 */
    model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
    model.addAttribute("next", pageable.next().getPageNumber());

    /* 이전 및 다음 페이지가 존재하는지 체크용 */
    model.addAttribute("hasNext", pageList.hasNext());
    model.addAttribute("hasPrevious", pageList.hasPrevious());

    /* 페이지 이동 링크 */
    int endPage = (int)(Math.ceil((pageable.getPageNumber()+1)/10.0)*10); // 10, 20, 30, ...
    int startPage = endPage-9; // 10개씩 보여주기 -1 = 9
    if(endPage>pageList.getTotalPages())
      endPage=pageList.getTotalPages();

    model.addAttribute("endPage", endPage);
    model.addAttribute("startPage", startPage);

    return "counsels";
  }

  @GetMapping("/home")
  public String home(Model model) {
    // 여기에 모델에 데이터를 추가하거나 다른 로직을 추가할 수 있습니다.
    return "home"; // "home.html" 템플릿을 찾아 렌더링합니다.
  }
}
