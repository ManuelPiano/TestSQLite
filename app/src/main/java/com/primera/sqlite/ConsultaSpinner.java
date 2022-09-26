package com.primera.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaSpinner extends AppCompatActivity {
    private Spinner sp_opt;
    private TextView tv_cod, tv_descripcion, tv_precio, tv_cat;
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        sp_opt = findViewById(R.id.sp_options);
        tv_cod = findViewById(R.id.tv_cod);
        tv_descripcion = findViewById(R.id.tv_descripcion);
        tv_precio = findViewById(R.id.tv_precio);
        tv_cat = findViewById(R.id.tv_categoria);

        conexion.consultaListaArticulo();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
        sp_opt.setAdapter(adaptador);

        sp_opt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position!=0){
                    tv_cod.setText("Codigo" + conexion.consultaListaArticulo().get(position-1).getCodigo());
                    tv_descripcion.setText("Descripcion:"+conexion.consultaListaArticulo().get(position-1).getDescripcion());
                    tv_precio.setText("precio:"+String.valueOf(conexion.consultaListaArticulo().get(position-1).getPrecio()));
                    tv_cat.setText("idCategoria:"+String.valueOf(conexion.consultaListaArticulo().get(position-1).getIdcategoria()));
                }else{
                    tv_cod.setText("Código:");
                    tv_descripcion.setText("Descripcion");
                    tv_precio.setText("Precio:");
                    tv_cat.setText("Categoria:");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}