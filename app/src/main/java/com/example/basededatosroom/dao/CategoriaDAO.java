package com.example.basededatosroom.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.basededatosroom.entities.Categoria;

import java.util.List;

@Dao
public interface CategoriaDAO {

    @Query("SELECT * FROM Categoria")
    List<Categoria> obtenerCategorias();

    @Query("SELECT * FROM Categoria WHERE idCategoria = :idcategoria")
    Categoria obtenerCategoria(int idcategoria);

    @Insert
    void insertarCategoria(Categoria...categorias);

    @Query("UPDATE Categoria SET nombre = :nombre, descripcion = :descripcion WHERE idCategoria = :idcategoria")
    void actualizarCategoria(int idcategoria, String nombre, String descripcion);

    @Query("Delete FROM Categoria WHERE idCategoria = :idcategoria")
    void eliminarCategoria(int idcategoria);



}
