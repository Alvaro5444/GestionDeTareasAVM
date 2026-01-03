package com.example.basededatosroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatosroom.R;
import com.example.basededatosroom.entities.Categoria;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> listaCategorias;
    private OnCategoriaClickListener listener;

    public interface OnCategoriaClickListener {
        void onEliminarClick(int position);
    }

    public CategoriaAdapter(List<Categoria> listaCategorias, OnCategoriaClickListener listener) {
        this.listaCategorias = listaCategorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardcategoria, parent, false);
        return new CategoriaViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = listaCategorias.get(position);
        holder.tvId.setText("ID: " + categoria.getIdCategoria());
        holder.tvNombre.setText(categoria.getNombre());
        holder.tvDescripcion.setText(categoria.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvDescripcion;
        ImageButton btnEliminar;

        public CategoriaViewHolder(@NonNull View itemView, OnCategoriaClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdCategoria);
            tvNombre = itemView.findViewById(R.id.tvNombreCategoria);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionCategoria);
            btnEliminar = itemView.findViewById(R.id.btnEliminarCategoria);

            btnEliminar.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEliminarClick(position);
                    }
                }
            });
        }
    }
}
