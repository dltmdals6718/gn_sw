package com.example.demo.service;

import com.example.demo.DTO.CounselForm;
import com.example.demo.domain.Counsel;
import com.example.demo.repository.CounselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CounselService {

  private final CounselRepository counselRepository;

  @Autowired
  public CounselService(CounselRepository counselRepository) {
    this.counselRepository = counselRepository;
  }

  // 모든 Counsel 데이터를 가져오는 메서드
  public List<Counsel> findAll() {
    return counselRepository.findAll();
  }

  @Transactional
  public void saveCounsel(CounselForm counselForm) {
    Counsel counsel = new Counsel();
    counsel.setEmail(counselForm.getEmail());
    counsel.setContext(counselForm.getContext());
    counselRepository.save(counsel);
  }

  @Transactional //변경 + readonly false
  public Long join(Counsel counsel){
    counselRepository.save(counsel);
    return counsel.getId();   //em.persist 아직 db에 들어간 시점이 아니라도 값을 넣어줌
  }
}
