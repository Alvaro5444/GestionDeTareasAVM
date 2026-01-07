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

    /**
     * Interfaz para el click en el botón de eliminar
     */
    public interface OnCategoriaClickListener {
        void onEliminarClick(int position);
    }


    public CategoriaAdapter(List<Categoria> listaCategorias, OnCategoriaClickListener listener) {
        this.listaCategorias = listaCategorias;
        this.listener = listener;
    }

    /**
     * Metodo para crear el view holder
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardcategoria, parent, false);
        return new CategoriaViewHolder(view, listener);
    }

    /**
     * Metodo para actualizar el view holder
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = listaCategorias.get(position);
        holder.tvId.setText("ID: " + categoria.getIdCategoria());
        holder.tvNombre.setText(categoria.getNombre());
        holder.tvDescripcion.setText(categoria.getDescripcion());
    }

    /**
     * Metodo para obtener el tamaño de la lista
     *
     * @return devolvemos el tamanio de la lista
     */
    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    /**
     * Clase para configurar el view holder
     */
    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvDescripcion;
        ImageButton btnEliminar;

        /**
         * Constructor para el view holder
         *
         * @param itemView
         * @param listener
         */
        public CategoriaViewHolder(@NonNull View itemView, OnCategoriaClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdCategoria);
            tvNombre = itemView.findViewById(R.id.tvNombreCategoria);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionCategoria);
            btnEliminar = itemView.findViewById(R.id.btnEliminarCategoria);

            //Configuramos el click en el boton de eliminar
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
