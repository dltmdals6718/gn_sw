package com.example.demo.service;

import com.example.demo.DTO.SpotForm;
import com.example.demo.domain.Spot;
import com.example.demo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpotService {
  private final SpotRepository spotRepository;

  @Autowired
  public SpotService(SpotRepository spotRepository) {
    this.spotRepository = spotRepository;
  }

  public List<Spot> findAll(){
    return spotRepository.findAll();
  }

  @Transactional
  public void saveSpot(SpotForm spotForm){
    Spot spot = new Spot();
    spot.setAddress(spot.getAddress());
    spot.setDescription(spot.getDescription());
    spot.setUserName(spot.getUserName());
  }

}
