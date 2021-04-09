package com.example.parcial1_productos;

import java.io.Serializable;

public class Productos implements Serializable {

    private String Nombre;
    private int Código;
    private double Valor;
    private String Exento;
    private String Descripcion;
    private String Categoria;

    public Productos(String nombre, int código, double valor, String exento, String descripcion, String categoria) {
        Nombre = nombre;
        Código = código;
        Valor = valor;
        Exento = exento;
        Descripcion = descripcion;
        Categoria = categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCódigo() {
        return Código;
    }

    public void setCódigo(int código) {
        Código = código;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public String getExento() {
        return Exento;
    }

    public void setExento(String exento) {
        Exento = exento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

}
