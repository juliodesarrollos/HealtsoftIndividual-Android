package com.juliovazquez.hsind.Activities.Paciente;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import java.text.Normalizer;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Agregar_Paciente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Agregar_Paciente extends DialogFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public boolean anyPaciente = false;
    EditText txtFechaNacimiento;
    DatePickerDialog picker;
    EditText txtNombre;
    EditText txtCorreo;
    EditText txtTelefono;
    Button btnAgregar;
    String fechaNacimiento = "";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Agregar_Paciente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Agregar_Paciente.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Agregar_Paciente newInstance(String param1, String param2) {
        Fragment_Agregar_Paciente fragment = new Fragment_Agregar_Paciente();
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
        txtFechaNacimiento = getView().findViewById(R.id.txtFechaNacimiento);
        txtNombre = getView().findViewById(R.id.txtNombre);
        txtCorreo = getView().findViewById(R.id.txtCorreo);
        txtTelefono = getView().findViewById(R.id.txtTelefono);
        btnAgregar = getView().findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        txtFechaNacimiento.setOnClickListener(this);
    }

    private void agregar_paciente() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                String cadenaNormalize = Normalizer.normalize(txtNombre.getText().toString(), Normalizer.Form.NFD);
                String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
                String cadenaNormalize2 = Normalizer.normalize(txtCorreo.getText().toString(), Normalizer.Form.NFD);
                String cadenaSinAcentos2 = cadenaNormalize2.replaceAll("[^\\p{ASCII}]", "");
                final Object res = Service_Consumo.agregarar_pacientes(cadenaSinAcentos, fechaNacimiento, cadenaSinAcentos2, txtTelefono.getText().toString(), Pojo_Usuario.recuperar_instancia().getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), (String) res);
                            anyPaciente = true;
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

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void date_picker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(getContext(), R.style.MySpinnerDatePickerStyle, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dia = day < 10 ? ("0" + day) : (day + "");
                String mes = month < 10 ? ("0" + month) : (month + "");
                fechaNacimiento = year + "-" + mes + "-" + dia;
                txtFechaNacimiento.setText(fechaNacimiento);
            }
        }, year, month, day);
        picker.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_paciente, container, false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnAgregar:
                if (verificar_campos())
                    agregar_paciente();
                break;
            case R.id.txtFechaNacimiento:
                date_picker();
                break;
        }
    }

    private boolean verificar_campos() {
        if (TextUtils.isEmpty(txtNombre.getText())) {
            txtNombre.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtFechaNacimiento.getText())) {
            txtFechaNacimiento.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtCorreo.getText())) {
            txtCorreo.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtTelefono.getText())) {
            txtTelefono.setError(getString(R.string.campo_requerido));
            return false;
        }
        return true;
    }
}