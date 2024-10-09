package com.juliovazquez.hsind.Pojos;

import com.juliovazquez.hsind.Activities.Agenda.DrawableCalendarEvent;

import java.util.List;

public class Pojo_Drawable_Event {
    public static List <DrawableCalendarEvent> eventosDra;

    public static void crear_instancia(List <DrawableCalendarEvent> eventos) {
        if (eventosDra == null) {
            eventosDra = eventos;
        }
    }

    public static List <DrawableCalendarEvent> recuperar_instancia() {
        return eventosDra;
    }

    public static void borar_instancia() {
        eventosDra = null;
    }

}
