package com.juliovazquez.hsind.Activities.Paciente;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.juliovazquez.hsind.Pojos.Pojo_Historia_Clinica;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.squareup.picasso.Picasso;

public class Activity_Menu_Paciente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView txtHeaderName;
    ImageView imgHeaderPx;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_);
        id = getIntent().getExtras().getInt("id");
        Toolbar toolbar = findViewById(R.id.toolbar_pacientes);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        consultar_hc();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_resumen:
                setTitle(getString(R.string.resumen_paciente));
                goResumen();
                break;
            case R.id.nav_item_hc:
                setTitle(getString(R.string.historia_clinica));
                goHC();
                break;
            case R.id.nav_item_signos:
                setTitle(getString(R.string.signos_vitales));
                goSignos();
                break;
            case R.id.nav_item_notas:
                setTitle(getString(R.string.notas_terapeuticas));
                goNotas();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    public void goHC() {
        Fragment_Resumen_Paciente _fragmentResumenPaciente = (Fragment_Resumen_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentResumen);
        Fragment_Historia_Clinica fragment_historia_clinica = (Fragment_Historia_Clinica) getSupportFragmentManager().findFragmentById(R.id.fragmentHC);
        Fragment_Notas_Paciente fragmentNotas_paciente = (Fragment_Notas_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentNotas);
        Fragment_Signos_Paciente fragmentSignos_paciente = (Fragment_Signos_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentSignos);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment_historia_clinica);
        fragmentTransaction.hide(fragmentNotas_paciente);
        fragmentTransaction.hide(fragmentSignos_paciente);
        fragmentTransaction.hide(_fragmentResumenPaciente);
        fragmentTransaction.commitNow();
        fragment_historia_clinica.llenar_datos();
    }

    public void goNotas() {
        Fragment_Resumen_Paciente _fragmentResumenPaciente = (Fragment_Resumen_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentResumen);
        Fragment_Historia_Clinica fragment_historia_clinica = (Fragment_Historia_Clinica) getSupportFragmentManager().findFragmentById(R.id.fragmentHC);
        Fragment_Notas_Paciente fragmentNotas_paciente = (Fragment_Notas_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentNotas);
        Fragment_Signos_Paciente fragmentSignos_paciente = (Fragment_Signos_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentSignos);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragmentNotas_paciente);
        fragmentTransaction.hide(fragment_historia_clinica);
        fragmentTransaction.hide(fragmentSignos_paciente);
        fragmentTransaction.hide(_fragmentResumenPaciente);
        fragmentTransaction.commitNow();
        fragmentNotas_paciente.consultar_notas();
    }

    public void goSignos() {
        Fragment_Resumen_Paciente _fragmentResumenPaciente = (Fragment_Resumen_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentResumen);
        Fragment_Historia_Clinica fragment_historia_clinica = (Fragment_Historia_Clinica) getSupportFragmentManager().findFragmentById(R.id.fragmentHC);
        Fragment_Notas_Paciente fragmentNotas_paciente = (Fragment_Notas_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentNotas);
        Fragment_Signos_Paciente fragmentSignos_paciente = (Fragment_Signos_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentSignos);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragmentSignos_paciente);
        fragmentTransaction.hide(fragmentNotas_paciente);
        fragmentTransaction.hide(fragment_historia_clinica);
        fragmentTransaction.hide(_fragmentResumenPaciente);
        fragmentTransaction.commitNow();
        if (!fragmentSignos_paciente.signos) fragmentSignos_paciente.consultar_signos();
    }

    public void goResumen() {
        Fragment_Resumen_Paciente _fragmentResumenPaciente = (Fragment_Resumen_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentResumen);
        Fragment_Historia_Clinica fragment_historia_clinica = (Fragment_Historia_Clinica) getSupportFragmentManager().findFragmentById(R.id.fragmentHC);
        Fragment_Notas_Paciente fragmentNotas_paciente = (Fragment_Notas_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentNotas);
        Fragment_Signos_Paciente fragmentSignos_paciente = (Fragment_Signos_Paciente) getSupportFragmentManager().findFragmentById(R.id.fragmentSignos);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentSignos_paciente);
        fragmentTransaction.hide(fragmentNotas_paciente);
        fragmentTransaction.hide(fragment_historia_clinica);
        fragmentTransaction.show(_fragmentResumenPaciente);
        fragmentTransaction.commitNow();
        _fragmentResumenPaciente.llenar_datos();
    }

    public void consultar_hc() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.historia_clinica(id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof Pojo_Historia_Clinica) {
                            setTitle(getString(R.string.resumen_paciente));
                            View header_view = navigationView.getHeaderView(0);
                            txtHeaderName = header_view.findViewById(R.id.nav_header_textView);
                            txtHeaderName.setText(Pojo_Historia_Clinica.recuperar_instancia().getPacienteNombre());
                            imgHeaderPx = header_view.findViewById(R.id.nav_header_imageView);
                            Picasso.with(getApplicationContext())
                                    .load(getString(R.string.url_storage) + Pojo_Historia_Clinica.recuperar_instancia().getPersonalesFotoUrl())
                                    .placeholder(R.drawable.ic_prerfil_fb_trz_blanco)
                                    .error(R.drawable.ic_prerfil_fb_trz_blanco)
                                    .resize(100, 100)
                                    .centerCrop()
                                    .into(imgHeaderPx);
                            goResumen();
                            //llenar_datos();
                        }
                    }
                });
            }
        };
        tr.start();
    }
}