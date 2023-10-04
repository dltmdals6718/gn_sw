package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SpotDTO {
    private String name;
    private String address;
    private String phone;
    private String place_id;
    private LocalDate date;
}
