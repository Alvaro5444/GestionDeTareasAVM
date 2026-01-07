package com.example.basededatosroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.basededatosroom.DataBase.AppDataBase;
import com.example.basededatosroom.R;
import com.example.basededatosroom.adapter.UsuarioAdapter;
import com.example.basededatosroom.entities.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListarUsuarioActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvusuario;
    FloatingActionButton fabAgregarUsuario;
    AppDataBase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarusuario);

        // Configuramos el toolbar
        toolbar = findViewById(R.id.toolbarlistarusuario);
        toolbar.setTitle("Listado de Usuarios");
        setSupportActionBar(toolbar);

        //Configuramos la flecha para atras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        //Configuramos el recycler view para que se vean los usuarios de la base de datos
        rvusuario = findViewById(R.id.rvuser);
        rvusuario.setLayoutManager(new LinearLayoutManager(this));

        //Configuramos el boton de agregar usuario
        fabAgregarUsuario = findViewById(R.id.fabAgregarUsuario);
        fabAgregarUsuario.setOnClickListener(v ->{
            Intent intent = new Intent(this, AgregarUsuario.class);
            startActivity(intent);
        });

        //Configuramos la base de datos para mostrar los usuarios
        appDatabase = AppDataBase.getInstance(this);

        obtenerUsuarios();
    }

    /**
     * Actualizamos la lista de usuarios
     */
    @Override
    protected void onResume() {
        super.onResume();
        obtenerUsuarios();
    }

    /**
     * Metodo para obtener los usuarios de la base de datos y mostrarlos en el recycler view
     */
    private void obtenerUsuarios() {
        List<Usuario> usuarios = appDatabase.usuariodao().obtenerUsuarios();

        UsuarioAdapter adapter = new UsuarioAdapter(usuarios, position -> {
            Usuario usuarioAEliminar = usuarios.get(position);
            appDatabase.usuariodao().eliminarUsuario(usuarioAEliminar.getIdUsuario());
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
            obtenerUsuarios();
        });

        rvusuario.setAdapter(adapter);
    }
}
