package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Operaciones;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OperacionesRepository extends JpaRepository<Operaciones,Long>{

}
