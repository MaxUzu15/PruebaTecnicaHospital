package com.pruebatec.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import oracle.sql.TIMESTAMP;

import java.sql.Timestamp;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "PERSONA")
public class Persona {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqPersona", sequenceName = "PERSONA_SEQ")
    @GeneratedValue(generator = "seqPersona")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDOS")
    private String apellidos;

    @Column(name = "FECHA_NACIMIENTO")
    private Timestamp fechaNacimiento;

    @Column(name = "NUMERO_SEGURO")
    private String numeroSeguro;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PERSONA")
    private CatTipoPersona catTipoPersona;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESPECIALIDAD")
    private CatEspecialidad catEspecialidad;


}
