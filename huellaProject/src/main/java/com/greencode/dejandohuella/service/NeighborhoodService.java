package com.greencode.dejandohuella.service;

import com.greencode.dejandohuella.repository.NeighborhoodRepository;
import com.greencode.dejandohuella.persistence.entity.Neighborhood;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NeighborhoodService {
    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    public List<Neighborhood> findAll() {
        return this.neighborhoodRepository.findAll();
    }

    public Neighborhood find(Long id) {
        return this.neighborhoodRepository.findById(id).orElse(null);
    }

    public Neighborhood save(Neighborhood neighborhood) {
        return this.neighborhoodRepository.save(neighborhood);
    }

    public void delete(Long id_neig) {
        this.neighborhoodRepository.deleteById(id_neig);
    }

    public boolean exists(Long id) {
        return this.neighborhoodRepository.existsById(id);
    }


}
