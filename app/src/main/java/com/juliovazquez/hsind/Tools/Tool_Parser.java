package com.juliovazquez.hsind.Tools;

import android.content.Context;
import android.graphics.Color;

import com.google.gson.Gson;
import com.juliovazquez.hsind.Activities.Agenda.DrawableCalendarEvent;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Evento;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.Pojos.Pojo_Nota;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Signos_Vitales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tool_Parser {

    public static Pojo_Evento ParserEvento(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray eventoArray = jsonObject.getJSONArray("evento");
            JSONObject eventObject = eventoArray.getJSONObject(0);
            Gson g = new Gson();
            Pojo_Evento pojo_evento = g.fromJson(String.valueOf(eventObject), Pojo_Evento.class);
            return pojo_evento;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Pojo_Historia_Clinica ParserHC(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray pacienteArray = jsonObject.getJSONArray("paciente");
            JSONObject pacienteObject = pacienteArray.getJSONObject(0);
            Gson g = new Gson();
            Pojo_Historia_Clinica pojoHistoria_clinica = g.fromJson(String.valueOf(pacienteObject), Pojo_Historia_Clinica.class);
            int count_notas = jsonObject.getInt("count_notas");
            if (count_notas > 0) {
                List<Pojo_Nota> pojoNotas = new ArrayList<>();
                JSONArray notasArray = jsonObject.getJSONArray("notas");
                for (int i = 0; i < notasArray.length(); i++) {
                    pojoNotas.add(g.fromJson(String.valueOf(notasArray.getJSONObject(i)), Pojo_Nota.class));
                }
                pojoHistoria_clinica.setPojoNotas(pojoNotas);
            } else pojoHistoria_clinica.setPojoNotas(null);
            int count_signos = jsonObject.getInt("count_signos");
            if (count_signos > 0) {
                pojoHistoria_clinica.setPojoSignos_vitales(g.fromJson(String.valueOf(jsonObject.getJSONArray("signos").getJSONObject(0)), Pojo_Signos_Vitales.class));
            } else pojoHistoria_clinica.setPojoNotas(null);
            return pojoHistoria_clinica;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Pojo_Paciente> ParserPacientes(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            int count = jsonObject.getInt("count");
            JSONArray arrayObject = jsonObject.getJSONArray("pacientes");
            List<Pojo_Paciente> pacientes = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                JSONObject jsonPacientes = arrayObject.getJSONObject(i);
                int id = jsonPacientes.getInt("id");
                String nombre = jsonPacientes.getString("nombre");
                String fechaNacimiento = jsonPacientes.getString("fechaNacimiento");
                String correo = jsonPacientes.getString("correo");
                String telefono = jsonPacientes.getString("telefono");
                String fotoUrl = jsonPacientes.getString("fotoUrl");
                String sexo = jsonPacientes.getString("sexo");
                Pojo_Paciente paciente = new Pojo_Paciente(id, nombre, fechaNacimiento, correo, telefono, fotoUrl, sexo);
                pacientes.add(paciente);
            }
            return pacientes;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<DrawableCalendarEvent> ParserEvento(String content, Context context) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            int count = jsonObject.getInt("count");
            JSONArray arrayObject = jsonObject.getJSONArray("eventos");
            List<DrawableCalendarEvent> eventos = new ArrayList<>();

            Calendar startTime3 = Calendar.getInstance();
            Calendar endTime3 = Calendar.getInstance();
            startTime3.set(Calendar.HOUR_OF_DAY, 14);
            startTime3.set(Calendar.MINUTE, 0);
            endTime3.set(Calendar.HOUR_OF_DAY, 15);
            endTime3.set(Calendar.MINUTE, 0);

            for (int i = 0; i < count; i++) {
                JSONObject jsonEvent = arrayObject.getJSONObject(i);
                long id = jsonEvent.getInt("idEvent");
                String title = jsonEvent.getString("title");
                String description = jsonEvent.getString("name");
                String color = jsonEvent.getString("color");
                String location = jsonEvent.getString("nombre");
                String desc = jsonEvent.getString("description");
                String start = jsonEvent.getString("start");
                String end = jsonEvent.getString("end");
                long numero = jsonEvent.getLong("telefono");
                Calendar cds = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cds.setTime(sdf.parse(start));// all done
                int colorEvent = Color.parseColor(color);
                DrawableCalendarEvent evento = new DrawableCalendarEvent(id, colorEvent, title, description, location, cds.getTimeInMillis(), numero, 0, desc, 0);
//                DrawableCalendarEvent evento = new DrawableCalendarEvent(title, description, location,
//                        colorEvent,cds , cds, false, android.R.drawable.ic_dialog_info);
                eventos.add(evento);
            }
            return eventos;
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Pojo_Codigo_Error ParserError(String content) {
        try {
            JSONObject jsonObject = new JSONObject(content);
            Boolean estado = jsonObject.getBoolean("conexion");
            String id = jsonObject.getString("error");
            String mensaje = jsonObject.getString("mensaje");
            Pojo_Codigo_Error pojoCodigoError = new Pojo_Codigo_Error(estado, id, mensaje);
            return pojoCodigoError;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> ParserUsuario(String content) {
        try {
            ArrayList<String> usuario = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(content);
            JSONArray arrayObject = jsonObject.getJSONArray("usuario");
            JSONObject usuarioObject = arrayObject.getJSONObject(0);
            int id = usuarioObject.getInt("id");
            String name = usuarioObject.getString("name");
            String email = usuarioObject.getString("email");
            String password = usuarioObject.getString("password");
            String fs = usuarioObject.getString("fechaSuscripcion");
            String ts = usuarioObject.getString("tipoSuscripcion");
            usuario.add(String.valueOf(id));
            usuario.add(name);
            usuario.add(email);
            usuario.add(password);
            if (fs.equals("") || fs == null || fs == "" || fs.equals("null"))
                usuario.add("2019-10-10");
            else usuario.add(fs);
            usuario.add(ts);
            return usuario;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
