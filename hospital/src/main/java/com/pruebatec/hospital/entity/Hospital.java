package com.pruebatec.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "HOSPITAL")
public class Hospital implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqHospital", sequenceName = "HOSPITAL_SEQ")
    @GeneratedValue(generator = "seqHospital")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

}
