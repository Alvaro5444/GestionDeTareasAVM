package com.example.basededatosroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatosroom.R;
import com.example.basededatosroom.entities.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> listaUsuarios;
    private OnUsuarioClickListener listener;

    /**
     * Interfaz para el click en el botón de eliminar
     */
    public interface OnUsuarioClickListener {
        void onEliminarClick(int position);
    }

    public UsuarioAdapter(List<Usuario> listaUsuarios, OnUsuarioClickListener listener) {
        this.listaUsuarios = listaUsuarios;
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
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carduser, parent, false);
        return new UsuarioViewHolder(view, listener);
    }

    /**
     * Metodo para actualizar el view holder
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.tvId.setText("ID: " + usuario.getIdUsuario());
        holder.tvNombre.setText(usuario.getNombre());
        holder.tvEmail.setText(usuario.getEmail());
    }

    /**
     * Metodo para obtener el tamaño de la lista
     *
     * @return devolvemos el tamanio de la lista
     */
    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    /**
     * Clase para configurar el view holder
     */
    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvEmail;
        ImageButton btnEliminar;

        /**
         * Constructor para el view holder
         *
         * @param itemView
         * @param listener
         */
        public UsuarioViewHolder(@NonNull View itemView, OnUsuarioClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdUsuario);
            tvNombre = itemView.findViewById(R.id.tvnombreuser);
            tvEmail = itemView.findViewById(R.id.tvcorreouser);
            btnEliminar = itemView.findViewById(R.id.btnEliminarUsuario);

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
