package com.pruebatec.hospital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDepartamento implements Serializable {

    @JsonProperty("numero")
    private int numero;

    @JsonProperty("departamento")
    private String departamento;

    @JsonProperty("nombreCorto")
    private String nombreCorto;

    @JsonProperty("direccion")
    private String direccion;
}
