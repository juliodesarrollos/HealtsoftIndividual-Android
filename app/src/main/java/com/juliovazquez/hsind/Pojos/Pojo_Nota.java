package com.juliovazquez.hsind.Pojos;

import java.util.List;

public class Pojo_Nota {
    private int id;
    private String nota;
    private int idPaciente;
    private String created_at;
    private String updated_at;
    private static List<Pojo_Nota> pojoNotas;

    public Pojo_Nota(int id, String nota, int idPaciente, String created_at, String updated_at) {
        this.id = id;
        this.nota = nota;
        this.idPaciente = idPaciente;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static void crear_instancia (List<Pojo_Nota> pojoNota) {
        pojoNotas = pojoNota;
    }

    public static List<Pojo_Nota> recuperar_instancia () {
        return pojoNotas;
    }

    public static void borrar_instancia () {
        pojoNotas = null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
