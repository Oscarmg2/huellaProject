package com.greencode.dejandohuella.service;

import com.greencode.dejandohuella.repository.FootPrintRecordRepository;
import com.greencode.dejandohuella.repository.UserRepository;
import com.greencode.dejandohuella.persistence.entity.FootPrintRecord;
import com.greencode.dejandohuella.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class FootprintRecordService {
    private final FootPrintRecordRepository footPrintRecordRepository;
    private final UserRepository userRepository;

    @Autowired
    public FootprintRecordService(FootPrintRecordRepository footPrintRecordRepository, UserRepository userRepository) {
        this.footPrintRecordRepository = footPrintRecordRepository;
        this.userRepository = userRepository;
    }

    public FootPrintRecord saveRecord(FootPrintRecord record) {
        if (record.getUser() == null || record.getUser().getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Se requiere un usuario con email");
        }

        // Buscar el usuario por email
        User user = userRepository.findByEmail(record.getUser().getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Asignar el usuario a la huella antes de guardarla
        record.setUser(user);

        return footPrintRecordRepository.save(record);
    }

    public List<FootPrintRecord> getRecordsByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return user.getRecords(); // Devuelve los registros del usuario
    }
}
