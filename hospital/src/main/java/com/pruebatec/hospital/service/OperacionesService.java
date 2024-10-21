package com.pruebatec.hospital.service;

import com.pruebatec.hospital.dto.OperacionesDTO;
import org.springframework.http.ResponseEntity;

public interface OperacionesService {
    public ResponseEntity<?> separarPeticiones(OperacionesDTO operacionesDTO);
}
