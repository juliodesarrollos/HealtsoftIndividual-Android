package com.juliovazquez.hsind.Activities.Paciente;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.Pojos.Pojo_Nota;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Fecha;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Agregar_Nota#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Agregar_Nota extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText txtNota;
    Button btnAgregar;
    boolean anyNota = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Agregar_Nota() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Agregar_Nota.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Agregar_Nota newInstance(String param1, String param2) {
        Fragment_Agregar_Nota fragment = new Fragment_Agregar_Nota();
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
        txtNota = getView().findViewById(R.id.txtNota);
        btnAgregar = getView().findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtNota.getText())) {
                    txtNota.setError(getString(R.string.error_ingresar_nota));
                } else agregar_nota();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar__nota, container, false);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void agregar_nota() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                String cadenaNormalize = Normalizer.normalize(txtNota.getText().toString(), Normalizer.Form.NFD);
                String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
                final Object res = Service_Consumo.agregarar_nota(cadenaSinAcentos, Pojo_Historia_Clinica.recuperar_instancia().getPacienteId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), (String) res);
                            if (Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas() == null) {
                                List<Pojo_Nota> pojoNotas = new ArrayList<>();
                                pojoNotas.add(new Pojo_Nota(0, cadenaSinAcentos, 0, Tool_Fecha.getStringCurrentDate(), ""));
                                Pojo_Historia_Clinica.recuperar_instancia().setPojoNotas(pojoNotas);
                            } else
                                Pojo_Historia_Clinica.recuperar_instancia().getPojoNotas().add(0, new Pojo_Nota(0, cadenaSinAcentos, 0, Tool_Fecha.getStringCurrentDate(), ""));
                            anyNota = true;
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
}