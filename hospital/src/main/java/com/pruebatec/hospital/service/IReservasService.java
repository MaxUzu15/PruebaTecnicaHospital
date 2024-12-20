package com.pruebatec.hospital.service;

import com.pruebatec.hospital.dto.ReservaDTO;
import com.pruebatec.hospital.entity.Reserva;

import java.util.List;

public interface IReservasService {
    public String crearReserva(ReservaDTO reserva);

    public ReservaDTO consultaReserva(int id);

    public String actualizarReserva(int id, ReservaDTO actualizacion);

    public String elimiarReserva(int id);

    public List<ReservaDTO> consultaReservas();

    public List<ReservaDTO> consultaReporte(ReservaDTO reserva);
}
