package com.juliovazquez.hsind.Pojos;

public class Pojo_Codigo_Error {

    private Boolean estado;
    private String id;
    private String message;
    private static Pojo_Codigo_Error pojoCodigoError;

    public Pojo_Codigo_Error(Boolean estado, String id, String message) {
        this.estado = estado;
        this.id = id;
        this.message = message;
    }

    public static void crear_instancia(Pojo_Codigo_Error pojoCodigoError) {
        if (pojoCodigoError == null) {
            pojoCodigoError = pojoCodigoError;
        }
    }

    public static Pojo_Codigo_Error recuperar_instancia() {
        return Pojo_Codigo_Error.pojoCodigoError;
    }

    public void borar_instancia() {
        Pojo_Codigo_Error.pojoCodigoError = null;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
