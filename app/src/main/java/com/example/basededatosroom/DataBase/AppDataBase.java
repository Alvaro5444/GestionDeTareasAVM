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

    private static volatile AppDataBase INSTANCE;

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
