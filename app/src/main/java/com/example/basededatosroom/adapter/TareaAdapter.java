package com.example.basededatosroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatosroom.R;
import com.example.basededatosroom.entities.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<Tarea> listaTareas;
    private OnTareaClickListener listener;

    /**
     * Interfaz para el click en el botón de eliminar
     */
    public interface OnTareaClickListener {
        void onEliminarClick(int position);
    }

    public TareaAdapter(List<Tarea> listaTareas, OnTareaClickListener listener) {
        this.listaTareas = listaTareas;
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
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardtarea, parent, false);
        return new TareaViewHolder(view, listener);
    }

    /**
     * Metodo para actualizar el view holder
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);

        holder.tvId.setText("ID: " + tarea.getIdTarea());
        holder.tvTitulo.setText(tarea.getTitulo());
        holder.tvDescripcion.setText(tarea.getDescripcion());
        holder.tvFecha.setText("Fecha: " + tarea.getFecha());
        holder.tvCompletada.setText("Completada: " + (tarea.isCompletada() ? "Sí" : "No"));
        holder.tvIdCategoria.setText("Cat ID: " + tarea.getIdCategoria());
        holder.tvIdUsuario.setText("User ID: " + tarea.getIdUsuario());
    }

    /**
     * Metodo para obtener el tamaño de la lista
     *
     * @return devolvemos el tamanio de la lista
     */
    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    /**
     * Clase para configurar el view holder
     */
    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitulo, tvDescripcion, tvFecha, tvCompletada, tvIdCategoria, tvIdUsuario;
        ImageButton btnEliminar;

        public TareaViewHolder(@NonNull View itemView, OnTareaClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdTarea);
            tvTitulo = itemView.findViewById(R.id.tvTituloTarea);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionTarea);
            tvFecha = itemView.findViewById(R.id.tvFechaTarea);
            tvCompletada = itemView.findViewById(R.id.tvCompletadaTarea);
            tvIdCategoria = itemView.findViewById(R.id.tvIdCategoriaTarea);
            tvIdUsuario = itemView.findViewById(R.id.tvIdUsuarioTarea);
            btnEliminar = itemView.findViewById(R.id.btnEliminarTarea);

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
