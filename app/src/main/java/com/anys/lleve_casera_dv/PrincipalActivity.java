package com.anys.lleve_casera_dv;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorMercados;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class PrincipalActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration ;
    TextView textView_nombreApellido, textView_correo;
    String nomUser,ApeUser, emaiuser, nomApe;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView title = findViewById(R.id.toolbar_title);
        title.setText("Productos");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

         /*
         Aquí colocamos los id de los fragment a los que queremos que muestre el nav del menú
         */
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.perfilFragment,R.id.productosFragment,
                R.id.mercadoFragment,R.id.agenciaFragment,R.id.carritoFragment,R.id.productoMercadoFragment,
                R.id.elementos_mercadoFragment,R.id.confirmar_compraFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setItemIconTintList(null); //Para que no se vea el fondo gris de los iconos del menú al ejecutar la app
    }

   //Menu opciones lado derecho en el nav_Bar
  @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal1, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_principal);

        nomUser = Preferences.obtenerPreferencesString(this, Preferences.PREFERENCES_nombreUsuario);
        ApeUser= Preferences.obtenerPreferencesString(this, Preferences.PREFERENCES_apellidoUsuario);
        emaiuser = Preferences.obtenerPreferencesString(this, Preferences.PREFERENCES_correoUsuario);

        nomApe= nomUser +" "+ ApeUser;
        // para llenar los datos del menu
        textView_nombreApellido= findViewById(R.id.textView_nombreApellido);
        textView_correo=findViewById(R.id.textView_correo);
        textView_nombreApellido.setText(nomApe);
        textView_correo.setText(emaiuser);


        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void cerrarSesion(){
        Preferences.savePreferencesBoolean(PrincipalActivity.this, false, Preferences.PREFERENCES_ESTADO_BUTTON_SESION);
        preferences = getSharedPreferences(Preferences.STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().clear().apply();
        Intent login = new Intent(this, LoginActivity.class);
        login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(login);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id== R.id.action_cerrar_sesion){
            cerrarSesion();
        }
        return super.onOptionsItemSelected(item);
    }
}