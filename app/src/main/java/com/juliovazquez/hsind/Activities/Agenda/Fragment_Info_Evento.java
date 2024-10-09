package com.juliovazquez.hsind.Activities.Agenda;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.juliovazquez.hsind.Activities.Paciente.Activity_Menu_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Evento;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Fecha;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Info_Evento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Info_Evento extends DialogFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    boolean anyDeleted = false;
    String eventid = null;
    Pojo_Evento pojo_evento;
    Calendar date;
    EditText txtDateTime, txtColor, txtDescripcion, txtTitulo;
    Spinner spPacientes;
    String color = "";
    RadioGroup colorGroup1, colorGroup2, colorGroup3;
    LinearLayout layoutColor;
    RadioButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15;
    int idPaciente = 0;
    List<Pojo_Paciente> pacientes = new ArrayList<>();
    Button btnWhats, btnVer, btnEliminar, btnEditar;
    LinearLayout layoutEvent;
    Date event_date, current;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Info_Evento() {
        // Required empty public constructor
    }

    public Fragment_Info_Evento(String evento) {
        // Required empty public constructor
        eventid = evento;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Info_Evento.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Info_Evento newInstance(String param1, String param2) {
        Fragment_Info_Evento fragment = new Fragment_Info_Evento();
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
        return inflater.inflate(R.layout.fragment__info__evento, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (eventid != null) {
            layoutEvent = getView().findViewById(R.id.layoutEvent);
            layoutEvent.setVisibility(View.GONE);
            btnEliminar = getView().findViewById(R.id.btnEliminar);
            btnEditar = getView().findViewById(R.id.btnEditar);
            btnVer = getView().findViewById(R.id.btnVer);
            btnWhats = getView().findViewById(R.id.btnWhats);
            txtDateTime = getView().findViewById(R.id.txtDateTime);
            spPacientes = getView().findViewById(R.id.spPaciente);
            txtColor = getView().findViewById(R.id.txtColor);
            txtDescripcion = getView().findViewById(R.id.txtDescripcion);
            txtTitulo = getView().findViewById(R.id.txtTitulo);
            colorGroup1 = getView().findViewById(R.id.colorGroup1);
            colorGroup2 = getView().findViewById(R.id.colorGroup2);
            colorGroup3 = getView().findViewById(R.id.colorGroup3);
            layoutColor = getView().findViewById(R.id.layoutColor);
            c1 = getView().findViewById(R.id.color1);
            c2 = getView().findViewById(R.id.color2);
            c3 = getView().findViewById(R.id.color3);
            c4 = getView().findViewById(R.id.color4);
            c5 = getView().findViewById(R.id.color5);
            c6 = getView().findViewById(R.id.color6);
            c7 = getView().findViewById(R.id.color7);
            c8 = getView().findViewById(R.id.color8);
            c9 = getView().findViewById(R.id.color9);
            c10 = getView().findViewById(R.id.color10);
            c11 = getView().findViewById(R.id.color11);
            c12 = getView().findViewById(R.id.color12);
            c13 = getView().findViewById(R.id.color13);
            c14 = getView().findViewById(R.id.color14);
            c15 = getView().findViewById(R.id.color15);

            c1.setOnClickListener(this::onClick);
            c2.setOnClickListener(this::onClick);
            c3.setOnClickListener(this::onClick);
            c4.setOnClickListener(this::onClick);
            c5.setOnClickListener(this::onClick);
            c6.setOnClickListener(this::onClick);
            c7.setOnClickListener(this::onClick);
            c8.setOnClickListener(this::onClick);
            c9.setOnClickListener(this::onClick);
            c10.setOnClickListener(this::onClick);
            c11.setOnClickListener(this::onClick);
            c12.setOnClickListener(this::onClick);
            c13.setOnClickListener(this::onClick);
            c14.setOnClickListener(this::onClick);
            c15.setOnClickListener(this::onClick);
            txtDateTime.setOnClickListener(this::onClick);
            txtColor.setOnClickListener(this::onClick);
            btnWhats.setOnClickListener(this::onClick);
            btnVer.setOnClickListener(this::onClick);
            btnEliminar.setOnClickListener(this::onClick);
            btnEditar.setOnClickListener(this::onClick);

            pacientes.clear();
            pacientes = Pojo_Paciente.recuperar_instancia();
            String[] nombres = new String[pacientes.size()];
            for (int i = 0; i < pacientes.size(); i++) {
                nombres[i] = pacientes.get(i).getNombre();
            }
            spPacientes.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, nombres));

            getEvent();
        } else dismiss();
    }

    @Override
    public void onClick(View v) {
        boolean checked = false;
        if (v instanceof RadioButton) checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.txtDateTime:
                showDateTimePicker();
                break;
            case R.id.txtColor:
                layoutColor.setVisibility(View.VISIBLE);
                break;
            case R.id.color1:
                if (checked) color = getString(R.string.color1);
                limpiar_colores();
                break;
            case R.id.color2:
                if (checked) color = getString(R.string.color2);
                limpiar_colores();
                break;
            case R.id.color3:
                if (checked) color = getString(R.string.color3);
                limpiar_colores();
                break;
            case R.id.color4:
                if (checked) color = getString(R.string.color4);
                limpiar_colores();
                break;
            case R.id.color5:
                if (checked) color = getString(R.string.color5);
                limpiar_colores();
                break;
            case R.id.color6:
                if (checked) color = getString(R.string.color6);
                limpiar_colores();
                break;
            case R.id.color7:
                if (checked) color = getString(R.string.color7);
                limpiar_colores();
                break;
            case R.id.color8:
                if (checked) color = getString(R.string.color8);
                limpiar_colores();
                break;
            case R.id.color9:
                if (checked) color = getString(R.string.color9);
                limpiar_colores();
                break;
            case R.id.color10:
                if (checked) color = getString(R.string.color10);
                limpiar_colores();
                break;
            case R.id.color11:
                if (checked) color = getString(R.string.color11);
                limpiar_colores();
                break;
            case R.id.color12:
                if (checked) color = getString(R.string.color12);
                limpiar_colores();
                break;
            case R.id.color13:
                if (checked) color = getString(R.string.color13);
                limpiar_colores();
                break;
            case R.id.color14:
                if (checked) color = getString(R.string.color14);
                limpiar_colores();
                break;
            case R.id.color15:
                if (checked) color = getString(R.string.color15);
                limpiar_colores();
                break;
            case R.id.btnVer:
                goPaciente();
                break;
            case R.id.btnWhats:
                goWhatsApp();
                break;
            case R.id.btnEditar:
                actualizar_evento();
                break;
            case R.id.btnEliminar:
                eliminar_evento();
                break;
        }
    }

    private void actualizar_evento() {
        Date new_event_date = Tool_Fecha.getDateTimeFromString(txtDateTime.getText().toString());
        if (new_event_date.after(current)) {
            actualizar_evento_service();
        } else {
            Tool_Sweet_Alert.ERROR_TYPE_CONTENT(getContext(), getString(R.string.error_update_cita), getString(R.string.hora_fecha_anteriores_update));
        }
    }

    private void eliminar_evento() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText(getString(R.string.desea_eliminar_cita))
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
                        eliminar_evento_service();
                    }
                })
                .show();
    }

    private void actualizar_evento_service() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                idPaciente = pacientes.get((int) spPacientes.getSelectedItemId()).getId();
                String cadenaNormalize3 = Normalizer.normalize(txtColor.getText().toString(), Normalizer.Form.NFD);
                color = cadenaNormalize3.replaceAll("[^\\p{ASCII}]", "");
                String cadenaNormalize = Normalizer.normalize(txtTitulo.getText().toString(), Normalizer.Form.NFD);
                String title = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
                String cadenaNormalize2 = Normalizer.normalize(txtDescripcion.getText().toString(), Normalizer.Form.NFD);
                String description = cadenaNormalize2.replaceAll("[^\\p{ASCII}]", "");
                final Object res = Service_Consumo.actualizar_evento(title, description, color, txtDateTime.getText().toString(), txtDateTime.getText().toString(), idPaciente, eventid);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), (String) res);
                            anyDeleted = true;
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

    private void eliminar_evento_service() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.eliminar_evento(eventid);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof String) {
                            Tool_Sweet_Alert.SUCCESS_TYPE(getContext(), (String) res);
                            anyDeleted = true;
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

    private void goWhatsApp() {
        String mensaje = "Hola!, el especialista " + Pojo_Usuario.recuperar_instancia().getName() + " le recuerda su cita con fecha " + pojo_evento.getStart();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone=521" + pojo_evento.getTelefono() + "&text=" + mensaje;
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    private void goPaciente() {
        int id = Integer.valueOf(pojo_evento.getIdPaciente());
        Intent i = new Intent(getContext(), Activity_Menu_Paciente.class);
        i.putExtra("id", id);
        getContext().startActivity(i);
    }

    private void llenar_datos() {
        color = pojo_evento.getColor();
        txtTitulo.setText(pojo_evento.getTitle());
        txtDescripcion.setText(pojo_evento.getDescription());
        txtDateTime.setText(pojo_evento.getStart());
        limpiar_colores();
        int idPaciente = pojo_evento.getIdPaciente();
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId() == idPaciente) spPacientes.setSelection(i);
        }
        layoutEvent.setVisibility(View.VISIBLE);
    }

    private void habilitar_botones() {
        event_date = Tool_Fecha.getDateTimeFromString(pojo_evento.getStart());
        current = Tool_Fecha.getCurrentDate();
        if (current.after(event_date)) {
            btnEliminar.setEnabled(false);
            btnEditar.setEnabled(false);
            btnWhats.setEnabled(false);
            btnEliminar.setBackgroundResource(R.drawable.boton_deshabilitado);
            btnEditar.setBackgroundResource(R.drawable.boton_deshabilitado);
            btnWhats.setBackgroundResource(R.drawable.boton_deshabilitado);
        }
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        int month = monthOfYear + 1;
                        txtDateTime.setText(date.get(Calendar.YEAR) + "-" + month + "-" + date.get(Calendar.DAY_OF_MONTH) + " " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":00");
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    private void getEvent() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.recuperar_evento(eventid);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof Pojo_Evento) {
                            pojo_evento = (Pojo_Evento) res;
                            llenar_datos();
                            habilitar_botones();
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

    private void limpiar_colores() {
        txtColor.setText(color);
        int myColor = Color.parseColor(color);
        txtColor.setBackgroundColor(myColor);
        txtColor.setTextColor(Color.WHITE);
        colorGroup1.clearCheck();
        colorGroup2.clearCheck();
        colorGroup3.clearCheck();
        layoutColor.setVisibility(View.GONE);
    }

    private boolean verificar_campos() {
        if (TextUtils.isEmpty(txtTitulo.getText())) {
            txtTitulo.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtColor.getText())) {
            txtColor.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtDescripcion.getText())) {
            txtDescripcion.setText(getString(R.string.sin_comentarios));
            return true;
        } else if (TextUtils.isEmpty(txtDateTime.getText())) {
            txtDateTime.setError(getString(R.string.campo_requerido));
            return false;
        }
        return true;
    }
}