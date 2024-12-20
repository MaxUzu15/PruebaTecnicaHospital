package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query(value = "FROM Reserva r where r.id = :idReserva")
    Optional<Reserva> findById(@Param("idReserva") int id);

    @Query(value = "FROM Reserva r where UPPER(r.nombreCliente) = UPPER(:nombreCliente)")
    Optional<Reserva> findByNombre(@Param("nombreCliente") String nombre);

    @Query(value = "FROM Reserva r ORDER BY r.id asc")
    List<Reserva> findAllReservas();

    @Query(value = "FROM Reserva r where r.fechaInicio >= :fechaInicio and r.estadoReserva = COALESCE(:estadoReserva, r.estadoReserva) ORDER BY r.fechaInicio asc")
    List<Reserva> findReport(@Param("fechaInicio") Timestamp fechaInicio,@Param("estadoReserva") String estadoReserva);
}
