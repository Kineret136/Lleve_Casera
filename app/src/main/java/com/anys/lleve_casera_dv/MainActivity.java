package com.anys.lleve_casera_dv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button btn_singin, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Preferences.obtenerPreferencesBoolean(MainActivity.this, Preferences.PREFERENCES_ESTADO_BUTTON_SESION)){
            finish();
            startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
        }
        btn_singin = findViewById(R.id.btn_sign_in);
        btn_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SingInActivity.class));
                finish();
            }
        });

        btn_login= findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

    }



}
