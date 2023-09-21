package com.example.demo.repository;

import com.example.demo.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRepository extends JpaRepository<Counsel, Long> {
  // 특별한 메서드 정의 없이 JpaRepository를 확장하면 기본적인 CRUD(Create, Read, Update, Delete) 기능을 사용할 수 있습니다.
}

