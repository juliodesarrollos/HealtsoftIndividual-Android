package com.juliovazquez.hsind.Pojos;

import com.juliovazquez.hsind.Activities.Agenda.DrawableCalendarEvent;

import java.util.List;

public class Pojo_Evento {
    int id;
    String title;
    String description;
    String color;
    String start;
    String telefono;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    int idPaciente;
    public static Pojo_Evento evento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static void crear_instancia(Pojo_Evento eventos) {
        if (evento == null) {
            evento = eventos;
        }
    }

    public static Pojo_Evento recuperar_instancia() {
        return evento;
    }

    public static void borar_instancia() {
        evento = null;
    }

}
