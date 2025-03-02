package com.greencode.dejandohuella.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int score;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Answer() {}

    public Answer(String text, int score, Question question) {
        this.text = text;
        this.score = score;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }

    public Question getQuestion() {
        return question;
    }
}
