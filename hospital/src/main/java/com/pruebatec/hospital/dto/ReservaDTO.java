package com.pruebatec.hospital.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pruebatec.hospital.Constants;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("fechaInicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = Constants.FORMAT_DATE_GENERAL,timezone = Constants.TIME_ZONE_MEXICO)
    private String fehcaInicio;

    @JsonProperty("fechaFin")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = Constants.FORMAT_DATE_GENERAL,timezone = Constants.TIME_ZONE_MEXICO)
    private String fehcaFin;

    @JsonProperty("idHabitacion")
    private int idHabitacion;

    @JsonProperty("nombreCliente")
    private String nombreCliente;

    @JsonProperty("estadoReserva")
    private String estadoReserva;


}
