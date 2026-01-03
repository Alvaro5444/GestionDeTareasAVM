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

    public interface OnTareaClickListener {
        void onEliminarClick(int position);
    }

    public TareaAdapter(List<Tarea> listaTareas, OnTareaClickListener listener) {
        this.listaTareas = listaTareas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardtarea, parent, false);
        return new TareaViewHolder(view, listener);
    }

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

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

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
