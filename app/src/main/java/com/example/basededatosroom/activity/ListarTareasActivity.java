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
import com.example.basededatosroom.adapter.TareaAdapter;
import com.example.basededatosroom.entities.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListarTareasActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rvtarea;
    FloatingActionButton fabAgregarTarea;
    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tareas);

        // Configuramos el toolbar
        toolbar = findViewById(R.id.toolbarlistartarea);
        toolbar.setTitle("Listado de Tareas");
        setSupportActionBar(toolbar);

        //Configuramos la flecha para atras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        //Configuramos el recycler view para que se vean las tareas de la base de datos
        rvtarea = findViewById(R.id.rvtarea);
        rvtarea.setLayoutManager(new LinearLayoutManager(this));

        //Configuramos el boton de agregar tarea
        fabAgregarTarea = findViewById(R.id.fabAgregarTarea);
        fabAgregarTarea.setOnClickListener(v ->{
            Intent intent = new Intent(this, AgregarTarea.class);
            startActivity(intent);
        });

        //Configuramos la base de datos para mostrar las tareas
        appDataBase = AppDataBase.getInstance(this);

        obtenerTareas();
    }

    /**
     * Actualizamos la lista de tareas
     */
    @Override
    protected void onResume() {
        super.onResume();
        obtenerTareas();
    }

    /**
     * Metodo para obtener las tareas de la base de datos y mostrarlas en el recycler view
     */
    private void obtenerTareas() {
        List<Tarea> tareas = appDataBase.tareadao().obtenerTareas();

        TareaAdapter adapter = new TareaAdapter(tareas, position -> {
            Tarea tareaAEliminar = tareas.get(position);
            appDataBase.tareadao().eliminarTarea(tareaAEliminar.getIdTarea());
            Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
            obtenerTareas();
        });

        rvtarea.setAdapter(adapter);
    }
}
