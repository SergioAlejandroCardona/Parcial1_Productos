package com.example.parcial1_productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btNuevoProducto;
    private Button btConsultas;

    private List<Productos> productosList = new ArrayList<>();
    private List<String> categoriasList = new ArrayList<>();

    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriasList.add("Seleccione una opción");
        categoriasList.add("Lacteos");
        categoriasList.add("Granos");
        categoriasList.add("Legumbres");
        categoriasList.add("Bebidas");
        categoriasList.add("Jabones");
        categoriasList.add("Cuidado Personal");
        categoriasList.add("Carnes");

        Intent intent = getIntent();
        productosList = (List<Productos>) intent.getSerializableExtra("lista_productos");

        empresa = new Empresa("EASYMARKET", "123456789", productosList, categoriasList);

        btNuevoProducto = findViewById(R.id.btnNuevoProducto);
        btConsultas = findViewById(R.id.btnConsultas);

        btNuevoProducto.setOnClickListener(this);
        btConsultas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNuevoProducto)
        {
            Intent myIntentProductos = new Intent(this, AgregarProductos.class);
            myIntentProductos.putExtra("empresa", (Serializable) empresa);
            startActivity(myIntentProductos);
        }
        if (v.getId() == R.id.btnConsultas)
        {
            Intent myIntentConsultas = new Intent(this, Consultas.class);
            myIntentConsultas.putExtra("empresa", (Serializable) empresa);
            startActivity(myIntentConsultas);
        }
    }
}