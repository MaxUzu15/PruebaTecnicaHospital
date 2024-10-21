package com.pruebatec.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleado {
    private int numeroEmpleado;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private AreaTrabajo areaTrabajo;
}
