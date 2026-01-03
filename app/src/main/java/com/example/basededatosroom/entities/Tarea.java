package com.example.basededatosroom.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Tarea")
public class Tarea {

    @PrimaryKey(autoGenerate = true)
    private int idTarea;
    @ColumnInfo(name = "titulo")
    private String titulo;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "completada")
    private boolean completada;
    @ColumnInfo(name = "idCategoria")
    private int idCategoria;
    @ColumnInfo(name = "idUsuario")
    private int idUsuario;

    public Tarea(int idTarea, String titulo, String descripcion, String fecha, boolean completada, int idCategoria, int idUsuario) {
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completada = completada;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
