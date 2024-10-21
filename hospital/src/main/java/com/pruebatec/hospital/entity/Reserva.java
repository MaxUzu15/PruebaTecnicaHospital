package com.pruebatec.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "RESERVA")
public class Reserva implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;

    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUARTO")
    private Cuarto cuarto;

    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;

    @Column(name = "ESTADO_RESERVA")
    private String estadoReserva;

}
