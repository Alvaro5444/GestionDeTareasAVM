package com.example.basededatosroom.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TareaWithCategoria {

    @Embedded
    public Tarea tarea;

    @Relation(
            parentColumn = "idCategoria",
            entityColumn = "idCategoria"
    )
    public List<Categoria> categorias;
}
