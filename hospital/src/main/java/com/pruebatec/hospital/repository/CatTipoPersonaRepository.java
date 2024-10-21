package com.pruebatec.hospital.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebatec.hospital.entity.CatTipoPersona;

@Repository
public interface CatTipoPersonaRepository extends JpaRepository<CatTipoPersona,Long>{

}
