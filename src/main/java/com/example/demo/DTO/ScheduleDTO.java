package com.example.demo.DTO;

import com.example.demo.domain.SpotDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ScheduleDTO {
    private List<SpotDTO> content;
    private List<String> memos;
}
