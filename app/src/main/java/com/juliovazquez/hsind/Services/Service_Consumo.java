package com.juliovazquez.hsind.Services;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;
import com.juliovazquez.hsind.Activities.Agenda.DrawableCalendarEvent;
import com.juliovazquez.hsind.Pojos.Pojo_Evento;
import com.juliovazquez.hsind.Tools.Tool_Fecha;
import com.juliovazquez.hsind.Tools.Tool_Parser;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Drawable_Event;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.Pojos.Pojo_Nota;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Signos_Vitales;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;

public class Service_Consumo {

    public static Object actualizar_evento(String title, String description, String color, String start, String end, int idPaciente, String id) {
        String parametros = "title=" + title + "&description=" + description+ "&color=" + color+ "&start=" + start+ "&end=" + end+ "&idPaciente=" + idPaciente + "&id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("http://healtsoft.com/API/Ind/update/evento.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object eliminar_evento(String id) {
        String parametros = "id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("http://healtsoft.com/API/Ind/delete/evento.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object recuperar_evento(String id) {
        Pojo_Usuario pojo_usuario = null;
        String parametros = "id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL("http://healtsoft.com/API/Ind/read/evento_id.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                Pojo_Evento pojo_evento = Tool_Parser.ParserEvento(respuesta);
                Pojo_Evento.borar_instancia();
                Pojo_Evento.crear_instancia(pojo_evento);
                return Pojo_Evento.recuperar_instancia();
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static Object update_token(int id, String token) {
        String parametros = "token=" + token + "&id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/update/token.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                return "El token se actualizo correctamente";
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object agregarar_evento(String title, String description, String color, String start, String end, int idPaciente, int idEspecialista) {
        String parametros = "title=" + title + "&description=" + description+ "&color=" + color+ "&start=" + start+ "&end=" + end+ "&idPaciente=" + idPaciente+ "&idEspecialista=" + idEspecialista;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/create/agregar_evento.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object agregarar_signo(String temperatura, String pulso, String fr, String o2, String ps, String pd, int idPaciente) {
        String parametros = "temperatura=" + temperatura + "&pulso=" + pulso+ "&frecuenciaRespiratoria=" + fr+ "&glucosa=" + o2+ "&presionSistolica=" + ps+ "&presionDiastolica=" + pd+ "&idPaciente=" + idPaciente;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/create/agregar_signos.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object recuperar_signos(int idPaciente) {
        String parametros = "id=" + idPaciente;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/read/signos.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            int count_signos = jsonObject.getInt("count_signos");
            if (count_signos > 0) {
                Gson g = new Gson();
                List<Pojo_Signos_Vitales> pojoSignos_vitales = new ArrayList<>();
                JSONArray signosArray = jsonObject.getJSONArray("signos");
                for (int i = 0; i < signosArray.length(); i ++ ){
                    pojoSignos_vitales.add(g.fromJson(String.valueOf(signosArray.getJSONObject(i)), Pojo_Signos_Vitales.class));
                }
                Pojo_Signos_Vitales.borrar_instancia();
                Pojo_Signos_Vitales.crear_instancia(pojoSignos_vitales);
                return pojoSignos_vitales;
            } else {
                Pojo_Nota.borrar_instancia();
                return "Pojo_Paciente sin registros";
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object update_hc(String uri, int id, String campo, String valor) {
        String parametros = "campo=" + campo + "&id=" + id + "&valor=" + valor;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                return "El campo se actualizo correctamente";
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Object recuperar_notas(int idPaciente) {
        String parametros = "id=" + idPaciente;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/read/notas.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            int count_notas = jsonObject.getInt("count_notas");
            if (count_notas > 0) {
                Gson g = new Gson();
                List<Pojo_Nota> pojoNotas = new ArrayList<>();
                JSONArray notasArray = jsonObject.getJSONArray("notas");
                for (int i = 0; i < notasArray.length(); i ++ ){
                    pojoNotas.add(g.fromJson(String.valueOf(notasArray.getJSONObject(i)), Pojo_Nota.class));
                }
                Pojo_Nota.borrar_instancia();
                Pojo_Nota.crear_instancia(pojoNotas);
                return pojoNotas;
            } else {
                Pojo_Nota.borrar_instancia();
                return "Pojo_Paciente sin notas";
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object agregarar_nota(String nota, int idPaciente) {
        String parametros = "nota=" + nota + "&idPaciente=" + idPaciente;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/create/agregar_nota.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object historia_clinica (int id) {
        String parametros = "id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL("http://healtsoft.com/API/Ind/read/paciente_id.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                Pojo_Historia_Clinica.borrar_instancia();
                Pojo_Historia_Clinica.crear_instancia(Tool_Parser.ParserHC(respuesta));
                return Pojo_Historia_Clinica.recuperar_instancia();
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return respuesta;
        }
    }

    public static Object agregarar_pacientes(String nombre, String fechaNacimiento, String correo, String telefono, int idEspecialista) {
        String parametros = "nombre=" + nombre + "&fechaNacimiento=" + fechaNacimiento + "&correo=" + correo + "&telefono=" + telefono + "&idEspecialista=" + idEspecialista;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/create/agregar_paciente.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                String mensaje = jsonObject.getString("mensaje");
                return mensaje;
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public static Object consultar_pacientes(int id) {
        String parametros = "id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        try {
            URL url = new URL("https://healtsoft.com/API/Ind/read/pacientes.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                List<Pojo_Paciente> pacientes = new ArrayList<>();
                pacientes = Tool_Parser.ParserPacientes(respuesta);
                Pojo_Paciente.borrar_instancia();
                Pojo_Paciente.crear_instancia(pacientes);
                return Pojo_Paciente.recuperar_instancia();
            } else {
                Pojo_Paciente.borrar_instancia();
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public static Object consultar_eventos(int id, Context context) {
        boolean con = true;
        String parametros = "id=" + id;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL("https://healtsoft.com/API/Ind/read/eventos.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            con = conexion;
            if (conexion){
                List<DrawableCalendarEvent> eventos = new ArrayList<>();
                eventos = Tool_Parser.ParserEvento(respuesta, context);
                Pojo_Drawable_Event.borar_instancia();
                Pojo_Drawable_Event.crear_instancia(eventos);
                return eventos;
            } else {
                Pojo_Drawable_Event.borar_instancia();
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public static Object login(String mail, String pass) {
        Pojo_Usuario pojo_usuario = null;
        String parametros = "mail=" + mail + "&pass=" + pass;
        HttpURLConnection connection = null;
        String respuesta = "";
        byte[] loginBytes = ("HealtSoft-Julio" + ":" + "J.u.l.i.0.").getBytes();
        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL("https://healtsoft.com/API/Ind/read/login.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();
            Scanner inStream = new Scanner(connection.getInputStream());
            while (inStream.hasNextLine()) {
                respuesta += (inStream.nextLine());
            }
            JSONObject jsonObject = new JSONObject(respuesta);
            Boolean conexion = jsonObject.getBoolean("conexion");
            if (conexion){
                JSONObject jsonObject2 = new JSONObject(respuesta);
                JSONArray arrayObject2 = jsonObject.getJSONArray("usuario");
                JSONObject usuarioObject = arrayObject2.getJSONObject(0);
                int id = usuarioObject.getInt("id");
                ArrayList<String> usuario = Tool_Parser.ParserUsuario(respuesta);
                Pojo_Usuario.crear_instancia(id, usuario.get(1), usuario.get(2), pass, usuario.get(4), usuario.get(5));
                Date current = Tool_Fecha.getCurrentDate();
                Date suscription = Tool_Fecha.getDateFromString(Pojo_Usuario.recuperar_instancia().getFechaSuscripcion());
                if (current.before(suscription)) {
                    return Pojo_Usuario.recuperar_instancia();
                } else {
                    Pojo_Codigo_Error pojo_codigo_error = new Pojo_Codigo_Error(false, "015", "Usuario sin suscripcion");
                    return pojo_codigo_error;
                }
            } else {
                Pojo_Codigo_Error pojoCodigoError = Tool_Parser.ParserError(respuesta);
                Pojo_Codigo_Error.crear_instancia(pojoCodigoError);
                return pojoCodigoError;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}