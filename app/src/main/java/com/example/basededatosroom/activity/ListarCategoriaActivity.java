package com.example.basededatosroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatosroom.DataBase.AppDataBase;
import com.example.basededatosroom.R;
import com.example.basededatosroom.adapter.CategoriaAdapter;
import com.example.basededatosroom.entities.Categoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListarCategoriaActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rvcategoria;
    FloatingActionButton fabAgregarCategoria;
    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categoria);

        toolbar = findViewById(R.id.toolbarlistarcategoria);
        toolbar.setTitle("Listado Categoria");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        rvcategoria = findViewById(R.id.rvcategoria);
        rvcategoria.setLayoutManager(new LinearLayoutManager(this));

        fabAgregarCategoria = findViewById(R.id.fabAgregarCategoria);
        fabAgregarCategoria.setOnClickListener(v ->{
            Intent intent = new Intent(this, AgregarCategoria.class);
            startActivity(intent);
        });

        appDataBase = AppDataBase.getInstance(this);
        obtenerCategoria();
    }

    @Override
    public void onResume() {
        super.onResume();
        obtenerCategoria();
    }

    public void obtenerCategoria(){
        List<Categoria> categorias = appDataBase.categoriadao().obtenerCategorias();

        CategoriaAdapter adapter = new CategoriaAdapter(categorias, id -> {
            Categoria categoriaAEliminar = categorias.get(id);

            appDataBase.categoriadao().eliminarCategoria(categoriaAEliminar.getIdCategoria());
            
            Toast.makeText(ListarCategoriaActivity.this, "Categoría eliminada", Toast.LENGTH_SHORT).show();

            obtenerCategoria();
        });

        rvcategoria.setAdapter(adapter);
    }
}
