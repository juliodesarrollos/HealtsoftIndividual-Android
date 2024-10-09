package com.juliovazquez.hsind.Activities.Usuario;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Pojos.Pojo_Drawable_Event;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Perfil_Usuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Perfil_Usuario extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText txtNombre, txtCorreo, txtTipoSuscripcion, txtFechaSuscripcion, txtPacientes, txtEventos;
    Button btnSalir;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Perfil_Usuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Perfil_Usuario.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Perfil_Usuario newInstance(String param1, String param2) {
        Fragment_Perfil_Usuario fragment = new Fragment_Perfil_Usuario();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__perfil__usuario, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNombre = getView().findViewById(R.id.txtNombre);
        txtCorreo = getView().findViewById(R.id.txtCorreo);
        txtTipoSuscripcion = getView().findViewById(R.id.txtTipoSuscripcion);
        txtFechaSuscripcion = getView().findViewById(R.id.txtFechaSuscripcion);
        txtPacientes = getView().findViewById(R.id.txtPacientes);
        txtEventos = getView().findViewById(R.id.txtEventos);
        btnSalir = getView().findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalir) {
            cerrar_sesion();
        }
    }

    public void cerrar_sesion() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText(getString(R.string.desea_cerrar_sesion))
                .setConfirmButton(getString(R.string.no), new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelButton(getString(R.string.si), new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            ((ActivityManager) sDialog.getContext().getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();
                        }
                    }
                })
                .show();
    }

    public void llenar_datos() {
        txtNombre.setText(Pojo_Usuario.recuperar_instancia().getName());
        txtCorreo.setText(Pojo_Usuario.recuperar_instancia().getEmail());
        txtTipoSuscripcion.setText(Pojo_Usuario.recuperar_instancia().getTipoSuscripcion());
        txtFechaSuscripcion.setText(Pojo_Usuario.recuperar_instancia().getFechaSuscripcion());
        txtPacientes.setText(Pojo_Paciente.recuperar_instancia().size() + getString(R.string.pacientes_registrados));
        txtEventos.setText(Pojo_Drawable_Event.recuperar_instancia().size() + getString(R.string.eventos_registrados));
    }
}