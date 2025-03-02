package com.greencode.dejandohuella.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;

@Entity
public class FootPrintRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg")
    private Long id;
    @Column(name = "personal_foot_print")
    private Double personalFootPrint;
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public FootPrintRecord() {

    }

    public FootPrintRecord(Long id, Double personalFootPrint, LocalDate date, User user) {
        this.id = id;
        this.personalFootPrint = personalFootPrint;
        this.date = date;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Double getPersonalFootPrint() {
        return personalFootPrint;
    }

    public void setPersonalFootPrint(Double personalFootPrint) {
        this.personalFootPrint = personalFootPrint;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
