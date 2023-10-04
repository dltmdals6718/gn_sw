package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Spot {

  @Id @GeneratedValue
  @Column(name = "SPOT_ID")
  private Long id;

  private String spotName;
  private String address;
  private String description;

  @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SpotComment> spotComments = new ArrayList<>();




}
