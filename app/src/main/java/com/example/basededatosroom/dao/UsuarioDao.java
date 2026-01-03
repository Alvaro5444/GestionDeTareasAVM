package com.example.basededatosroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.basededatosroom.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    List<Usuario> obtenerUsuarios();

    @Query("SELECT * FROM Usuario WHERE idUsuario = :idusuario")
    Usuario obtenerUsuario(int idusuario);

    @Insert
    void insertarUsuario(Usuario...usuarios);

    @Query("Update Usuario SET nombre = :nombre, email = :email WHERE idUsuario = :idusuario")
    void actualizarUsuario(int idusuario, String nombre, String email);

    @Query("Delete FROM Usuario WHERE idUsuario = :idusuario")
    void eliminarUsuario(int idusuario);




}
