package com.pruebatec.hospital.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pruebatec.hospital.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEmpleado implements Serializable {

    @JsonProperty("numeroEmpleado")
    private int numeroEmpleado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellidos")
    private String apellidos;

    @JsonProperty("edad")
    private int edad;

    @JsonProperty("fechaIngreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = Constants.FORMAT_DATE,timezone = Constants.TIME_ZONE_MEXICO)
    private String fechaIngreso;

    @JsonProperty("areaTrabajo")
    private ResponseDepartamento areaTrabajo;


}
