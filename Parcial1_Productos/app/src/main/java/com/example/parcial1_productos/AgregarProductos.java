package com.example.parcial1_productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ib.custom.toast.CustomToastView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgregarProductos extends AppCompatActivity implements View.OnClickListener {

    private EditText etNombre;
    private EditText etCodigo;
    private EditText etValor;
    private Spinner spExento;
    private EditText etdescripcion;
    private Spinner spCategoria;
    private Button btGuardar;
    private Button btAtrasProductos;

    static List<Productos> productosList = new ArrayList<>();

    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);

        etNombre = findViewById(R.id.etNombre);
        etCodigo = findViewById(R.id.etCodigo);
        etValor = findViewById(R.id.etValor);
        spExento = findViewById(R.id.spExento);
        etdescripcion = findViewById(R.id.etDescripcion);
        spCategoria = findViewById(R.id.spCategoria);
        btGuardar = findViewById(R.id.btnGuardar);
        btAtrasProductos = findViewById(R.id.btnAtrasProductos);

        btGuardar.setOnClickListener(this);
        btAtrasProductos.setOnClickListener(this);

        Intent intent = getIntent();
        empresa = (Empresa) intent.getSerializableExtra("empresa");

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,empresa.getCategoriasList());
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spCategoria.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.options_iva,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spExento.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGuardar)
        {

            String nombre = etNombre.getText().toString();
            String codigo = etCodigo.getText().toString();
            String valor = etValor.getText().toString();
            String descripcion = etdescripcion.getText().toString();

            if(nombre.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar el Nombre", R.layout.custom_toast).show();
                return;
            }
            if(codigo.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar el Código", R.layout.custom_toast).show();
                return;
            }
            if(valor.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar el Valor", R.layout.custom_toast).show();
                return;
            }
            if(spExento.getSelectedItemId() == 0){
                CustomToastView.makeInfoToast(this, "Seleccione si el producto esta o no exento de IVA", R.layout.custom_toast).show();
                return;
            }
            if(descripcion.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar La Descripción", R.layout.custom_toast).show();
                return;
            }
            if(spCategoria.getSelectedItemId() == 0){
                CustomToastView.makeInfoToast(this, "Seleccione una categoria", R.layout.custom_toast).show();
                return;
            }

            AgregarProducto();
            LimpiarCampos();
            CustomToastView.makeSuccessToast(this, "Producto agregado correctamente",R.layout.custom_toast).show();
        }

        if (v.getId() == R.id.btnAtrasProductos)
        {
            Intent myIntentPrincipal = new Intent(this, MainActivity.class);
            myIntentPrincipal.putExtra("lista_productos", (Serializable) productosList);
            startActivity(myIntentPrincipal);
        }
    }

    private void AgregarProducto() {

        Productos producto = new Productos(etNombre.getText().toString(),Integer.parseInt(etCodigo.getText().toString()),
                Double.parseDouble(etValor.getText().toString()),spExento.getSelectedItem().toString(),etdescripcion.getText().toString(),
                spCategoria.getSelectedItem().toString());

        productosList.add(producto);
    }

    private void LimpiarCampos(){

        etNombre.setText("");
        etCodigo.setText("");
        etValor.setText("");
        spExento.setSelection(0);
        etdescripcion.setText("");
        spCategoria.setSelection(0);
        etNombre.requestFocus();
    }

}