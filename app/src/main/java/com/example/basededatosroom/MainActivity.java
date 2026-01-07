package com.example.basededatosroom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.basededatosroom.DataBase.AppDataBase;
import com.example.basededatosroom.activity.ListarCategoriaActivity;
import com.example.basededatosroom.activity.ListarTareasActivity;
import com.example.basededatosroom.activity.ListarUsuarioActivity;
import com.example.basededatosroom.entities.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnuser, btncategoria, btntarea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configuramos el toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Gestión de Tareas");
        setSupportActionBar(toolbar);

        //Configuramos los botones para ir a las pantallas de listar usuario, listar categoria y listar tareas
        btnuser = findViewById(R.id.buttonuser);

        btnuser.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListarUsuarioActivity.class);
            startActivity(intent);
        });

        btncategoria = findViewById(R.id.buttoncategoria);

        btncategoria.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListarCategoriaActivity.class);
            startActivity(intent);
        });

        btntarea = findViewById(R.id.buttontarea);
        btntarea.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListarTareasActivity.class);
            startActivity(intent);
        });





    }

}