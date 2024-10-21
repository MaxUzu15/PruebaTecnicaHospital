package com.pruebatec.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "CAT_ESPECIALIDAD")
public class CatEspecialidad implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqCatEspecialidad", sequenceName = "CAT_ESPECIALIDAD_SEQ")
    @GeneratedValue(generator = "seqCatEspecialidad")
    private int id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CLAVE")
    private String clave;

    @Column(name = "DIRECCION")
    private String direccion;

}
