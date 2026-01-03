package com.example.basededatosroom.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basededatosroom.DataBase.AppDataBase;
import com.example.basededatosroom.R;
import com.example.basededatosroom.entities.Categoria;
import com.example.basededatosroom.entities.Tarea;
import com.example.basededatosroom.entities.Usuario;

import java.util.Calendar;
import java.util.List;

public class AgregarTarea extends AppCompatActivity {

    Toolbar toolbar;
    EditText etNombre, etDescripcionTarea, etFecha;
    CheckBox checkBox;
    Spinner spinnerUsuario, spinnerCategoria;
    Button btnGuardarTarea;
    AppDataBase appDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_tarea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbaragregartarea);
        toolbar.setTitle("Agregar Tarea");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        etNombre = findViewById(R.id.etNombreTarea);
        etDescripcionTarea = findViewById(R.id.etDescripcionTarea);
        etFecha = findViewById(R.id.etFecha);
        checkBox = findViewById(R.id.compleatacheck);
        spinnerUsuario = findViewById(R.id.spinnerusuario);
        spinnerCategoria = findViewById(R.id.spinnercategoria);
        btnGuardarTarea = findViewById(R.id.btnGuardarTarea);

        etFecha.setFocusable(false);
        etFecha.setClickable(true);
        etFecha.setOnClickListener(v -> mostrarDatePicker());

        appDataBase = AppDataBase.getInstance(this);

        obtenerUsuarios();

        obtenerCategoria();


        btnGuardarTarea.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String descripcion = etDescripcionTarea.getText().toString();
            String fecha = etFecha.getText().toString();
            boolean completada = checkBox.isChecked();

            Usuario usuarioSeleccionado = (Usuario) spinnerUsuario.getSelectedItem();
            Categoria categoriaSeleccionada = (Categoria) spinnerCategoria.getSelectedItem();

            if (!nombre.isEmpty() && !descripcion.isEmpty() && !fecha.isEmpty() && usuarioSeleccionado != null && categoriaSeleccionada != null) {
                Tarea tarea = new Tarea(0, nombre, descripcion, fecha, completada, categoriaSeleccionada.getIdCategoria(), usuarioSeleccionado.getIdUsuario());
                
                appDataBase.tareadao().insertarTarea(tarea);
                
                Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerUsuarios(){
        List<Usuario> listaUsuarios = appDataBase.usuariodao().obtenerUsuarios();
        ArrayAdapter<Usuario> adapterUsuario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaUsuarios);
        adapterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adapterUsuario);
    }

    public void obtenerCategoria(){
        List<Categoria> listaCategorias = appDataBase.categoriadao().obtenerCategorias();
        ArrayAdapter<Categoria> adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCategorias);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String fecha =
                            String.format("%02d/%02d/%04d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear);

                    etFecha.setText(fecha);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
}
