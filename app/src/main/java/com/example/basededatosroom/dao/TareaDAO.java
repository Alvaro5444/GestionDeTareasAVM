package com.example.basededatosroom.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.basededatosroom.entities.Tarea;

import java.util.List;

@Dao
public interface TareaDAO {

    @Query("SELECT * FROM Tarea")
    List<Tarea> obtenerTareas();

    @Query("SELECT * FROM Tarea WHERE idTarea = :idtarea")
    Tarea obtenerTarea(int idtarea);

    @Insert
    void insertarTarea(Tarea...tareas);

    @Query("UPDATE Tarea SET titulo = :titulo, descripcion = :descripcion, fecha = :fecha, completada = :completada, idCategoria = :idcategoria, idUsuario = :idUsuario WHERE idTarea = :idtarea")
    void actualizarTarea(int idtarea, String titulo, String descripcion, String fecha, boolean completada, int idcategoria, int idUsuario);

    @Query("Delete FROM Tarea WHERE idTarea = :idtarea")
    void eliminarTarea(int idtarea);

}
