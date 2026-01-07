package com.example.basededatosroom.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.basededatosroom.dao.CategoriaDAO;
import com.example.basededatosroom.dao.TareaDAO;
import com.example.basededatosroom.dao.UsuarioDao;
import com.example.basededatosroom.entities.Categoria;
import com.example.basededatosroom.entities.Tarea;
import com.example.basededatosroom.entities.Usuario;

@Database(
        entities = {Usuario.class, Categoria.class, Tarea.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuarioDao usuariodao();
    public abstract CategoriaDAO categoriadao();
    public abstract TareaDAO tareadao();

    // Creamos la instancia de la base de datos
    private static volatile AppDataBase INSTANCE;

    /**
     * Metodo para obtener la instancia de la base de datos para que se cree solo una vez
     *
     * @param context
     * @return
     */
    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDataBase.class,
                            "BasededatosRoom"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
