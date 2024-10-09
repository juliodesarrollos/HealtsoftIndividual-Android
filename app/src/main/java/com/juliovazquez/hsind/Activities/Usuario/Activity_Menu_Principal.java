package com.juliovazquez.hsind.Activities.Usuario;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.juliovazquez.hsind.Activities.Agenda.Fragment_Agenda;
import com.juliovazquez.hsind.Activities.Paciente.Fragment_Pacientes;
import com.juliovazquez.hsind.R;

public class Activity_Menu_Principal extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int INTERVALO = 2000; //2 segundos para salir
    public String idEvent = null;
    BottomNavigationView bottomNavigationView;
    MenuItem searchItem;
    MenuInflater inflater;
    private long tiempoPrimerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        if (getIntent().getStringExtra("ID_EVENT") != null) {
            idEvent = getIntent().getStringExtra("ID_EVENT");
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        goAgenda();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscar, menu);

        searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Buscar");
        searchItem.setVisible(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Fragment_Pacientes fragment_pacientes = (Fragment_Pacientes) getSupportFragmentManager().findFragmentById(R.id.fragmentPacientes);
                fragment_pacientes.buscar(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aviso:
                ver_aviso();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_agenda:
                setTitle(getString(R.string.agenda));
                item.setChecked(true);
                searchItem.setVisible(false);
                goAgenda();
                break;
            case R.id.menu_pacientes:
                searchItem.setVisible(true);
                setTitle(getString(R.string.pacientes));
                item.setChecked(true);
                goPacientes();
                break;
            case R.id.menu_perfil:
                searchItem.setVisible(false);
                setTitle(getString(R.string.perfil));
                item.setChecked(true);
                goPerfil();
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, getString(R.string.presionar_otra_vez), Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    public void goPacientes() {
        Fragment_Perfil_Usuario fragment_perfil_usuario = (Fragment_Perfil_Usuario) getSupportFragmentManager().findFragmentById(R.id.fragmentPerfil);
        Fragment_Agenda fragment_agenda = (Fragment_Agenda) getSupportFragmentManager().findFragmentById(R.id.fragmentAgenda);
        Fragment_Pacientes fragment_pacientes = (Fragment_Pacientes) getSupportFragmentManager().findFragmentById(R.id.fragmentPacientes);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment_agenda);
        fragmentTransaction.hide(fragment_perfil_usuario);
        fragmentTransaction.show(fragment_pacientes);
        fragmentTransaction.commitNow();
    }

    public void goAgenda() {
        Fragment_Perfil_Usuario fragment_perfil_usuario = (Fragment_Perfil_Usuario) getSupportFragmentManager().findFragmentById(R.id.fragmentPerfil);
        Fragment_Agenda fragment_agenda = (Fragment_Agenda) getSupportFragmentManager().findFragmentById(R.id.fragmentAgenda);
        Fragment_Pacientes fragment_pacientes = (Fragment_Pacientes) getSupportFragmentManager().findFragmentById(R.id.fragmentPacientes);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment_agenda);
        fragmentTransaction.hide(fragment_perfil_usuario);
        fragmentTransaction.hide(fragment_pacientes);
        fragmentTransaction.commitNow();
        if (idEvent != null) fragment_agenda.idEvent = idEvent;
        idEvent = null;
    }

    public void goPerfil() {
        Fragment_Perfil_Usuario fragment_perfil_usuario = (Fragment_Perfil_Usuario) getSupportFragmentManager().findFragmentById(R.id.fragmentPerfil);
        Fragment_Agenda fragment_agenda = (Fragment_Agenda) getSupportFragmentManager().findFragmentById(R.id.fragmentAgenda);
        Fragment_Pacientes fragment_pacientes = (Fragment_Pacientes) getSupportFragmentManager().findFragmentById(R.id.fragmentPacientes);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment_perfil_usuario);
        fragmentTransaction.hide(fragment_agenda);
        fragmentTransaction.hide(fragment_pacientes);
        fragmentTransaction.commitNow();
        fragment_perfil_usuario.llenar_datos();
    }

    public void ver_aviso() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment_Aviso_Privacidad d = new Fragment_Aviso_Privacidad();
        d.show(fragmentManager, "tag");
        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                super.onFragmentViewDestroyed(fm, f);
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            }
        }, false);
    }
}