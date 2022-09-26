package com.primera.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class ListViewArticulos extends AppCompatActivity {
    ListView listviewpersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listview;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] version = {"Aestro", "Blender", "Cupckake", "Donut", "Eclair", "Froyo", "GingerBread", "HoneyComb",
            "IceCream", "JellyBean", "KitKat", "LoliPop", "Marshmellow", "Nought", "Oreo", "Pie", "10","11","12","12L","13"};

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);

        listviewpersonas = findViewById(R.id.listViewPersonas);
        searchView = findViewById(R.id.searchView);
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, conexion.ConsultaListaArticulos1());
        listviewpersonas.setAdapter(adaptador);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                adaptador.getFilter().filter(text);
                return false;
            }
        });
        listviewpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                String informacion= "Codigo: "+ conexion.consultaListaArticulo().get(pos).getDescripcion()+"\n";
                informacion+= "Descripción:"+conexion.consultaListaArticulo().get(pos).getDescripcion()+"\n";
                informacion+="Precio:"+conexion.consultaListaArticulo().get(pos).getPrecio();

                Dto articulos = conexion.consultaListaArticulo().get(pos);
                Intent intent = new Intent(ListViewArticulos.this, detalles_articulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("articulo", articulos);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}