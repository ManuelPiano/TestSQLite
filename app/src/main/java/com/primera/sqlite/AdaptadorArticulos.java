package com.primera.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ArticulosViewHolder>{
    private Context mCtx;
    private List<Dto> articulosList;
    //Añadimos un contructor a partir de las propiedades definidas en esta clase (AdaptorArticulos).
    public AdaptadorArticulos(Context mCtx, List<Dto> articulosList) { this.mCtx = mCtx;
        this.articulosList = articulosList;
    }
    @Override
    public ArticulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_recyclerview, null); return new ArticulosViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ArticulosViewHolder holder, int position) { //Toast.makeText(mCtx, "Se encontraron: "+getItemCount()+ " Registros.",Toast.LENGTH_SHORT).show();
        Dto dto = articulosList.get(position);
        holder.textViewCodigo1.setText(String.valueOf(dto.getCodigo()));
        holder.textViewDescripcion1.setText(dto.getDescripcion());
        holder.textViewPrecio1.setText(String.valueOf(dto.getPrecio()));
        holder.textViewOtro.setText(String.valueOf("Registro #:" + (position+1)) + "/" +
                getItemCount());
        holder.textViewCat.setText(String.valueOf(dto.getIdcategoria()));

    }
    @Override
    public int getItemCount() { return articulosList.size();
    }
    public static class ArticulosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodigo1, textViewDescripcion1, textViewPrecio1, textViewOtro, textViewCat;
        public ArticulosViewHolder(View itemView) {
            super(itemView);
            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1= itemView.findViewById(R.id.textViewPrecio1);
            textViewOtro = itemView.findViewById(R.id.textViewOtro);
            textViewCat = itemView.findViewById(R.id.textViewCategoria1);

        }
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView); }
}
