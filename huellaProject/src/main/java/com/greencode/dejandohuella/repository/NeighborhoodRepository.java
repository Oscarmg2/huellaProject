package com.greencode.dejandohuella.repository;

import com.greencode.dejandohuella.persistence.entity.Neighborhood;
import org.springframework.data.repository.ListCrudRepository;

public interface NeighborhoodRepository extends ListCrudRepository <Neighborhood, Long> {
}
