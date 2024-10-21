package com.pruebatec.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "OPERACIONES")
public class Operaciones {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqOperaciones", sequenceName = "OPERACIONES_SEQ")
    @GeneratedValue(generator = "seqOperaciones")
    private int id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE")
    private Persona paciente;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DOCTOR")
    private Persona doctor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUARTO")
    private Cuarto cuarto;

    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;

    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;


}
