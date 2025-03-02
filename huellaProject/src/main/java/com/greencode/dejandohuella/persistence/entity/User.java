package com.greencode.dejandohuella.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") // Asegúrate de que el nombre coincide con tu BD
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    //Nuevoo
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FootPrintRecord> records = new ArrayList<>();


    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<FootPrintRecord> getRecords() {
        return records;
    }

    public void setRecords(List<FootPrintRecord> records) {
        this.records = records;
    }
}

