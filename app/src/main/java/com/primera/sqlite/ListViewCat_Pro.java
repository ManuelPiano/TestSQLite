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

public class ListViewCat_Pro extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayAdapter adaptador;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    String[] version = {"Aestro", "Blender", "Cupckake", "Donut", "Eclair", "Froyo", "GingerBread", "HoneyComb",
            "IceCream", "JellyBean", "KitKat", "LoliPop", "Marshmellow", "Nought", "Oreo", "Pie", "10","11","12","12L","13"};

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_cat_pro);

        listViewPersonas=(ListView) findViewById(R.id.ViewPer);
        searchView=(SearchView)findViewById(R.id.BuscarVista);

        adaptador=new ArrayAdapter(this, android.R.layout.simple_list_item_1, conexion.consultaListaArticulos1IJ());
        listViewPersonas.setAdapter(adaptador);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text=s;
                adaptador.getFilter().filter(text);
                return false;
            }
        });

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int post, long l) {
                String informacion="Codigo:" +conexion.consultaListaArticulosIJ().get(post).getDescripcion()+"\n";
                informacion+="Descripcion:" +conexion.consultaListaArticulosIJ().get(post).getDescripcion()+"\n";
                informacion+="Precio:"+conexion.consultaListaArticulosIJ().get(post).getPrecio();
                informacion+="Categoria:"+conexion.consultaListaArticulosIJ().get(post).getIdcategoria();

                Dto articulos = conexion.consultaListaArticulosIJ().get(post);
                Intent intent = new Intent(ListViewCat_Pro.this, detalles_articulos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("tb_productos", articulos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}