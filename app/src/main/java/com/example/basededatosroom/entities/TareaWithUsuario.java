package com.example.basededatosroom.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TareaWithUsuario {

    @Embedded
    public Tarea tarea;

    @Relation(
            parentColumn = "idUsuario",
            entityColumn = "idUsuario"
    )
    public List<Usuario> usuarios;
}
