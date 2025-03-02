package com.greencode.dejandohuella.web.controller;

import com.greencode.dejandohuella.service.NeighborhoodService;
import com.greencode.dejandohuella.persistence.entity.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/neighborhood")
public class NeighborhoodController {
    private final NeighborhoodService neighborhoodService;

    @Autowired
    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping
    public ResponseEntity<List<Neighborhood>> gertAll(){
        return ResponseEntity.ok(this.neighborhoodService.findAll());
    }

    @GetMapping("/{id_neig}")
    public ResponseEntity<?> get(@PathVariable Long id_neig){
        try{
            if (this.neighborhoodService.exists(id_neig)){
                return ResponseEntity.ok(this.neighborhoodService.find(id_neig));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ese barrio no existe!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Neighborhood neighborhood){
        try{
            if(neighborhood.getId() == null || !this.neighborhoodService.exists(neighborhood.getId())){
                return ResponseEntity.ok(this.neighborhoodService.save(neighborhood));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ese barrio ya existe!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Neighborhood neighborhood){
        try{
            if(neighborhood.getId() != null || this.neighborhoodService.exists(neighborhood.getId())){
                return ResponseEntity.ok(this.neighborhoodService.save(neighborhood));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ese barrio no existe!");
       }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id_neig}")
    public ResponseEntity<?> delete(@PathVariable Long id_neig){
        try{
            if (this.neighborhoodService.exists(id_neig)){
                this.neighborhoodService.delete(id_neig);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ese barrio no existe!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
