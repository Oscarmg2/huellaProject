package com.greencode.dejandohuella.service;

import com.greencode.dejandohuella.repository.QuestionRepository;
import com.greencode.dejandohuella.persistence.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
