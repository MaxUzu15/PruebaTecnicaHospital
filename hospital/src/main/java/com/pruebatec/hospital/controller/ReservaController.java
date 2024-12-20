package com.pruebatec.hospital.controller;

import com.pruebatec.hospital.dto.ReservaDTO;
import com.pruebatec.hospital.service.IReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practica/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {
    @Autowired
    private IReservasService iReservasService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reserva) {
        try {
            String estatusReserva = iReservasService.crearReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(estatusReserva);
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reserva: "+e.getMessage());
        }

    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ReservaDTO> crearReserva(@PathVariable Integer id){
        try {
            ReservaDTO reservaDTO = iReservasService.consultaReserva(id);
            return ResponseEntity.ok(reservaDTO);
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> actualizarReserva(@PathVariable Integer id, @RequestBody ReservaDTO paciente) {
        try {
            String estatusReserva = iReservasService.actualizarReserva(id,paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(estatusReserva);
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actulizar la reserva: "+e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> eliminarReserva(@PathVariable Integer id) {
        try {
            String estatusReserva = iReservasService.elimiarReserva(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(estatusReserva);
        }catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la reserva: "+e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/consultaReservas")
    public ResponseEntity<List<ReservaDTO>> consultarReservas(){
        try {
            List<ReservaDTO> reservaDTO = iReservasService.consultaReservas();
            return ResponseEntity.ok(reservaDTO);
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reporte")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ReservaDTO>> crearReporte(@RequestBody ReservaDTO reserva) {
        try {
            List<ReservaDTO> reservaDTO = iReservasService.consultaReporte(reserva);
            return ResponseEntity.ok(reservaDTO);
        }catch (Exception e) {
            return  ResponseEntity.notFound().build();
        }

    }
}
