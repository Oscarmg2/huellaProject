package com.greencode.dejandohuella.web.controller;

import com.greencode.dejandohuella.service.FootprintRecordService;
import com.greencode.dejandohuella.persistence.entity.FootPrintRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/records")
public class FootprintRecordController {
    private final FootprintRecordService footprintRecordService;

    @Autowired
    public FootprintRecordController(FootprintRecordService footprintRecordService) {
        this.footprintRecordService = footprintRecordService;
    }

    @PostMapping
    public ResponseEntity<?> saveRecord(@RequestBody FootPrintRecord record) {
        try {
            FootPrintRecord savedRecord = footprintRecordService.saveRecord(record);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<FootPrintRecord>> getRecordsByUser(@PathVariable String email) {
        List<FootPrintRecord> records = footprintRecordService.getRecordsByUser(email);
        return ResponseEntity.ok(records); // Devuelve los registros del usuario
    }
//    @Autowired
//    private FootPrintRecordRepository footPrintRecordRepository;
//
//    @Autowired
//    private UserRepository userRepository;  // <-- Agregar esto
//
//    @PostMapping
//    public ResponseEntity<?> saveRecord(@RequestBody FootPrintRecord record) {
//        if (record.getUser() == null || record.getUser().getEmail() == null) {
//            return ResponseEntity.badRequest().body("Error: Se requiere un usuario con email");
//        }
//
//        // Buscar el usuario por email
//        User user = userRepository.findByEmail(record.getUser().getEmail())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
//
//        // Asignar el usuario a la huella antes de guardarla
//        record.setUser(user);
//
//        FootPrintRecord savedRecord = footPrintRecordRepository.save(record);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
//    }
//
//    @GetMapping("/{email}")
//    public ResponseEntity<List<FootPrintRecord>> getRecordsByUser(@PathVariable String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
//
//        return ResponseEntity.ok(user.getRecords()); // Devuelve los registros del usuario
//    }

}
