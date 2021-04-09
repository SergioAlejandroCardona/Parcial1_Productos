package com.example.parcial1_productos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Empresa implements Serializable {

    private String Nombre;
    private String Nit;
    List<Productos> productosList = new ArrayList<>();
    List<String> categoriasList = new ArrayList<>();

    public Empresa(String nombre, String nit, List<Productos> productosList, List<String> categoriasList) {
        Nombre = nombre;
        Nit = nit;
        this.productosList = productosList;
        this.categoriasList = categoriasList;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public List<String> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<String> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public List<Productos> ExentosIVA(){

        List<Productos> listProductos = new ArrayList<>();

        for (Productos emp: productosList)
        {
            if ("Producto EXENTO de iva".equalsIgnoreCase(emp.getExento()))
            {
                listProductos.add(emp);
            }
        }

        return listProductos;
    }

    public List<Productos> SinExencionIVA(){

        List<Productos> listProductos = new ArrayList<>();

        for (Productos emp: productosList)
        {
            if ("Producto SIN exencion de iva".equalsIgnoreCase(emp.getExento()))
            {
                listProductos.add(emp);
            }
        }

        return listProductos;
    }

    public List<Productos> DiezMasCostosos(){

        List<Productos> listProductos = new ArrayList<>();
        int cont = 0;

        Collections.sort(productosList, new Comparator<Productos>(){
            @Override
            public int compare(Productos p1, Productos p2) {
                return new Double(p2.getValor()).compareTo(new Double(p1.getValor()));
            }
        });

        for (Productos emp: productosList)
        {
            if (cont <= 9)
            {
                listProductos.add(emp);
                cont++;
            }
        }

        return listProductos;
    }

    public List<Productos> DiezDiezMenosCostoso(){

        List<Productos> listProductos = new ArrayList<>();
        int cont = 0;

        Collections.sort(productosList, new Comparator<Productos>(){
            @Override
            public int compare(Productos p1, Productos p2) {
                return new Double(p1.getValor()).compareTo(new Double(p2.getValor()));
            }
        });

        for (Productos emp: productosList)
        {
            if (cont <= 9)
            {
                listProductos.add(emp);
                cont++;
            }
        }

        return listProductos;
    }

    public double PromedioValorProductos(){

        int cont = 0;
        double totalValores = 0;

        for (Productos emp: productosList)
        {
            totalValores = totalValores + emp.getValor();
            cont++;
        }

        double promedio = totalValores / cont;

        return promedio;
    }


}
