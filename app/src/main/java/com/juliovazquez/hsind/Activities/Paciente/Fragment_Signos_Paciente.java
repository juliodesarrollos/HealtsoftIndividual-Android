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
import com.juliovazquez.hsind.Adapters.Adapter_Signo;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.Pojos.Pojo_Signos_Vitales;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Signos_Paciente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Signos_Paciente extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvSignos;
    Adapter_Signo adapterSigno;
    List<Pojo_Signos_Vitales> signosVitales = new ArrayList<>();
    TextView txtSinSignos;
    boolean signos = false;

    public Fragment_Signos_Paciente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Signos_Paciente.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Signos_Paciente newInstance(String param1, String param2) {
        Fragment_Signos_Paciente fragment = new Fragment_Signos_Paciente();
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
        rvSignos = (RecyclerView) getView().findViewById(R.id.rvSignos);
        txtSinSignos = getView().findViewById(R.id.txtSinSignos);
        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setAlpha(0.80f);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvSignos.setLayoutManager(gridLayoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment_Agregar_Signos d = new Fragment_Agregar_Signos();
                d.show(fragmentManager, "tag");
                fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                        super.onFragmentViewDestroyed(fm, f);
                        if (d.anySignos) consultar_signos();
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                    }
                }, false);
            }
        });
    }

    public void consultar_signos() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.recuperar_signos(Pojo_Historia_Clinica.recuperar_instancia().getPacienteId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof List) {
                            show_signos();
                            signos = true;
                        } else if (res instanceof String) {
                            rvSignos.setVisibility(View.GONE);
                            txtSinSignos.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    private void show_signos() {
        signosVitales.clear();
        signosVitales = Pojo_Signos_Vitales.recuperar_instancia();
        adapterSigno = new Adapter_Signo(getContext(), signosVitales);
        rvSignos.setAdapter(adapterSigno);
        rvSignos.setHasFixedSize(true);
        rvSignos.setVisibility(View.VISIBLE);
        txtSinSignos.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signos__paciente, container, false);
    }
}