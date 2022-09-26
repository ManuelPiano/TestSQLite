package com.primera.sqlite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modal {
    Dialog myDialog;
    AlertDialog.Builder dialogo;
    boolean validainput = false;
    String codigo, descripcion, precio;
    SQLiteDatabase bd = null;
    Dto datos=new Dto();
    public void Search(final Context context){
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.ventana1);
        myDialog.setTitle("Search");
        myDialog.setCancelable(false);
        final ConexionSQLite conexion = new ConexionSQLite(context);
        final EditText et_cod = myDialog.findViewById(R.id.et_cod);
        Button btn_buscar = myDialog.findViewById(R.id.btn_buscar);
        TextView tv_close = myDialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_cod.getText().toString().length()==0){
                    validainput=false;
                    et_cod.setError("Campo Obligatorio");
                }else{
                    validainput=true;

                }
                if (validainput){
                    String cod = et_cod.getText().toString();
                    datos.setCodigo(Integer.parseInt(cod));
                    if (conexion.consultaCodigo(datos)){
                        codigo=String.valueOf(datos.getCodigo());
                        descripcion=datos.getDescripcion();
                        precio=String.valueOf(datos.getPrecio());
                        String action;
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.putExtra("senal","1");
                        intent.putExtra("codigo",codigo);
                        intent.putExtra("descripcion",descripcion);
                        intent.putExtra("precio",precio);
                        context.startActivity(intent);

                        myDialog.dismiss();

                    }else{
                        Toast.makeText(context,"No se han encontrado resultados para la busqueda especificada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
