package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.CatEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatEspecialidadRepository extends JpaRepository<CatEspecialidad,Long> {

    @Query(value = "FROM CatEspecialidad ce where ce.clave = :clave")
    Optional<CatEspecialidad> findCatEspecialidadByClave (@Param("clave") String clave);
}
