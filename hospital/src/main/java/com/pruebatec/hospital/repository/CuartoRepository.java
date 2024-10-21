package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Cuarto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CuartoRepository extends JpaRepository<Cuarto,Long>{

    @Query(value = "FROM Cuarto c where c.id = :idCuarto")
    Optional<Cuarto> findByIdCuarto(@Param("idCuarto") int idCuarto);

}
