package com.greencode.dejandohuella.service;

import com.greencode.dejandohuella.repository.AnswerRepository;
import com.greencode.dejandohuella.persistence.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}
