package com.juliovazquez.hsind.Activities.Paciente;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import java.text.Normalizer;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Historia_Clinica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Historia_Clinica extends Fragment implements View.OnClickListener, OnKeyListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayout layoutDatosPersonales, layoutHC, layoutPersonales, layoutPatologicos, layoutDatosPatologicos;
    LinearLayout layoutConsulta, layoutDatosConsulta, layoutTto, layoutDatosTto;
    TextView btnPersonales, btnPatologicos, btnConsulta, btnTto;
    Button btnPersonales2, btnPatologicos2, btnConsulta2, btnTto2;
    EditText txtDireccion, txtSexo, txtCivil, txtOcupacion, txtEstudios, txtSangre;
    EditText txtEnfermedades, txtHeredofamiliares, txtMedicamentos, txtCirugias, txtBanderas, txtAlcohol, txtFuma, txtDrogas, txtFracturas;
    EditText txtMotivo, txtCausa, txtInicio, txtPrevio, txtAumentar, txtDisminuir, txtAlteraciones, txtAsistencia;
    EditText txtDxMedico, txtDxFisio, txtCie, txtTto, txtObjetivo, txtComentarios, txtSesiones;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Historia_Clinica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pojo_Historia_Clinica.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Historia_Clinica newInstance(String param1, String param2) {
        Fragment_Historia_Clinica fragment = new Fragment_Historia_Clinica();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPersonales2 = getView().findViewById(R.id.btnPersonales);
        btnPersonales = getView().findViewById(R.id.btnPersonales2);
        btnPatologicos = getView().findViewById(R.id.btnPatologicos);
        btnPatologicos2 = getView().findViewById(R.id.btnPatologicos2);
        btnConsulta = getView().findViewById(R.id.btnConsulta);
        btnConsulta2 = getView().findViewById(R.id.btnConsulta2);
        btnTto = getView().findViewById(R.id.btnTto);
        btnTto2 = getView().findViewById(R.id.btnTto2);

        layoutDatosPatologicos = getView().findViewById(R.id.layoutDatosPatologicos);
        layoutPatologicos = getView().findViewById(R.id.layoutPatologicos);
        layoutPersonales = getView().findViewById(R.id.layoutPersonales);
        layoutDatosPersonales = getView().findViewById(R.id.layoutDatosPersonales);
        layoutConsulta = getView().findViewById(R.id.layoutConsulta);
        layoutDatosConsulta = getView().findViewById(R.id.layoutDatosConsulta);
        layoutTto = getView().findViewById(R.id.layoutTto);
        layoutDatosTto = getView().findViewById(R.id.layoutDatosTto);

        txtSesiones = getView().findViewById(R.id.txtSesiones);
        txtComentarios = getView().findViewById(R.id.txtComentarios);
        txtObjetivo = getView().findViewById(R.id.txtObjetivo);
        txtTto = getView().findViewById(R.id.txtTto);
        txtCie = getView().findViewById(R.id.txtCie);
        txtDxFisio = getView().findViewById(R.id.txtDxFisio);
        txtDxMedico = getView().findViewById(R.id.txtDxMedico);
        txtDireccion = getView().findViewById(R.id.txtDireccion);
        layoutHC = getView().findViewById(R.id.layoutHC);
        txtSexo = getView().findViewById(R.id.txtSexo);
        txtCivil = getView().findViewById(R.id.txtCivil);
        txtOcupacion = getView().findViewById(R.id.txtOcupacion);
        txtEstudios = getView().findViewById(R.id.txtEstudios);
        txtSangre = getView().findViewById(R.id.txtSangre);
        txtEnfermedades = getView().findViewById(R.id.txtEnfermedades);
        txtHeredofamiliares = getView().findViewById(R.id.txtHeredofamiliares);
        txtMedicamentos = getView().findViewById(R.id.txtMedicamentos);
        txtCirugias = getView().findViewById(R.id.txtCirugias);
        txtBanderas = getView().findViewById(R.id.txtBandera);
        txtAlcohol = getView().findViewById(R.id.txtAlcohol);
        txtFuma = getView().findViewById(R.id.txtFumar);
        txtDrogas = getView().findViewById(R.id.txtDrogas);
        txtFracturas = getView().findViewById(R.id.txtFracturas);
        txtMotivo = getView().findViewById(R.id.txtMotivo);
        txtCausa = getView().findViewById(R.id.txtCausa);
        txtInicio = getView().findViewById(R.id.txtInicio);
        txtPrevio = getView().findViewById(R.id.txtPrevio);
        txtAumentar = getView().findViewById(R.id.txtAumentar);
        txtDisminuir = getView().findViewById(R.id.txtDisminuir);
        txtAlteraciones = getView().findViewById(R.id.txtAlteraciones);
        txtAsistencia = getView().findViewById(R.id.txtAsistencia);

        btnPersonales2.setOnClickListener(this);
        btnPersonales.setOnClickListener(this);
        btnPatologicos.setOnClickListener(this);
        btnPatologicos2.setOnClickListener(this);
        btnConsulta.setOnClickListener(this);
        btnConsulta2.setOnClickListener(this);
        btnTto.setOnClickListener(this);
        btnTto2.setOnClickListener(this);

        txtDireccion.setOnKeyListener(this::onKey);
        txtSexo.setOnKeyListener(this::onKey);
        txtCivil.setOnKeyListener(this::onKey);
        txtOcupacion.setOnKeyListener(this::onKey);
        txtEstudios.setOnKeyListener(this::onKey);
        txtSangre.setOnKeyListener(this::onKey);
        txtEnfermedades.setOnKeyListener(this::onKey);
        txtHeredofamiliares.setOnKeyListener(this::onKey);
        txtMedicamentos.setOnKeyListener(this::onKey);
        txtCirugias.setOnKeyListener(this::onKey);
        txtBanderas.setOnKeyListener(this::onKey);
        txtAlcohol.setOnKeyListener(this::onKey);
        txtFuma.setOnKeyListener(this::onKey);
        txtDrogas.setOnKeyListener(this::onKey);
        txtFracturas.setOnKeyListener(this::onKey);
        txtMotivo.setOnKeyListener(this::onKey);
        txtCausa.setOnKeyListener(this::onKey);
        txtInicio.setOnKeyListener(this::onKey);
        txtPrevio.setOnKeyListener(this::onKey);
        txtAumentar.setOnKeyListener(this::onKey);
        txtDisminuir.setOnKeyListener(this::onKey);
        txtAlteraciones.setOnKeyListener(this::onKey);
        txtAsistencia.setOnKeyListener(this::onKey);
        txtDxMedico.setOnKeyListener(this::onKey);
        txtDxFisio.setOnKeyListener(this::onKey);
        txtCie.setOnKeyListener(this::onKey);
        txtTto.setOnKeyListener(this::onKey);
        txtObjetivo.setOnKeyListener(this::onKey);
        txtComentarios.setOnKeyListener(this::onKey);
        txtSesiones.setOnKeyListener(this::onKey);
    }

    private void update(String uri, int id, String campo, String valor) {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                String cadenaNormalize = Normalizer.normalize(valor, Normalizer.Form.NFD);
                String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
                final Object res = Service_Consumo.update_hc(uri, id, campo, cadenaSinAcentos);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), getString(R.string.update_correcto));
                        } else if (res instanceof Pojo_Codigo_Error) {
                            Tool_Sweet_Alert.ERROR_TYPE(getContext(), ((Pojo_Codigo_Error) res).getMessage());
                        } else {
                            Tool_Sweet_Alert.ERROR_TYPE(getContext(), getString(R.string.campo_requerido));
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void llenar_datos() {
        if (Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId() > 0) {
            layoutPersonales.setVisibility(View.VISIBLE);
            layoutHC.setVisibility(View.VISIBLE);
            txtDireccion.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesDomicilio());
            txtSexo.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesSexo());
            txtCivil.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesEstadoCivil());
            txtOcupacion.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesOcupacion());
            txtEstudios.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesEstudios());
            txtSangre.setText(Pojo_Historia_Clinica.recuperar_instancia().getPersonalesTipoSangre());
        } else layoutPersonales.setVisibility(View.GONE);
        if (Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos() > 0) {
            layoutPatologicos.setVisibility(View.VISIBLE);
            layoutHC.setVisibility(View.VISIBLE);
            txtEnfermedades.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosEnfermedades());
            txtMedicamentos.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosMedicamentos());
            txtHeredofamiliares.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosHeredofamiliares());
            txtCirugias.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosCirugias());
            txtBanderas.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosTipoBandera());
            txtAlcohol.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosAlcohol());
            txtFuma.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosCigarro());
            txtDrogas.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosDrogas());
            txtFracturas.setText(Pojo_Historia_Clinica.recuperar_instancia().getPatologicosFracturas());
        } else layoutPatologicos.setVisibility(View.GONE);
        if (Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta() > 0) {
            layoutConsulta.setVisibility(View.VISIBLE);
            layoutHC.setVisibility(View.VISIBLE);
            txtMotivo.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaMotivoConsulta());
            txtCausa.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaCausaMolestia());
            txtInicio.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaInicioMolestia());
            txtPrevio.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaTtoPrevio());
            txtAumentar.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaCausaAumento());
            txtDisminuir.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaCausaDisminuye());
            txtAlteraciones.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaAlteracionesMarcha());
            txtAsistencia.setText(Pojo_Historia_Clinica.recuperar_instancia().getConsultaDispositivoAsistencia());
        } else layoutConsulta.setVisibility(View.GONE);
        if (Pojo_Historia_Clinica.recuperar_instancia().getIdTto() > 0) {
            layoutTto.setVisibility(View.VISIBLE);
            layoutHC.setVisibility(View.VISIBLE);
            txtDxMedico.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoDxMedico());
            txtDxFisio.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoDxFisio());
            txtCie.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoCodigoCie());
            txtTto.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoTratamiento());
            txtObjetivo.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoObjetivoTto());
            txtComentarios.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoComentarios());
            txtSesiones.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoNumeroSesiones() + "");
        } else layoutTto.setVisibility(View.GONE);
        habilitar_campos(true);
    }

    public void ver_editar() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("¿Desea ver o editar los datos?")
                .setConfirmButton("Ver", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        habilitar_campos(false);
                    }
                })
                .setCancelButton("Editar", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        habilitar_campos(true);
                    }
                })
                .show();
    }

    private void habilitar_campos(boolean enable) {
        txtDireccion.setEnabled(enable);
        txtSexo.setEnabled(enable);
        txtCivil.setEnabled(enable);
        txtOcupacion.setEnabled(enable);
        txtEstudios.setEnabled(enable);
        txtSangre.setEnabled(enable);
        txtEnfermedades.setEnabled(enable);
        txtHeredofamiliares.setEnabled(enable);
        txtMedicamentos.setEnabled(enable);
        txtCirugias.setEnabled(enable);
        txtBanderas.setEnabled(enable);
        txtAlcohol.setEnabled(enable);
        txtFuma.setEnabled(enable);
        txtDrogas.setEnabled(enable);
        txtFracturas.setEnabled(enable);
        txtMotivo.setEnabled(enable);
        txtCausa.setEnabled(enable);
        txtInicio.setEnabled(enable);
        txtPrevio.setEnabled(enable);
        txtAumentar.setEnabled(enable);
        txtDisminuir.setEnabled(enable);
        txtAlteraciones.setEnabled(enable);
        txtAsistencia.setEnabled(enable);
        txtDxMedico.setEnabled(enable);
        txtDxFisio.setEnabled(enable);
        txtCie.setEnabled(enable);
        txtTto.setEnabled(enable);
        txtObjetivo.setEnabled(enable);
        txtComentarios.setEnabled(enable);
        txtSesiones.setEnabled(enable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historia__clinica, container, false);
    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        switch (view) {
            case R.id.btnPersonales2:
                mostrarPersonales();
                break;
            case R.id.btnPersonales:
                mostrarPersonales();
                break;
            case R.id.btnPatologicos:
                mostrarPatologicos();
                break;
            case R.id.btnPatologicos2:
                mostrarPatologicos();
                break;
            case R.id.btnConsulta:
                mostrarConsulta();
                break;
            case R.id.btnConsulta2:
                mostrarConsulta();
                break;
            case R.id.btnTto:
                mostrarTto();
                break;
            case R.id.btnTto2:
                mostrarTto();
                break;
        }
    }

    private void mostrarTto() {
        btnTto2.setBackground(layoutDatosTto.getVisibility() == View.VISIBLE ? getResources().getDrawable(R.drawable.ic_boton_abrir) : getResources().getDrawable(R.drawable.ic_boton_cerrar));
        layoutDatosTto.setVisibility(layoutDatosTto.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void mostrarConsulta() {
        btnConsulta2.setBackground(layoutDatosConsulta.getVisibility() == View.VISIBLE ? getResources().getDrawable(R.drawable.ic_boton_abrir) : getResources().getDrawable(R.drawable.ic_boton_cerrar));
        layoutDatosConsulta.setVisibility(layoutDatosConsulta.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void mostrarPatologicos() {
        btnPatologicos2.setBackground(layoutDatosPatologicos.getVisibility() == View.VISIBLE ? getResources().getDrawable(R.drawable.ic_boton_abrir) : getResources().getDrawable(R.drawable.ic_boton_cerrar));
        layoutDatosPatologicos.setVisibility(layoutDatosPatologicos.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void mostrarPersonales() {
        btnPersonales2.setBackground(layoutDatosPersonales.getVisibility() == View.VISIBLE ? getResources().getDrawable(R.drawable.ic_boton_abrir) : getResources().getDrawable(R.drawable.ic_boton_cerrar));
        layoutDatosPersonales.setVisibility(layoutDatosPersonales.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            // Perform action on key press
            switch (v.getId()) {
                case R.id.txtDireccion:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "domicilio", txtDireccion.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesDomicilio(txtDireccion.getText().toString());
                    break;
                case R.id.txtSexo:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "sexo", txtSexo.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesSexo(txtSexo.getText().toString());
                    break;
                case R.id.txtCivil:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "estadoCivil", txtCivil.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesEstadoCivil(txtCivil.getText().toString());
                    break;
                case R.id.txtOcupacion:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "ocupacion", txtOcupacion.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesOcupacion(txtOcupacion.getText().toString());
                    break;
                case R.id.txtEstudios:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "estudios", txtEstudios.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesEstudios(txtEstudios.getText().toString());
                    break;
                case R.id.txtSangre:
                    update(getString(R.string.url_personales), Pojo_Historia_Clinica.recuperar_instancia().getPersonalesId(), "tipoSangre", txtSangre.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPersonalesTipoSangre(txtSangre.getText().toString());
                    break;
                case R.id.txtEnfermedades:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "enfermedades", txtEnfermedades.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosEnfermedades(txtEnfermedades.getText().toString());
                    break;
                case R.id.txtHeredofamiliares:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "heredofamiliares", txtHeredofamiliares.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosHeredofamiliares(txtHeredofamiliares.getText().toString());
                    break;
                case R.id.txtMedicamentos:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "medicamentos", txtMedicamentos.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosMedicamentos(txtMedicamentos.getText().toString());
                    break;
                case R.id.txtCirugias:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "cirugias", txtCirugias.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosCirugias(txtCirugias.getText().toString());
                    break;
                case R.id.txtBandera:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "tipoBandera", txtBanderas.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosTipoBandera(txtBanderas.getText().toString());
                    break;
                case R.id.txtAlcohol:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "alcohol", txtAlcohol.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosAlcohol(txtAlcohol.getText().toString());
                    break;
                case R.id.txtFumar:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "cigarro", txtFuma.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosCigarro(txtFuma.getText().toString());
                    break;
                case R.id.txtDrogas:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "drogas", txtDrogas.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosDrogas(txtDrogas.getText().toString());
                    break;
                case R.id.txtFracturas:
                    update(getString(R.string.url_patologicos), Pojo_Historia_Clinica.recuperar_instancia().getIdPatologicos(), "fracturas", txtFracturas.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setPatologicosFracturas(txtFracturas.getText().toString());
                    break;
                case R.id.txtMotivo:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "motivoConsulta", txtMotivo.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaMotivoConsulta(txtMotivo.getText().toString());
                    break;
                case R.id.txtCausa:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "causaMolestia", txtCausa.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaCausaMolestia(txtCausa.getText().toString());
                    break;
                case R.id.txtInicio:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "inicioMolestia", txtInicio.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaInicioMolestia(txtInicio.getText().toString());
                    break;
                case R.id.txtPrevio:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "ttoPrevio", txtPrevio.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaTtoPrevio(txtPrevio.getText().toString());
                    break;
                case R.id.txtAumentar:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "causaAumento", txtAumentar.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaCausaAumento(txtAumentar.getText().toString());
                    break;
                case R.id.txtDisminuir:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "causaDisminuye", txtDisminuir.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaCausaDisminuye(txtDisminuir.getText().toString());
                    break;
                case R.id.txtAlteraciones:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "alteracionesMarcha", txtAlteraciones.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaAlteracionesMarcha(txtAlteraciones.getText().toString());
                    break;
                case R.id.txtAsistencia:
                    update(getString(R.string.url_consulta), Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta(), "dispositivoAsistencia", txtAsistencia.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setConsultaDispositivoAsistencia(txtAsistencia.getText().toString());
                    break;
                case R.id.txtDxMedico:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "dxMedico", txtDxMedico.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoDxMedico(txtDxMedico.getText().toString());
                    break;
                case R.id.txtDxFisio:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "dxFisio", txtDxFisio.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoDxFisio(txtDxFisio.getText().toString());
                    break;
                case R.id.txtCie:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "codigoCie", txtCie.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoCodigoCie(txtCie.getText().toString());
                    break;
                case R.id.txtTto:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "tratamiento", txtTto.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoTratamiento(txtTto.getText().toString());
                    break;
                case R.id.txtObjetivo:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "objetivoTto", txtObjetivo.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoObjetivoTto(txtObjetivo.getText().toString());
                    break;
                case R.id.txtComentarios:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "comentarios", txtComentarios.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoComentarios(txtComentarios.getText().toString());
                    break;
                case R.id.txtSesiones:
                    update(getString(R.string.url_tto), Pojo_Historia_Clinica.recuperar_instancia().getIdTto(), "numeroSesiones", txtSesiones.getText().toString());
                    Pojo_Historia_Clinica.recuperar_instancia().setTtoNumeroSesiones(Integer.valueOf(txtSesiones.getText().toString()));
                    break;
            }
            return true;
        }
        return false;
    }
}