package com.pruebatec.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "CAT_TIPO_PERSONA")
public class CatTipoPersona implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(allocationSize = 1, name = "seqCatTipoPersona", sequenceName = "CAT_TIPO_PERSONA_SEQ")
    @GeneratedValue(generator = "seqCatTipoPersona")
    private int id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
