package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query(value = "FROM Persona p where p.id = :idPersona")
    Optional<Persona> findPersonaById (@Param("idPersona") int id);
}
