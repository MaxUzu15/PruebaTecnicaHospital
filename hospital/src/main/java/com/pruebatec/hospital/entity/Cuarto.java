package com.pruebatec.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "CUARTO")
public class Cuarto implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqCuarto", sequenceName = "CUARTO_SEQ")
    @GeneratedValue(generator = "seqCuarto")
    private int id;

    @Column(name = "NUMERO_CUARTO")
    private String numeroCuarto;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HOSPITAL")
    private Hospital hospital;

    @Column(name = "ESTATUS")
    private Integer estatus;


}
