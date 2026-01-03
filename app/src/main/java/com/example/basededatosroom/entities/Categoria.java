package com.example.basededatosroom.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categoria")
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    private int idCategoria;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public Categoria(String descripcion, String nombre, int idCategoria) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
