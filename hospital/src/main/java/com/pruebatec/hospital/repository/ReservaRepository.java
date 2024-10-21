package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query(value = "FROM Reserva r where r.id = :idReserva")
    Optional<Reserva> findById(@Param("idReserva") int id);

    @Query(value = "FROM Reserva r where UPPER(r.nombreCliente) = UPPER(:nombreCliente)")
    Optional<Reserva> findByNombre(@Param("nombreCliente") String nombre);
}
