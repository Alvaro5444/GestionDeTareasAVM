package com.example.basededatosroom.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basededatosroom.DataBase.AppDataBase;
import com.example.basededatosroom.R;
import com.example.basededatosroom.entities.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class AgregarUsuario extends AppCompatActivity {

    Toolbar toolbar;

    EditText etNombre, etEmail;

    Button btnguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Configuramos el toolbar
        toolbar = findViewById(R.id.toolbaragregarusuario);
        toolbar.setTitle("Agregar Usuario");
        setSupportActionBar(toolbar);

        //Configuramos la flecha para atras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);

        //Configuramos el boton de guardar y controlamos los campos que esten rellenos
        btnguardar = findViewById(R.id.btnGuardarUsuario);
        btnguardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String email = etEmail.getText().toString();
        if (!nombre.isEmpty() && !email.isEmpty()){
            AppDataBase appDataBase = AppDataBase.getInstance(this);
            appDataBase.usuariodao().insertarUsuario(new Usuario(0, nombre, email));
            finish();
        }else {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
        });

    }
}
