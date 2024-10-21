package com.pruebatec.hospital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperacionesDTO implements Serializable {

    @JsonProperty("operacion")
    private String operacion;

    @JsonProperty("REGISTRO")
    private String registro;

    @JsonProperty("DATOS")
    private String datos;

}
