package com.juliovazquez.hsind.Activities.Usuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.juliovazquez.hsind.Pojos.Pojo_Codigo_Error;
import com.juliovazquez.hsind.Pojos.Pojo_Usuario;
import com.juliovazquez.hsind.R;
import com.juliovazquez.hsind.Services.Service_Consumo;
import com.juliovazquez.hsind.Tools.Tool_Internet;
import com.juliovazquez.hsind.Tools.Tool_Sweet_Alert;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Activity_Login extends AppCompatActivity {

    Button btnLogin;
    EditText txtCorreo;
    EditText txtPassword;
    CheckBox chkSesion;
    String password;
    String correo;
    Pojo_Usuario pojoUsuario;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("");

        context = getApplicationContext();
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkSesion = findViewById(R.id.chkSesion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = txtCorreo.getText().toString();
                password = txtPassword.getText().toString();
                if (Tool_Internet.isOnlineNet()) {
                    if (verificar_campos()) {
                        taskBtnAcces();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.sin_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mostrar_error(String codigo) {
        if (codigo.equals("004")) {
            txtPassword.setError(getString(R.string.verificar_contraseña));
            Tool_Sweet_Alert.ERROR_TYPE(Activity_Login.this, getString(R.string.verificar_contraseña));
        } else if (codigo.equals("003")) {
            txtCorreo.setError(getString(R.string.verificar_correo));
            Tool_Sweet_Alert.ERROR_TYPE(getApplicationContext(), getString(R.string.verificar_correo));
        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.setTitleText(getString(R.string.sin_suscripcion))
                    .setConfirmButton(getString(R.string.ok), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .setCancelButton(getString(R.string.ir_sitio), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Uri uri = Uri.parse(getString(R.string.url_healtsoft));
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    })
                    .show();
        }
    }

    private boolean verificar_campos() {
        if (TextUtils.isEmpty(txtCorreo.getText())) {
            txtCorreo.setError(getString(R.string.campo_requerido));
            return false;
        } else if (TextUtils.isEmpty(txtPassword.getText())) {
            txtPassword.setError(getString(R.string.campo_requerido));
            return false;
        }
        return true;
    }

    public void guardar_sesion() {
        SharedPreferences settings = getSharedPreferences("LOGIN_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        if (chkSesion.isChecked()) {
            editor.putBoolean("SAVE_SESSION", true);
            editor.putString("EMAIL", correo);
            editor.putString("PASSWORD", password);
            editor.putString("NAME", pojoUsuario.getName());
            editor.putInt("ID", pojoUsuario.getId());
            editor.putString("FS", pojoUsuario.getFechaSuscripcion());
            editor.putString("TS", pojoUsuario.getTipoSuscripcion());
        } else {
            editor.putBoolean("SAVE_SESSION", false);
        }
        editor.apply();
    }

    public void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            update(Pojo_Usuario.recuperar_instancia().getId(), newToken);
        });
    }

    private void update(int id, String token) {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.update_token(id, token);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        };
        tr.start();
    }

    public void goMainScreen() {
        Intent intent = new Intent(this, Activity_Menu_Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void taskBtnAcces() {
        final Thread tr = new Thread() {
            @Override
            public void run() {
                super.run();
                final Object res = Service_Consumo.login(correo, password);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (res instanceof Pojo_Usuario) {
                            pojoUsuario = Pojo_Usuario.recuperar_instancia();
                            guardar_sesion();
                            consultar_pacientes();
                            getToken();
                            goMainScreen();
                        } else if (res instanceof Pojo_Codigo_Error) {
                            String codigo = ((Pojo_Codigo_Error) res).getId();
                            mostrar_error(codigo);
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.algo_salio_mal) + res.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        tr.start();
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

                    }
                });
            }
        };
        tr.start();
    }
}