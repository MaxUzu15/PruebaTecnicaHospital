package com.pruebatec.hospital.repository;

import com.pruebatec.hospital.entity.Hospital;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
