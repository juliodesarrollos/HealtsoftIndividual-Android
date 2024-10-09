package com.juliovazquez.hsind.Pojos;

import java.util.List;

public class Pojo_Signos_Vitales {
    private int id;
    private double temperatura;
    private int pulso;
    private int frecuenciaRespiratoria;
    private int presionSistolica;
    private int presionDiastolica;
    private int glucosa;
    private String created_at;
    private String updated_at;
    private static List<Pojo_Signos_Vitales> pojoSignos_vitales;

    public Pojo_Signos_Vitales(int id, int temperatura, int pulso, int frecuenciaRespiratoria, int presionSistolica, int presionDiastolica, int glucosa, String created_at, String updated_at) {
        this.id = id;
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.glucosa = glucosa;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static void crear_instancia (List<Pojo_Signos_Vitales> signos) {
        pojoSignos_vitales = signos;
    }

    public static List<Pojo_Signos_Vitales> recuperar_instancia () {
        return pojoSignos_vitales;
    }

    public static void borrar_instancia () {
        pojoSignos_vitales = null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getPulso() {
        return pulso;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public int getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(int glucosa) {
        this.glucosa = glucosa;
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
