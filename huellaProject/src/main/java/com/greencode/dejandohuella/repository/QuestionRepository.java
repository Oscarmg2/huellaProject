package com.greencode.dejandohuella.repository;

import com.greencode.dejandohuella.persistence.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
