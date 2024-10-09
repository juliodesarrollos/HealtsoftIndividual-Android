package com.juliovazquez.hsind.Activities.Paciente;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.widgets.FloatingActionButton;
import com.juliovazquez.hsind.Adapters.Adapter_Nota;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.Pojos.Pojo_Nota;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Notas_Paciente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Notas_Paciente extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvNotas;
    Adapter_Nota adapterNota;
    List<Pojo_Nota> pojoNotas = new ArrayList<>();
    TextView txtSinNotas;
    boolean notas = false;

    public Fragment_Notas_Paciente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Notas_Paciente.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Notas_Paciente newInstance(String param1, String param2) {
        Fragment_Notas_Paciente fragment = new Fragment_Notas_Paciente();
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
        rvNotas = (RecyclerView) getView().findViewById(R.id.rvNotas);
        txtSinNotas = getView().findViewById(R.id.txtSinNotas);
        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setAlpha(0.80f);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvNotas.setLayoutManager(gridLayoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment_Agregar_Nota d = new Fragment_Agregar_Nota();
                d.show(fragmentManager, "tag");
                fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                        super.onFragmentViewDestroyed(fm, f);
                        if (d.anyNota)consultar_notas();
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                    }
                }, false);
            }
        });
    }

    public void consultar_notas() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.recuperar_notas(Pojo_Historia_Clinica.recuperar_instancia().getPacienteId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof List) {
                            show_notas();
                            notas = true;
                        } else if (res instanceof String) {
                            rvNotas.setVisibility(View.GONE);
                            txtSinNotas.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public void show_notas() {
        pojoNotas.clear();
        pojoNotas = Pojo_Nota.recuperar_instancia();
        adapterNota = new Adapter_Nota(getContext(), pojoNotas);
        rvNotas.setAdapter(adapterNota);
        rvNotas.setHasFixedSize(true);
        rvNotas.setVisibility(View.VISIBLE);
        txtSinNotas.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notas__paciente, container, false);
    }
}