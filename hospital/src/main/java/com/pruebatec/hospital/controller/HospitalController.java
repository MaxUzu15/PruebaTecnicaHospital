package com.pruebatec.hospital.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatec.hospital.dto.AreaTrabajo;
import com.pruebatec.hospital.dto.Empleado;
import com.pruebatec.hospital.dto.OperacionesDTO;
import com.pruebatec.hospital.dto.ReservaDTO;
import com.pruebatec.hospital.service.OperacionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/practica")
public class HospitalController {

    @Autowired
    private OperacionesService operacionesService;

    @PostMapping("/operacion")
    public ResponseEntity<?> operaciones(@RequestBody OperacionesDTO operacionesDTO) {
        try {
            ResponseEntity<?> response = operacionesService.separarPeticiones(operacionesDTO);

            return ResponseEntity.ok(response);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

}
