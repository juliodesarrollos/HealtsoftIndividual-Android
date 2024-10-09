package com.juliovazquez.hsind.Pojos;

import java.util.List;

public class Pojo_Historia_Clinica {
    int PacienteId;
    String PacienteNombre;
    String PacienteFechaNacimiento;
    String PacienteCorreo;
    String PacienteTelefono;
    int PacienteIdEspecialista;
    int PersonalesId;
    String PersonalesFotoUrl;
    String PersonalesDomicilio;
    String PersonalesSexo;
    String PersonalesEstadoCivil;
    String PersonalesOcupacion;
    String PersonalesEstudios;
    String PersonalesTipoSangre;
    int idHC;
    int idPatologicos;
    String PatologicosEnfermedades;
    String PatologicosHeredofamiliares;
    String PatologicosMedicamentos;
    String PatologicosCirugias;
    String PatologicosTipoBandera;
    String PatologicosAlcohol;
    String PatologicosCigarro;
    String PatologicosDrogas;
    String PatologicosFracturas;
    int idConsulta;
    String ConsultaMotivoConsulta;
    String ConsultaCausaMolestia;
    String ConsultaInicioMolestia;
    String ConsultaTtoPrevio;
    String ConsultaCausaAumento;
    String ConsultaCausaDisminuye;
    int ConsultaNivelDolor;
    String ConsultaAlteracionesMarcha;
    String ConsultaDispositivoAsistencia;
    int idTto;
    String TtoDxMedico;
    String TtoDxFisio;
    String TtoCodigoCie;
    String TtoTratamiento;
    String TtoObjetivoTto;
    String TtoComentarios;
    int TtoNumeroSesiones;
    Pojo_Signos_Vitales pojoSignos_vitales;
    List<Pojo_Nota> pojoNotas;

    public Pojo_Historia_Clinica(int pacienteId, String pacienteNombre, String pacienteFechaNacimiento, String pacienteCorreo, String pacienteTelefono, int pacienteIdEspecialista, int personalesId, String personalesFotoUrl, String personalesDomicilio, String personalesSexo, String personalesEstadoCivil, String personalesOcupacion, String personalesEstudios, String personalesTipoSangre, int idHC, int idPatologicos, String patologicosEnfermedades, String patologicosHeredofamiliares, String patologicosMedicamentos, String patologicosCirugias, String patologicosTipoBandera, String patologicosAlcohol, String patologicosCigarro, String patologicosDrogas, String patologicosFracturas, int idConsulta, String consultaMotivoConsulta, String consultaCausaMolestia, String consultaInicioMolestia, String consultaTtoPrevio, String consultaCausaAumento, String consultaCausaDisminuye, int consultaNivelDolor, String consultaAlteracionesMarcha, String consultaDispositivoAsistencia, int idTto, String ttoDxMedico, String ttoDxFisio, String ttoCodigoCie, String ttoTratamiento, String ttoObjetivoTto, String ttoComentarios, int ttoNumeroSesiones, Pojo_Signos_Vitales pojoSignos_vitales, List<Pojo_Nota> pojoNotas) {
        PacienteId = pacienteId;
        PacienteNombre = pacienteNombre;
        PacienteFechaNacimiento = pacienteFechaNacimiento;
        PacienteCorreo = pacienteCorreo;
        PacienteTelefono = pacienteTelefono;
        PacienteIdEspecialista = pacienteIdEspecialista;
        PersonalesId = personalesId;
        PersonalesFotoUrl = personalesFotoUrl;
        PersonalesDomicilio = personalesDomicilio;
        PersonalesSexo = personalesSexo;
        PersonalesEstadoCivil = personalesEstadoCivil;
        PersonalesOcupacion = personalesOcupacion;
        PersonalesEstudios = personalesEstudios;
        PersonalesTipoSangre = personalesTipoSangre;
        this.idHC = idHC;
        this.idPatologicos = idPatologicos;
        PatologicosEnfermedades = patologicosEnfermedades;
        PatologicosHeredofamiliares = patologicosHeredofamiliares;
        PatologicosMedicamentos = patologicosMedicamentos;
        PatologicosCirugias = patologicosCirugias;
        PatologicosTipoBandera = patologicosTipoBandera;
        PatologicosAlcohol = patologicosAlcohol;
        PatologicosCigarro = patologicosCigarro;
        PatologicosDrogas = patologicosDrogas;
        PatologicosFracturas = patologicosFracturas;
        this.idConsulta = idConsulta;
        ConsultaMotivoConsulta = consultaMotivoConsulta;
        ConsultaCausaMolestia = consultaCausaMolestia;
        ConsultaInicioMolestia = consultaInicioMolestia;
        ConsultaTtoPrevio = consultaTtoPrevio;
        ConsultaCausaAumento = consultaCausaAumento;
        ConsultaCausaDisminuye = consultaCausaDisminuye;
        ConsultaNivelDolor = consultaNivelDolor;
        ConsultaAlteracionesMarcha = consultaAlteracionesMarcha;
        ConsultaDispositivoAsistencia = consultaDispositivoAsistencia;
        this.idTto = idTto;
        TtoDxMedico = ttoDxMedico;
        TtoDxFisio = ttoDxFisio;
        TtoCodigoCie = ttoCodigoCie;
        TtoTratamiento = ttoTratamiento;
        TtoObjetivoTto = ttoObjetivoTto;
        TtoComentarios = ttoComentarios;
        TtoNumeroSesiones = ttoNumeroSesiones;
        this.pojoSignos_vitales = pojoSignos_vitales;
        this.pojoNotas = pojoNotas;
    }

    public Pojo_Signos_Vitales getPojoSignos_vitales() {
        return pojoSignos_vitales;
    }

    public void setPojoSignos_vitales(Pojo_Signos_Vitales pojoSignos_vitales) {
        this.pojoSignos_vitales = pojoSignos_vitales;
    }

    public List<Pojo_Nota> getPojoNotas() {
        return pojoNotas;
    }

    public void setPojoNotas(List<Pojo_Nota> pojoNotas) {
        this.pojoNotas = pojoNotas;
    }

    private static Pojo_Historia_Clinica pojoHistoria_clinica;

    public static void crear_instancia (Pojo_Historia_Clinica hc) {
        pojoHistoria_clinica = hc;
    }

    public static Pojo_Historia_Clinica recuperar_instancia () {
        return pojoHistoria_clinica;
    }

    public static void borrar_instancia () {
        pojoHistoria_clinica = null;
    }

    public int getPacienteId() {
        return PacienteId;
    }

    public void setPacienteId(int pacienteId) {
        PacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return PacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        PacienteNombre = pacienteNombre;
    }

    public String getPacienteFechaNacimiento() {
        return PacienteFechaNacimiento;
    }

    public void setPacienteFechaNacimiento(String pacienteFechaNacimiento) {
        PacienteFechaNacimiento = pacienteFechaNacimiento;
    }

    public String getPacienteCorreo() {
        return PacienteCorreo;
    }

    public void setPacienteCorreo(String pacienteCorreo) {
        PacienteCorreo = pacienteCorreo;
    }

    public String getPacienteTelefono() {
        return PacienteTelefono;
    }

    public void setPacienteTelefono(String pacienteTelefono) {
        PacienteTelefono = pacienteTelefono;
    }

    public int getPacienteIdEspecialista() {
        return PacienteIdEspecialista;
    }

    public void setPacienteIdEspecialista(int pacienteIdEspecialista) {
        PacienteIdEspecialista = pacienteIdEspecialista;
    }

    public int getPersonalesId() {
        return PersonalesId;
    }

    public void setPersonalesId(int personalesId) {
        PersonalesId = personalesId;
    }

    public String getPersonalesFotoUrl() {
        return PersonalesFotoUrl;
    }

    public void setPersonalesFotoUrl(String personalesFotoUrl) {
        PersonalesFotoUrl = personalesFotoUrl;
    }

    public String getPersonalesDomicilio() {
        return PersonalesDomicilio;
    }

    public void setPersonalesDomicilio(String personalesDomicilio) {
        PersonalesDomicilio = personalesDomicilio;
    }

    public String getPersonalesSexo() {
        return PersonalesSexo;
    }

    public void setPersonalesSexo(String personalesSexo) {
        PersonalesSexo = personalesSexo;
    }

    public String getPersonalesEstadoCivil() {
        return PersonalesEstadoCivil;
    }

    public void setPersonalesEstadoCivil(String personalesEstadoCivil) {
        PersonalesEstadoCivil = personalesEstadoCivil;
    }

    public String getPersonalesOcupacion() {
        return PersonalesOcupacion;
    }

    public void setPersonalesOcupacion(String personalesOcupacion) {
        PersonalesOcupacion = personalesOcupacion;
    }

    public String getPersonalesEstudios() {
        return PersonalesEstudios;
    }

    public void setPersonalesEstudios(String personalesEstudios) {
        PersonalesEstudios = personalesEstudios;
    }

    public String getPersonalesTipoSangre() {
        return PersonalesTipoSangre;
    }

    public void setPersonalesTipoSangre(String personalesTipoSangre) {
        PersonalesTipoSangre = personalesTipoSangre;
    }

    public int getIdHC() {
        return idHC;
    }

    public void setIdHC(int idHC) {
        this.idHC = idHC;
    }

    public int getIdPatologicos() {
        return idPatologicos;
    }

    public void setIdPatologicos(int idPatologicos) {
        this.idPatologicos = idPatologicos;
    }

    public String getPatologicosEnfermedades() {
        return PatologicosEnfermedades;
    }

    public void setPatologicosEnfermedades(String patologicosEnfermedades) {
        PatologicosEnfermedades = patologicosEnfermedades;
    }

    public String getPatologicosHeredofamiliares() {
        return PatologicosHeredofamiliares;
    }

    public void setPatologicosHeredofamiliares(String patologicosHeredofamiliares) {
        PatologicosHeredofamiliares = patologicosHeredofamiliares;
    }

    public String getPatologicosMedicamentos() {
        return PatologicosMedicamentos;
    }

    public void setPatologicosMedicamentos(String patologicosMedicamentos) {
        PatologicosMedicamentos = patologicosMedicamentos;
    }

    public String getPatologicosCirugias() {
        return PatologicosCirugias;
    }

    public void setPatologicosCirugias(String patologicosCirugias) {
        PatologicosCirugias = patologicosCirugias;
    }

    public String getPatologicosTipoBandera() {
        return PatologicosTipoBandera;
    }

    public void setPatologicosTipoBandera(String patologicosTipoBandera) {
        PatologicosTipoBandera = patologicosTipoBandera;
    }

    public String getPatologicosAlcohol() {
        return PatologicosAlcohol;
    }

    public void setPatologicosAlcohol(String patologicosAlcohol) {
        PatologicosAlcohol = patologicosAlcohol;
    }

    public String getPatologicosCigarro() {
        return PatologicosCigarro;
    }

    public void setPatologicosCigarro(String patologicosCigarro) {
        PatologicosCigarro = patologicosCigarro;
    }

    public String getPatologicosDrogas() {
        return PatologicosDrogas;
    }

    public void setPatologicosDrogas(String patologicosDrogas) {
        PatologicosDrogas = patologicosDrogas;
    }

    public String getPatologicosFracturas() {
        return PatologicosFracturas;
    }

    public void setPatologicosFracturas(String patologicosFracturas) {
        PatologicosFracturas = patologicosFracturas;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getConsultaMotivoConsulta() {
        return ConsultaMotivoConsulta;
    }

    public void setConsultaMotivoConsulta(String consultaMotivoConsulta) {
        ConsultaMotivoConsulta = consultaMotivoConsulta;
    }

    public String getConsultaCausaMolestia() {
        return ConsultaCausaMolestia;
    }

    public void setConsultaCausaMolestia(String consultaCausaMolestia) {
        ConsultaCausaMolestia = consultaCausaMolestia;
    }

    public String getConsultaInicioMolestia() {
        return ConsultaInicioMolestia;
    }

    public void setConsultaInicioMolestia(String consultaInicioMolestia) {
        ConsultaInicioMolestia = consultaInicioMolestia;
    }

    public String getConsultaTtoPrevio() {
        return ConsultaTtoPrevio;
    }

    public void setConsultaTtoPrevio(String consultaTtoPrevio) {
        ConsultaTtoPrevio = consultaTtoPrevio;
    }

    public String getConsultaCausaAumento() {
        return ConsultaCausaAumento;
    }

    public void setConsultaCausaAumento(String consultaCausaAumento) {
        ConsultaCausaAumento = consultaCausaAumento;
    }

    public String getConsultaCausaDisminuye() {
        return ConsultaCausaDisminuye;
    }

    public void setConsultaCausaDisminuye(String consultaCausaDisminuye) {
        ConsultaCausaDisminuye = consultaCausaDisminuye;
    }

    public int getConsultaNivelDolor() {
        return ConsultaNivelDolor;
    }

    public void setConsultaNivelDolor(int consultaNivelDolor) {
        ConsultaNivelDolor = consultaNivelDolor;
    }

    public String getConsultaAlteracionesMarcha() {
        return ConsultaAlteracionesMarcha;
    }

    public void setConsultaAlteracionesMarcha(String consultaAlteracionesMarcha) {
        ConsultaAlteracionesMarcha = consultaAlteracionesMarcha;
    }

    public String getConsultaDispositivoAsistencia() {
        return ConsultaDispositivoAsistencia;
    }

    public void setConsultaDispositivoAsistencia(String consultaDispositivoAsistencia) {
        ConsultaDispositivoAsistencia = consultaDispositivoAsistencia;
    }

    public int getIdTto() {
        return idTto;
    }

    public void setIdTto(int idTto) {
        this.idTto = idTto;
    }

    public String getTtoDxMedico() {
        return TtoDxMedico;
    }

    public void setTtoDxMedico(String ttoDxMedico) {
        TtoDxMedico = ttoDxMedico;
    }

    public String getTtoDxFisio() {
        return TtoDxFisio;
    }

    public void setTtoDxFisio(String ttoDxFisio) {
        TtoDxFisio = ttoDxFisio;
    }

    public String getTtoCodigoCie() {
        return TtoCodigoCie;
    }

    public void setTtoCodigoCie(String ttoCodigoCie) {
        TtoCodigoCie = ttoCodigoCie;
    }

    public String getTtoTratamiento() {
        return TtoTratamiento;
    }

    public void setTtoTratamiento(String ttoTratamiento) {
        TtoTratamiento = ttoTratamiento;
    }

    public String getTtoObjetivoTto() {
        return TtoObjetivoTto;
    }

    public void setTtoObjetivoTto(String ttoObjetivoTto) {
        TtoObjetivoTto = ttoObjetivoTto;
    }

    public String getTtoComentarios() {
        return TtoComentarios;
    }

    public void setTtoComentarios(String ttoComentarios) {
        TtoComentarios = ttoComentarios;
    }

    public int getTtoNumeroSesiones() {
        return TtoNumeroSesiones;
    }

    public void setTtoNumeroSesiones(int ttoNumeroSesiones) {
        TtoNumeroSesiones = ttoNumeroSesiones;
    }

    public static Pojo_Historia_Clinica getPojoHistoria_clinica() {
        return pojoHistoria_clinica;
    }

    public static void setPojoHistoria_clinica(Pojo_Historia_Clinica pojoHistoria_clinica) {
        Pojo_Historia_Clinica.pojoHistoria_clinica = pojoHistoria_clinica;
    }

    public Pojo_Historia_Clinica(int pacienteId, String pacienteNombre, String pacienteFechaNacimiento, String pacienteCorreo, String pacienteTelefono, int pacienteIdEspecialista, int personalesId, String personalesFotoUrl, String personalesDomicilio, String personalesSexo, String personalesEstadoCivil, String personalesOcupacion, String personalesEstudios, String personalesTipoSangre, int idHC, int idPatologicos, String patologicosEnfermedades, String patologicosHeredofamiliares, String patologicosMedicamentos, String patologicosCirugias, String patologicosTipoBandera, String patologicosAlcohol, String patologicosCigarro, String patologicosDrogas, String patologicosFracturas, int idConsulta, String consultaMotivoConsulta, String consultaCausaMolestia, String consultaInicioMolestia, String consultaTtoPrevio, String consultaCausaAumento, String consultaCausaDisminuye, int consultaNivelDolor, String consultaAlteracionesMarcha, String consultaDispositivoAsistencia, int idTto, String ttoDxMedico, String ttoDxFisio, String ttoCodigoCie, String ttoTratamiento, String ttoObjetivoTto, String ttoComentarios, int ttoNumeroSesiones) {
        PacienteId = pacienteId;
        PacienteNombre = pacienteNombre;
        PacienteFechaNacimiento = pacienteFechaNacimiento;
        PacienteCorreo = pacienteCorreo;
        PacienteTelefono = pacienteTelefono;
        PacienteIdEspecialista = pacienteIdEspecialista;
        PersonalesId = personalesId;
        PersonalesFotoUrl = personalesFotoUrl;
        PersonalesDomicilio = personalesDomicilio;
        PersonalesSexo = personalesSexo;
        PersonalesEstadoCivil = personalesEstadoCivil;
        PersonalesOcupacion = personalesOcupacion;
        PersonalesEstudios = personalesEstudios;
        PersonalesTipoSangre = personalesTipoSangre;
        this.idHC = idHC;
        this.idPatologicos = idPatologicos;
        PatologicosEnfermedades = patologicosEnfermedades;
        PatologicosHeredofamiliares = patologicosHeredofamiliares;
        PatologicosMedicamentos = patologicosMedicamentos;
        PatologicosCirugias = patologicosCirugias;
        PatologicosTipoBandera = patologicosTipoBandera;
        PatologicosAlcohol = patologicosAlcohol;
        PatologicosCigarro = patologicosCigarro;
        PatologicosDrogas = patologicosDrogas;
        PatologicosFracturas = patologicosFracturas;
        this.idConsulta = idConsulta;
        ConsultaMotivoConsulta = consultaMotivoConsulta;
        ConsultaCausaMolestia = consultaCausaMolestia;
        ConsultaInicioMolestia = consultaInicioMolestia;
        ConsultaTtoPrevio = consultaTtoPrevio;
        ConsultaCausaAumento = consultaCausaAumento;
        ConsultaCausaDisminuye = consultaCausaDisminuye;
        ConsultaNivelDolor = consultaNivelDolor;
        ConsultaAlteracionesMarcha = consultaAlteracionesMarcha;
        ConsultaDispositivoAsistencia = consultaDispositivoAsistencia;
        this.idTto = idTto;
        TtoDxMedico = ttoDxMedico;
        TtoDxFisio = ttoDxFisio;
        TtoCodigoCie = ttoCodigoCie;
        TtoTratamiento = ttoTratamiento;
        TtoObjetivoTto = ttoObjetivoTto;
        TtoComentarios = ttoComentarios;
        TtoNumeroSesiones = ttoNumeroSesiones;
    }
}
