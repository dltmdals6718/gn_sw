package com.example.demo.api;

import com.example.demo.domain.Counsel;
import com.example.demo.service.CounselService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CounselApiController {
  private final CounselService counselService;

  @PostMapping("/api/counsels")  //email을 다른이름으로 바꾸면 오류남 -> DTO(CreateCounselRequest)로 받으면 더 안정적! 이거 추천
  public CreateCounselResponse saveCounsel(@RequestBody @Valid CreateCounselRequest request){
    Counsel counsel = new Counsel();
    counsel.setEmail(request.getEmail());
    counsel.setContext(request.getContext());

    Long id = counselService.join(counsel);
    return new CreateCounselResponse(id);
  }

  @Data
  static class CreateCounselRequest{
    @NotEmpty
    private String email;

    private String context;
  }

  @Data
  static class CreateCounselResponse{
    private Long id;

    public CreateCounselResponse(Long id) {
      this.id = id;
    }
  }

  @PutMapping("/api/counsels/{id}")
  public UpdateCounselResponse updateCounsel(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateCounselRequest request){
    counselService.update(id, request.getEmail(), request.getContext());
    Counsel findCounsel = counselService.findOne(id);
    return new UpdateCounselResponse(findCounsel.getId(), findCounsel.getEmail(), findCounsel.getContext());
  }

  @Data
  static class UpdateCounselRequest{
    private String email;
    private String context;
  }

  @Data
  @AllArgsConstructor
  static class UpdateCounselResponse{
    private Long id;
    private String email;
    private String context;
  }

  @GetMapping("/api/counsels")
  public Result counsels(){
    List<Counsel> findCounsels = counselService.findCounsels();
    List<CounselDto> collect = findCounsels.stream()
        .map(c -> new CounselDto(c.getEmail(), c.getContext()))
        .collect(Collectors.toList());

    return new Result(collect);
  }

  @Data
  @AllArgsConstructor
  static class Result<T>{
    private T data;
  }

  @Data
  @AllArgsConstructor
  static class CounselDto{
    private String email;
    private String context;
  }

}