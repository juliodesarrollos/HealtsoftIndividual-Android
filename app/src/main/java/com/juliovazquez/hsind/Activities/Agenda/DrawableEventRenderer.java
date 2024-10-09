package com.juliovazquez.hsind.Activities.Agenda;

import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.render.EventRenderer;
import com.juliovazquez.hsind.R;

import java.util.Calendar;
import java.util.Locale;

public class DrawableEventRenderer extends EventRenderer<DrawableCalendarEvent> {
    @Override
    public void render(View view, DrawableCalendarEvent event) {
        ImageView imgPaciente = (ImageView) view.findViewById(R.id.imgPaciente);
        ImageView imgLocation = (ImageView) view.findViewById(R.id.imgLocation);
        ImageView imgTerapeuta = view.findViewById(R.id.imgTerapeuta);
        ImageView imgHora = view.findViewById(R.id.imgHora);
        TextView txtTitle = (TextView) view.findViewById(R.id.view_agenda_event_title);
        TextView txtPaciente = (TextView) view.findViewById(R.id.txtEventPaciente);
        TextView txtDoctor = (TextView) view.findViewById(R.id.txtEventDoctor);
        TextView txtLocation = (TextView) view.findViewById(R.id.view_agenda_event_location);
        TextView txtHora = view.findViewById(R.id.view_agenda_event_hora);
        LinearLayout descriptionContainer = (LinearLayout) view.findViewById(R.id.view_agenda_event_description_container);
        LinearLayout locationContainer = (LinearLayout) view.findViewById(R.id.view_agenda_event_location_container);
        descriptionContainer.setVisibility(View.VISIBLE);

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(event.getStartTime().getTimeInMillis());
        String date = DateFormat.format("HH:mm", cal).toString();

        String paciente = event.getLocation();
        String doctor = event.getDescription();
        String titulo = event.getTitle();
        txtPaciente.setText(paciente);
        txtDoctor.setText(doctor);
        txtTitle.setText(titulo);
        txtLocation.setText(R.string.consultorio_1);
        txtHora.setText(date);
        locationContainer.setVisibility(View.GONE);
        imgPaciente.setColorFilter(Color.WHITE);
        imgTerapeuta.setColorFilter(Color.WHITE);
        imgLocation.setColorFilter(Color.WHITE);
        imgHora.setColorFilter(Color.WHITE);
        locationContainer.setVisibility(View.VISIBLE);
        descriptionContainer.setBackgroundColor(event.getColor());
    }

    @Override
    public int getEventLayout() {
        return R.layout.item_event_calendar;
    }

    @Override
    public Class<DrawableCalendarEvent> getRenderType() {
        return super.getRenderType();
    }
}
