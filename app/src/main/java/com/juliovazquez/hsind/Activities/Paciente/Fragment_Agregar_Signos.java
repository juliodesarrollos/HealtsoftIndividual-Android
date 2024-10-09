package com.juliovazquez.hsind.Activities.Paciente;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Agregar_Signos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Agregar_Signos extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText txtTemperatura, txtPulso, txtFr, txtC02, txtPs, txtPd;
    Button btnAgregar;
    boolean anySignos = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Agregar_Signos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Agregar_Signos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Agregar_Signos newInstance(String param1, String param2) {
        Fragment_Agregar_Signos fragment = new Fragment_Agregar_Signos();
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
        txtTemperatura = getView().findViewById(R.id.txtTemperatura);
        txtPulso = getView().findViewById(R.id.txtPulso);
        txtFr = getView().findViewById(R.id.txtFr);
        txtC02 = getView().findViewById(R.id.txtCo2);
        txtPd = getView().findViewById(R.id.txtPd);
        txtPs = getView().findViewById(R.id.txtPs);
        btnAgregar = getView().findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificar_campos()) {
                    agregar_signos();
                }
            }
        });
    }

    private void agregar_signos() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.agregarar_signo(txtTemperatura.getText().toString(), txtPulso.getText().toString(), txtFr.getText().toString(), txtC02.getText().toString(), txtPs.getText().toString(), txtPd.getText().toString(), Pojo_Historia_Clinica.recuperar_instancia().getPacienteId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), (String) res);
                            anySignos = true;
                            dismiss();
                        } else if (res instanceof Pojo_Codigo_Error) {
                            Tool_Sweet_Alert.ERROR_TYPE(getContext(), ((Pojo_Codigo_Error) res).getMessage());
                        } else {
                            Tool_Sweet_Alert.ERROR_TYPE(getContext(), getString(R.string.algo_salio_mal));
                        }
                    }
                });
            }
        };
        tr.start();
    }


    public boolean verificar_campos() {
        if (TextUtils.isEmpty(txtTemperatura.getText())) {
            txtTemperatura.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtPulso.getText())) {
            txtPulso.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtFr.getText())) {
            txtFr.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtC02.getText())) {
            txtC02.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtPd.getText())) {
            txtPd.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtPs.getText())) {
            txtPs.setError(getString(R.string.campo_requerido));
            return false;
        } else return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__agregar__signos, container, false);
    }
}