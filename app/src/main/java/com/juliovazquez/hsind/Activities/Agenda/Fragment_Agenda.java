package com.juliovazquez.hsind.Activities.Agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.github.tibolte.agendacalendarview.widgets.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Drawable_Event;
import com.juliovazquez.hsind.Pojos.Pojo_Paciente;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;
import com.riontech.calendar.CustomCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Agenda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Agenda extends Fragment implements CalendarPickerController {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String idEvent = null;
    AgendaCalendarView mAgendaCalendarView;
    Calendar maxDate;
    Calendar minDate;
    CalendarPickerController calendarPickerController;
    List<CalendarEvent> eventList = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CustomCalendar customCalendar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mRootView;

    public Fragment_Agenda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Agenda.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Agenda newInstance(String param1, String param2) {
        Fragment_Agenda fragment = new Fragment_Agenda();
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

        minDate = Calendar.getInstance();
        maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        mAgendaCalendarView = getView().findViewById(R.id.agenda_calendar_view);
        mAgendaCalendarView.setSaveEnabled(false);
        calendarPickerController = this;

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setAlpha(0.80f);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Pojo_Paciente.recuperar_instancia() != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment_Agregar_Evento d = new Fragment_Agregar_Evento();
                    d.show(fragmentManager, "tag");
                    fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                            super.onFragmentViewDestroyed(fm, f);
                            if (d.anyEvent) getEvents();
                            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                        }
                    }, false);
                } else
                    Tool_Sweet_Alert.ERROR_TYPE(getContext(), getString(R.string.crear_evento_sin_pacientes));
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult().getToken();
                        update(Pojo_Usuario.recuperar_instancia().getId(), token);
                    }
                });

        getEvents();
    }

    private void update(int id, String token) {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.update_token(id, token);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        };
        tr.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        }
        return mRootView;
    }

    @Override
    public void onDaySelected(DayItem dayItem) {

    }

    public void details_event(String id) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment_Info_Evento d = new Fragment_Info_Evento(id);
        d.show(fragmentManager, "tag");
        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                super.onFragmentViewDestroyed(fm, f);
                if (d.anyDeleted) getEvents();
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            }
        }, false);
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        if (Pojo_Drawable_Event.recuperar_instancia() != null && event.getId() > 0)
            details_event(String.valueOf(event.getId()));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {

    }

    private void getEvents() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.consultar_eventos(Pojo_Usuario.recuperar_instancia().getId(), getContext());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof List) {
                            eventList.clear();
                            eventList = ((List<CalendarEvent>) res);
                            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), calendarPickerController);
                            mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
                            if (idEvent != null) details_event(idEvent);
                            idEvent = null;
                        } else if (res instanceof Pojo_Codigo_Error) {
                            eventList.clear();
                            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), calendarPickerController);
                            mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
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