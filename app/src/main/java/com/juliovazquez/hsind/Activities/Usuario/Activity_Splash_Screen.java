package com.juliovazquez.hsind.Activities.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Activity_Splash_Screen extends AppCompatActivity {

    String name, email, pass, fs, ts, idEvent = null;
    int id;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (getIntent().hasExtra("ID")) {
            idEvent = getIntent().getStringExtra("ID");
        }

        mHandler.postDelayed(new Runnable() {
            public void run() {
                checkPreferences();
            }
        }, 2500);
    }

    public void checkPreferences() {
        SharedPreferences settings = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE);
        Boolean guardarSesion = settings.getBoolean("SAVE_SESSION", false);
        if (guardarSesion) {
            name = settings.getString("NAME", "N/A");
            id = settings.getInt("ID", 0);
            email = settings.getString("EMAIL", "N/A");
            pass = settings.getString("PASSWORD", "N/A");
            fs = settings.getString("FS", "N/A");
            ts = settings.getString("TS", "N/A");
            Pojo_Usuario.crear_instancia(id, name, email, pass, fs, ts);
            consultar_pacientes();
        } else goLoginScreen();
    }

    public void goLoginScreen() {
        Intent intent = new Intent(this, Activity_Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void consultar_pacientes() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.consultar_pacientes(Pojo_Usuario.recuperar_instancia().getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        goMainScreen();
                    }
                });
            }
        };
        tr.start();
    }

    public void goMainScreen() {
        Intent intent = new Intent(this, Activity_Menu_Principal.class);
        intent.putExtra("ID_EVENT", idEvent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}