package com.greencode.dejandohuella.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Neighborhood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_neig")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "total_foot_print")
    private Double totalFootPrint;
    @OneToMany(mappedBy = "neighborhood", fetch = FetchType.LAZY)
    @Transient
    private List<User> user = new ArrayList<>();

    public Neighborhood(String name, String city) {
        this.name = name;
        this.city = city;
        this.totalFootPrint = 0.0;
    }

    public Neighborhood() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTotalFootPrint() {
        return totalFootPrint;
    }

    public void setTotalFootPrint(Double totalFootPrint) {
        this.totalFootPrint = totalFootPrint;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


}
