package com.greencode.dejandohuella.web.controller;

import com.greencode.dejandohuella.service.QuestionService;
import com.greencode.dejandohuella.persistence.entity.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/api/questions")
// Permite el acceso desde el front
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

}
