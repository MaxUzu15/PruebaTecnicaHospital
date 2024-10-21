package com.pruebatec.hospital.serviceImpl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.pruebatec.hospital.Constants;
import com.pruebatec.hospital.dto.*;
import com.pruebatec.hospital.entity.CatEspecialidad;
import com.pruebatec.hospital.entity.Persona;
import com.pruebatec.hospital.entity.Reserva;
import com.pruebatec.hospital.repository.CatEspecialidadRepository;
import com.pruebatec.hospital.repository.PersonaRepository;
import com.pruebatec.hospital.repository.ReservaRepository;
import com.pruebatec.hospital.service.OperacionesService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class OperacionesServiceImpl implements OperacionesService {

    @Autowired
    private CatEspecialidadRepository catEspecialidadRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public ResponseEntity<?> separarPeticiones(OperacionesDTO operacionesDTO) {
        if (operacionesDTO.getRegistro().equals("areaTrabajo")) {
            return ResponseEntity.ok(departamento(operacionesDTO));
        } else if (operacionesDTO.getRegistro().equals("EMPLEADO")) {
            return ResponseEntity.ok(empleado(operacionesDTO));
        }
        
        return ResponseEntity.ok("No ha seleccionado una opcion valida en REGISTRO");
    }

    public ResponseDepartamento departamento(OperacionesDTO operacionesDTO) {
        ResponseDepartamento responseDepartamento;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            AreaTrabajo areaTrabajo = xmlMapper.readValue(operacionesDTO.getDatos(), AreaTrabajo.class);
            switch (operacionesDTO.getOperacion()) {
                case "ALTA":
                    return altaDepartamento(areaTrabajo);
                case "MODIFICACION":
                    return modificacionDepartamento(areaTrabajo);
                case "CONSULTA":
                    return consultaDepartamento(areaTrabajo);
                default:
                    responseDepartamento = new ResponseDepartamento();
                    break;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return responseDepartamento;
    }

    public ResponseDepartamento altaDepartamento(AreaTrabajo areaTrabajo) {

        Optional<CatEspecialidad> departamento = catEspecialidadRepository.findCatEspecialidadByClave(areaTrabajo.getNombreCorto());
        if (departamento.isPresent()) {
            return devolverResponseDepartamento(departamento.get());
        } else {
            CatEspecialidad especialidad = new CatEspecialidad();
            especialidad.setDescripcion(areaTrabajo.getDepartamento());
            especialidad.setClave(areaTrabajo.getNombreCorto());
            especialidad.setDireccion(areaTrabajo.getDireccion());
            catEspecialidadRepository.save(especialidad);
            return devolverResponseDepartamento(especialidad);
        }

    }

    public ResponseDepartamento modificacionDepartamento(AreaTrabajo areaTrabajo){
        Optional<CatEspecialidad> departamento = catEspecialidadRepository.findCatEspecialidadByClave(areaTrabajo.getNombreCorto());
        if (departamento.isPresent()) {
            CatEspecialidad especialidad = departamento.get();
            especialidad.setDescripcion(areaTrabajo.getDepartamento());
            especialidad.setClave(areaTrabajo.getNombreCorto());
            especialidad.setDireccion(areaTrabajo.getDireccion());
            catEspecialidadRepository.save(especialidad);
            return devolverResponseDepartamento(especialidad);
        } else {
            throw new ServiceException("No se encontro el departamento buscado");
        }
    }
    
    public ResponseDepartamento consultaDepartamento(AreaTrabajo areaTrabajo){
        Optional<CatEspecialidad> departamento = catEspecialidadRepository.findCatEspecialidadByClave(areaTrabajo.getNombreCorto());
        if (departamento.isPresent()) {
            return devolverResponseDepartamento(departamento.get());
        } else {
            throw new ServiceException("No se encontro el departamento buscado");
        }
    }

    

    public ResponseDepartamento devolverResponseDepartamento(CatEspecialidad especialidad) {
        ResponseDepartamento responseDepartamento = new ResponseDepartamento();
        responseDepartamento.setNumero(especialidad.getId());
        responseDepartamento.setDepartamento(especialidad.getDescripcion());
        responseDepartamento.setNombreCorto(especialidad.getClave());
        responseDepartamento.setDireccion(especialidad.getDireccion());
        return responseDepartamento;
    }


    public ResponseEmpleado empleado(OperacionesDTO operacionesDTO) {
        ResponseEmpleado responseEmpleado;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            Empleado empleado = xmlMapper.readValue(operacionesDTO.getDatos(), Empleado.class);
            switch (operacionesDTO.getOperacion()) {
                case "ALTA":
                    return altaEmpleado(empleado);
                case "MODIFICACION":
                    return modificarEmpleado(empleado);
                case "CONSULTA":
                    return consultaEmpleado(empleado);
                default:
                    responseEmpleado = new ResponseEmpleado();
                    break;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return responseEmpleado;
    }

    public ResponseEmpleado altaEmpleado(Empleado empleado){
        Optional<Persona> persona = personaRepository.findPersonaById(empleado.getNumeroEmpleado());
        try {
            DateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE);
            Date date;
            Timestamp timeStampDate;
            if (persona.isPresent()) {
                return devolverResponseEmpleado(persona.get());
            } else {
                Persona personaNueva = new Persona();
                personaNueva.setNombre(empleado.getNombre());
                personaNueva.setApellidos(empleado.getApellidos());
                date = formatter.parse(empleado.getFechaNacimiento());
                timeStampDate = new Timestamp(date.getTime());
                personaNueva.setFechaNacimiento(timeStampDate);
                Optional<CatEspecialidad> especialidad = catEspecialidadRepository.findCatEspecialidadByClave(empleado.getAreaTrabajo().getNombreCorto());
                if(especialidad.isPresent()) {
                    personaNueva.setCatEspecialidad(especialidad.get());
                }
                personaRepository.save(personaNueva);
                return devolverResponseEmpleado(personaNueva);
            }
        }catch (Exception e ){
            throw new ServiceException("Error al guardar empleado");
        }
    }

    public ResponseEmpleado consultaEmpleado(Empleado empleado){
        Optional<Persona> persona = personaRepository.findPersonaById(empleado.getNumeroEmpleado());
        if(persona.isPresent()){
            return devolverResponseEmpleado(persona.get());
        }else{
            throw new ServiceException("No existe el empleado con ese id");
        }
    }

    public ResponseEmpleado modificarEmpleado(Empleado empleado){
        Optional<Persona> persona = personaRepository.findPersonaById(empleado.getNumeroEmpleado());
        try {
            DateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE);
            Date date;
            Timestamp timeStampDate;
            if (persona.isPresent()) {
                Persona personaNueva = persona.get();
                personaNueva.setNombre(empleado.getNombre());
                personaNueva.setApellidos(empleado.getApellidos());
                date = formatter.parse(empleado.getFechaNacimiento());
                timeStampDate = new Timestamp(date.getTime());
                personaNueva.setFechaNacimiento(timeStampDate);
                Optional<CatEspecialidad> especialidad = catEspecialidadRepository.findCatEspecialidadByClave(empleado.getAreaTrabajo().getNombreCorto());
                if(especialidad.isPresent()) {
                    personaNueva.setCatEspecialidad(especialidad.get());
                }
                personaRepository.save(personaNueva);
                return devolverResponseEmpleado(personaNueva);
            } else {
                throw new ServiceException("No existe el empleado con el id a modificar");
            }
        }catch (Exception e ){
            throw new ServiceException("Error al guardar empleado");
        }
    }

    public ResponseEmpleado devolverResponseEmpleado(Persona persona){
        ResponseEmpleado empleado = new ResponseEmpleado();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE);
        empleado.setNumeroEmpleado(persona.getId());
        empleado.setNombre(persona.getNombre());
        empleado.setApellidos(persona.getApellidos());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(dateFormat.format(persona.getFechaNacimiento()), fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        empleado.setEdad(periodo.getYears());
        Optional<Reserva> reserva = reservaRepository.findByNombre(persona.getNombre());
        if(reserva.isPresent()){
            empleado.setFechaIngreso(dateFormat.format(reserva.get().getFechaInicio()));
        }
        empleado.setAreaTrabajo(devolverResponseDepartamento(persona.getCatEspecialidad()));
        return empleado;
    }
}
