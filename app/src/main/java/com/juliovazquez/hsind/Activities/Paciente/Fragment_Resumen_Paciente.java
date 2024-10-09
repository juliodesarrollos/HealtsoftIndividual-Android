package com.juliovazquez.hsind.Activities.Paciente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Resumen_Paciente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Resumen_Paciente extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayout layoutResumenPx;
    LinearLayout layoutDx;
    LinearLayout layoutTto;
    LinearLayout layoutOtros;
    LinearLayout layoutSignos;
    LinearLayout layoutNotas;
    TextView txtDxMedico;
    TextView txtDxFisio;
    TextView txtTto;
    TextView txtMotivoConsulta;
    TextView txtCausaMolestia;
    TextView txtPulso;
    TextView txtFr;
    TextView txtGlucosa;
    TextView txtTemperatura;
    TextView txtPresion;
    TextView txtFecha1;
    TextView txtFecha2;
    TextView txtFecha3;
    TextView txtNota1;
    TextView txtNota2;
    TextView txtNota3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Resumen_Paciente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Resumen_Paciente.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Resumen_Paciente newInstance(String param1, String param2) {
        Fragment_Resumen_Paciente fragment = new Fragment_Resumen_Paciente();
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
        layoutResumenPx = getView().findViewById(R.id.layoutResumenPx);
        layoutDx = getView().findViewById(R.id.layoutDx);
        layoutTto = getView().findViewById(R.id.layoutTto);
        layoutOtros = getView().findViewById(R.id.layoutOtros);
        layoutSignos = getView().findViewById(R.id.layoutSignos);
        layoutNotas = getView().findViewById(R.id.layoutNotas);

        txtDxMedico = getView().findViewById(R.id.txtDxMedico);
        txtDxFisio = getView().findViewById(R.id.txtDxFisio);
        txtTto = getView().findViewById(R.id.txtTto);
        txtMotivoConsulta = getView().findViewById(R.id.txtMotivoConsulta);
        txtCausaMolestia = getView().findViewById(R.id.txtCausaMolestia);
        txtPulso = getView().findViewById(R.id.txtPulso);
        txtFr = getView().findViewById(R.id.txtFr);
        txtGlucosa = getView().findViewById(R.id.txtGlucosa);
        txtTemperatura = getView().findViewById(R.id.txtTemperatura);
        txtPresion = getView().findViewById(R.id.txtPresion);
        txtFecha1 = getView().findViewById(R.id.txtFecha1);
        txtFecha2 = getView().findViewById(R.id.txtFecha2);
        txtFecha3 = getView().findViewById(R.id.txtFecha3);
        txtNota1 = getView().findViewById(R.id.txtNota1);
        txtNota2 = getView().findViewById(R.id.txtNota2);
        txtNota3 = getView().findViewById(R.id.txtNota3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resumen_, container, false);
    }

    public void llenar_datos() {
        if (Pojo_Historia_Clinica.recuperar_instancia().getIdTto() != 0 && Pojo_Historia_Clinica.recuperar_instancia().getIdTto() > 0) {
            layoutDx.setVisibility(View.VISIBLE);
            layoutTto.setVisibility(View.VISIBLE);
            txtDxMedico.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoDxMedico());
            txtDxFisio.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoDxFisio());
            txtTto.setText(Pojo_Historia_Clinica.recuperar_instancia().getTtoTratamiento());
        } else {
            layoutDx.setVisibility(View.GONE);
            layoutTto.setVisibility(View.GONE);
        }
        if (Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta() != 0 && Pojo_Historia_Clinica.recuperar_instancia().getIdConsulta() > 0) {
            layoutOtros.setVisibility(View.VISIBLE);
            txtMotivoConsulta.setText(getString(R.string.motivo_consulta) + Pojo_Historia_Clinica.recuperar_instancia().getConsultaMotivoConsulta());
            txtCausaMolestia.setText(getString(R.string.causa_molestia) + Pojo_Historia_Clinica.recuperar_instancia().getConsultaCausaMolestia());
        } else layoutOtros.setVisibility(View.GONE);
        if (Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales() != null) {
            layoutSignos.setVisibility(View.VISIBLE);
            txtPulso.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getPulso() + " BPM");
            txtFr.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getFrecuenciaRespiratoria() + " RPM");
            txtGlucosa.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getGlucosa() + "% O2");
            txtTemperatura.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getTemperatura() + "°C");
            txtPresion.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getPresionSistolica() + "/" + Pojo_Historia_Clinica.recuperar_instancia().getPojoSignos_vitales().getPresionDiastolica());
        } else layoutSignos.setVisibility(View.GONE);
        if (Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas() != null) {
            int count_notas = Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().size();
            layoutNotas.setVisibility(View.VISIBLE);
            if (count_notas >= 3) {
                txtFecha1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getCreated_at().substring(0, 10));
                txtFecha2.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(1).getCreated_at().substring(0, 10));
                txtFecha3.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(2).getCreated_at().substring(0, 10));
                txtNota1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getNota());
                txtNota2.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(1).getNota());
                txtNota3.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(2).getNota());
            } else if (count_notas == 2) {
                txtFecha1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getCreated_at().substring(0, 10));
                txtFecha2.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(1).getCreated_at().substring(0, 10));
                txtNota1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getNota());
                txtNota2.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(1).getNota());
                txtNota3.setVisibility(View.GONE);
                txtFecha3.setVisibility(View.GONE);
            } else if (count_notas == 1) {
                txtFecha1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getCreated_at().substring(0, 10));
                txtNota1.setText(Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().get(0).getNota());
                txtNota3.setVisibility(View.GONE);
                txtNota2.setVisibility(View.GONE);
                txtFecha3.setVisibility(View.GONE);
                txtFecha2.setVisibility(View.GONE);
            }
        } else layoutNotas.setVisibility(View.GONE);
    }
}