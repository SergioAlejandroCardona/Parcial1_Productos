package com.example.parcial1_productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

import java.util.ArrayList;
import java.util.List;

public class Consultas extends AppCompatActivity implements View.OnClickListener {

    private Spinner spTiposConsultas;
    private Button btConsultar;
    private TextView tvResultadoConsulta;
    private Button btAtrasConsultas;

    private Empresa empresa;
    private List<Productos> productosResConsulta = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        Intent intent = getIntent();
        empresa = (Empresa) intent.getSerializableExtra("empresa");

        spTiposConsultas = findViewById(R.id.spTiposConsultas);
        btConsultar = findViewById(R.id.btnConsultar);
        tvResultadoConsulta = findViewById(R.id.tvResultadoConsulta);
        btAtrasConsultas = findViewById(R.id.btnAtrasConsultas);

        tvResultadoConsulta.setMovementMethod(new ScrollingMovementMethod());

        btConsultar.setOnClickListener(this);
        btAtrasConsultas.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.options_array,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spTiposConsultas.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    if (v.getId() == R.id.btnConsultar)
    {
        int IdSeleccion = (int)spTiposConsultas.getSelectedItemId();

        switch (IdSeleccion)
        {
            case 0:
                CustomToastView.makeInfoToast(this, "Seleccione un tipo consulta", R.layout.custom_toast).show();
                break;

            case 1:
                String ResultadoLista = "Los Productos exentos de IVA son: \n";
                productosResConsulta = empresa.ExentosIVA();
                for (int i = 0; i <= productosResConsulta.size()-1; i++){
                    ResultadoLista = ResultadoLista + "\n" + "--> Nombre: "+ productosResConsulta.get(i).getNombre()
                            + "\nValor: " + productosResConsulta.get(i).getValor()
                            + "\nExento: " + productosResConsulta.get(i).getExento() + "\n";
                }
                tvResultadoConsulta.setText(ResultadoLista);

                break;

            case 2:
                ResultadoLista = "Los Productos SIN exención de IVA son: \n";
                productosResConsulta = empresa.SinExencionIVA();
                for (int i = 0; i <= productosResConsulta.size()-1; i++){
                    ResultadoLista = ResultadoLista + "\n" + "--> Nombre: "+ productosResConsulta.get(i).getNombre()
                            + "\nValor: " + productosResConsulta.get(i).getValor()
                            + "\nExento: " + productosResConsulta.get(i).getExento() + "\n";
                }
                tvResultadoConsulta.setText(ResultadoLista);

                break;

            case 3:
                ResultadoLista = "Los 10 Productos mas costosos son: \n";
                productosResConsulta = empresa.DiezMasCostosos();
                for (int i = 0; i <= productosResConsulta.size()-1; i++){
                    ResultadoLista = ResultadoLista + "\n" + "--> Nombre: "+ productosResConsulta.get(i).getNombre()
                            + "\nCódigo: " + productosResConsulta.get(i).getCódigo()
                            + "\nValor: " + productosResConsulta.get(i).getValor() + "\n";
                }
                tvResultadoConsulta.setText(ResultadoLista);

                break;

            case 4:
                ResultadoLista = "Los 10 Productos mas baratos son: \n";
                productosResConsulta = empresa.DiezDiezMenosCostoso();
                for (int i = 0; i <= productosResConsulta.size()-1; i++){
                    ResultadoLista = ResultadoLista + "\n" + "--> Nombre: "+ productosResConsulta.get(i).getNombre()
                            + "\nCódigo: " + productosResConsulta.get(i).getCódigo()
                            + "\nValor: " + productosResConsulta.get(i).getValor() + "\n";
                }
                tvResultadoConsulta.setText(ResultadoLista);

                break;

            case 5:
                ResultadoLista = "El promedio del valor de todos los productos es: \n";

                double promedio = empresa.PromedioValorProductos();

                tvResultadoConsulta.setText(ResultadoLista + "\n" + promedio);

                break;
        }
    }


        if (v.getId() == R.id.btnAtrasConsultas)
        {
            Intent myIntentPrincipal = new Intent(this, MainActivity.class);
            startActivity(myIntentPrincipal);
        }
    }
}