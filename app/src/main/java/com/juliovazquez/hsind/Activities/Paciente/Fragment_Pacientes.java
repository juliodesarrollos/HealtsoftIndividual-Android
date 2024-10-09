package com.juliovazquez.hsind.Activities.Paciente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.tibolte.agendacalendarview.widgets.FloatingActionButton;
import com.juliovazquez.hsind.Adapters.Adapter_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Internet;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Pacientes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Pacientes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rvPAcientes;
    Adapter_Paciente adapterPaciente;
    List<Pojo_Paciente> pacientes = new ArrayList<>();
    List<Pojo_Paciente> pacientesBusqueda = new ArrayList<>();
    SwipeRefreshLayout refreshLayout;
    TextView txtSinPacientes;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mRootView;

    public Fragment_Pacientes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Pacientes.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Pacientes newInstance(String param1, String param2) {
        Fragment_Pacientes fragment = new Fragment_Pacientes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtSinPacientes = getView().findViewById(R.id.txtSinPacientes);
        rvPAcientes = getView().findViewById(R.id.rvPAcientes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvPAcientes.setLayoutManager(gridLayoutManager);
        refreshLayout = getView().findViewById(R.id.refreshPacientes);
        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setAlpha(0.80f);
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        consultar_pacientes();
                    }
                }
        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pojo_Paciente.recuperar_instancia() != null) {
                    int pacientes = 0;
                    switch (Pojo_Usuario.recuperar_instancia().getTipoSuscripcion()) {
                        case "Pacientes Ilimitados":
                            pacientes = 1000000;
                            break;
                        case "10 Pacientes":
                            pacientes = 10;
                            break;
                        case "50 Pacientes":
                            pacientes = 50;
                            break;
                    }
                    if (Pojo_Paciente.recuperar_instancia().size() < pacientes) {
                        abrir_fragmen();
                    } else {
                        Tool_Sweet_Alert.WARNING_TYPE(getContext(), getString(R.string.limite_pacientes));
                    }
                } else abrir_fragmen();
            }
        });
        if (Tool_Internet.isOnlineNet()) {
            consultar_pacientes();
        } else {
            Toast.makeText(getContext(), getString(R.string.sin_internet), Toast.LENGTH_SHORT).show();
        }
    }

    public void abrir_fragmen() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment_Agregar_Paciente d = new Fragment_Agregar_Paciente();
        d.show(fragmentManager, "tag");
        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                super.onFragmentViewDestroyed(fm, f);
                if (d.anyPaciente) consultar_pacientes();
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            }
        }, false);
    }

    public void consultar_pacientes() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.consultar_pacientes(Pojo_Usuario.recuperar_instancia().getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof List) {
                            pacientes.clear();
                            pacientes = (List) res;
                            llenar_busqueda();
                            adapterPaciente = new Adapter_Paciente(getContext(), pacientesBusqueda);
                            rvPAcientes.setAdapter(adapterPaciente);
                            rvPAcientes.setHasFixedSize(true);
                            txtSinPacientes.setVisibility(View.GONE);
                            refreshLayout.setVisibility(View.VISIBLE);
                        } else if (res instanceof Pojo_Codigo_Error) {
                            txtSinPacientes.setVisibility(View.VISIBLE);
                            refreshLayout.setVisibility(View.GONE);
                        } else {
                            Tool_Sweet_Alert.ERROR_TYPE(getContext(), getString(R.string.algo_salio_mal));
                        }
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        };
        tr.start();
    }

    public void llenar_busqueda() {
        pacientesBusqueda.clear();
        for (int i = 0; i < pacientes.size(); i++) {
            pacientesBusqueda.add(pacientes.get(i));
        }
    }

    public void buscar(String busqueda) {
        if (pacientes.size() > 0) {
            pacientesBusqueda.clear();
            for (int i = 0; i < pacientes.size(); i++) {
                if (pacientes.get(i).getNombre().contains(busqueda)) {
                    pacientesBusqueda.add(pacientes.get(i));
                }
            }
            adapterPaciente.notifyDataSetChanged();
        }
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
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_pacientes, container, false);
        }
        return mRootView;
    }
}