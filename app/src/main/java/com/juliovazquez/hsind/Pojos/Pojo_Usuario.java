package com.juliovazquez.hsind.Pojos;

public class Pojo_Usuario {
    private int id;
    private String name;
    private String email;
    private String password;
    private String fechaSuscripcion;
    private String tipoSuscripcion;
    private static Pojo_Usuario pojoUsuario;

    private Pojo_Usuario(int id, String name, String email, String password, String fechaSuscripcion, String tipoSuscripcion) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.fechaSuscripcion = fechaSuscripcion;
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public static void crear_instancia(int id, String name, String email, String password, String fechaSuscripcion, String tipoSuscripcion) {
        if (pojoUsuario == null) {
            pojoUsuario = new Pojo_Usuario(id, name, email, password, fechaSuscripcion, tipoSuscripcion);
        }
    }

    public static Pojo_Usuario recuperar_instancia() {
        return pojoUsuario;
    }

    public static void borar_instancia() {
        pojoUsuario = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }
}
