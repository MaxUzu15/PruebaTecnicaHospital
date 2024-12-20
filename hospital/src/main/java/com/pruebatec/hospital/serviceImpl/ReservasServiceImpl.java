package com.pruebatec.hospital.serviceImpl;

import com.pruebatec.hospital.Constants;
import com.pruebatec.hospital.dto.ReservaDTO;
import com.pruebatec.hospital.entity.Cuarto;
import com.pruebatec.hospital.entity.Reserva;
import com.pruebatec.hospital.repository.CuartoRepository;
import com.pruebatec.hospital.repository.ReservaRepository;
import com.pruebatec.hospital.service.IReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservasServiceImpl implements IReservasService {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    CuartoRepository cuartoRepository;

    public String crearReserva(ReservaDTO reserva) {
        String resultado = "Reserva creada.";
        try {
            Optional<Reserva> reservaExistente = reservaRepository.findById(reserva.getId());
            if (reservaExistente.isPresent()) {
                resultado = "Esta reserva con este id ya ha sido creada previamente";
            } else {
                Reserva resrvaNueva = new Reserva();
                resrvaNueva.setId(reserva.getId());
                DateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
                Date date;
                Timestamp timeStampDate;
                resrvaNueva.setId(reserva.getId());
                if (reserva.getFehcaInicio() != null) {
                    date = formatter.parse(reserva.getFehcaInicio());
                    timeStampDate = new Timestamp(date.getTime());
                    resrvaNueva.setFechaInicio(timeStampDate);
                } else {
                    return "La fecha inicio es necesaria para crear la Reserva.";
                }

                if (reserva.getFehcaFin() != null) {
                    date = formatter.parse(reserva.getFehcaFin());
                    timeStampDate = new Timestamp(date.getTime());
                    resrvaNueva.setFechaFin(timeStampDate);
                } else {
                    return "La fecha fin es necesaria para crear la Reserva.";
                }
                Optional<Cuarto> cuartoExistente = cuartoRepository.findByIdCuarto(reserva.getIdHabitacion());
                if(cuartoExistente.isPresent()) {
                    resrvaNueva.setCuarto(cuartoExistente.get());
                }else{
                    return "El id de habitación no se ha encontrado";
                }

                resrvaNueva.setNombreCliente(reserva.getNombreCliente());
                resrvaNueva.setEstadoReserva(reserva.getEstadoReserva());
                reservaRepository.save(resrvaNueva);
            }
        } catch (Exception e) {
            return "Ocurrió un error al crear la reserva: " + e.getMessage();
        }
        return resultado;
    }

    public ReservaDTO consultaReserva(int id){
        ReservaDTO response = new ReservaDTO();
        Optional<Reserva> reserva = reservaRepository.findById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
        if(reserva.isPresent()){
            response.setId(reserva.get().getId());
            response.setFehcaInicio(dateFormat.format(reserva.get().getFechaInicio()));
            response.setFehcaFin(dateFormat.format(reserva.get().getFechaFin()));
            response.setIdHabitacion(reserva.get().getCuarto().getId());
            response.setNombreCliente(reserva.get().getNombreCliente());
            response.setEstadoReserva(reserva.get().getEstadoReserva());
        }
        return response;
    }

    public String actualizarReserva(int id, ReservaDTO actualizacion){
        String resultado = "Reserva actualizada.";
        try {
            Optional<Reserva> reserva = reservaRepository.findById(id);
            if (reserva.isPresent()) {
                DateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
                Date date;
                Timestamp timeStampDate;

                Reserva reservaActualizar = reserva.get();
                reservaActualizar.setId(actualizacion.getId());
                if (actualizacion.getFehcaInicio() != null) {
                    date = formatter.parse(actualizacion.getFehcaInicio());
                    timeStampDate = new Timestamp(date.getTime());
                    reservaActualizar.setFechaInicio(timeStampDate);
                }
                if (actualizacion.getFehcaFin() != null) {
                    date = formatter.parse(actualizacion.getFehcaFin());
                    timeStampDate = new Timestamp(date.getTime());
                    reservaActualizar.setFechaFin(timeStampDate);
                }
                Optional<Cuarto> cuartoExistente = cuartoRepository.findByIdCuarto(actualizacion.getIdHabitacion());
                if(cuartoExistente.isPresent()) {
                    reservaActualizar.setCuarto(cuartoExistente.get());
                }else{
                    return "El id de habitación no se ha encontrado";
                }
                reservaActualizar.setNombreCliente(actualizacion.getNombreCliente());
                reservaActualizar.setEstadoReserva(actualizacion.getEstadoReserva());
                reservaRepository.save(reservaActualizar);
            }else{
                return "El id de la reserva a actualizar no existe";
            }
        }catch (Exception e){
            return "Ocurrió un error al Actualizar la reserva: " + e.getMessage();
        }
        return resultado;
    }

    public String elimiarReserva(int id){
        String resultado = "Confirmación de eliminación.";
        try {
            Optional<Reserva> reserva = reservaRepository.findById(id);
            if (reserva.isPresent()) {
                reservaRepository.delete(reserva.get());
            } else {
                return "Este id de reserva no existe";
            }
        }catch (Exception e){
            return "Ocurrió un error al eliminar la reserva: " + e.getMessage();
        }
        return resultado;
    }

    public List<ReservaDTO> consultaReservas(){
        try {
            List<ReservaDTO> reservaDTO = new ArrayList<>();

            List<Reserva> reservas = reservaRepository.findAllReservas();
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
            if(reservas != null &&reservas.size() > 0){
                for(Reserva reserva : reservas){
                    ReservaDTO res = new ReservaDTO();
                    res.setId(reserva.getId());
                    res.setFehcaInicio(dateFormat.format(reserva.getFechaInicio()));
                    res.setFehcaFin(dateFormat.format(reserva.getFechaFin()));
                    res.setIdHabitacion(reserva.getCuarto().getId());
                    res.setNombreCliente(reserva.getNombreCliente());
                    res.setEstadoReserva(reserva.getEstadoReserva());
                    reservaDTO.add(res);
                }
                return reservaDTO;
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al obtener las reservas: " + e.getMessage());
        }
        return null;
    }

    public List<ReservaDTO> consultaReporte(ReservaDTO reserva){
        try {
            List<ReservaDTO> reservaDTO = new ArrayList<>();
            DateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
            Date date;
            Timestamp timeStampDate =null;
            String estatusReserva = null;
            if (reserva.getFehcaInicio() != null) {
                date = formatter.parse(reserva.getFehcaInicio());
                timeStampDate = new Timestamp(date.getTime());
            }
            if(reserva.getEstadoReserva() != null && !reserva.getEstadoReserva().isEmpty()){
                estatusReserva = reserva.getEstadoReserva();
            }
            List<Reserva> reservas = reservaRepository.findReport(timeStampDate,estatusReserva);
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_GENERAL);
            if(reservas != null &&reservas.size() > 0){
                for(Reserva reservaR : reservas){
                    ReservaDTO res = new ReservaDTO();
                    res.setId(reservaR.getId());
                    res.setFehcaInicio(dateFormat.format(reservaR.getFechaInicio()));
                    res.setFehcaFin(dateFormat.format(reservaR.getFechaFin()));
                    res.setIdHabitacion(reservaR.getCuarto().getId());
                    res.setNombreCliente(reservaR.getNombreCliente());
                    res.setEstadoReserva(reservaR.getEstadoReserva());
                    reservaDTO.add(res);
                }
                return reservaDTO;
            }

        }catch (Exception e){
            System.out.println("Ocurrio un error al obtener las reservas: " + e.getMessage());
        }
        return null;
    }
}
