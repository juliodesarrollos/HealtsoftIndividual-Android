package com.juliovazquez.hsind.Pojos;

import java.util.List;

public class Pojo_Paciente {
    private int id;
    private String nombre;
    private String fechaNacimiento;
    private String correo;
    private String telefono;
    private String fotoUrl;
    private String sexo;
    private static List<Pojo_Paciente> pacientes;

    public static void crear_instancia (List<Pojo_Paciente> nota) {
        pacientes = nota;
    }

    public static List<Pojo_Paciente> recuperar_instancia () {
        return pacientes;
    }

    public static void borrar_instancia () {
        pacientes = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Pojo_Paciente(int id, String nombre, String fechaNacimiento, String correo, String telefono, String fotoUrl, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.fotoUrl = fotoUrl;
        this.sexo = sexo;
    }
}
