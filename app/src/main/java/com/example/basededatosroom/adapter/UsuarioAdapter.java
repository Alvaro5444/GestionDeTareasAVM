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

    public interface OnUsuarioClickListener {
        void onEliminarClick(int position);
    }

    public UsuarioAdapter(List<Usuario> listaUsuarios, OnUsuarioClickListener listener) {
        this.listaUsuarios = listaUsuarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carduser, parent, false);
        return new UsuarioViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.tvId.setText("ID: " + usuario.getIdUsuario());
        holder.tvNombre.setText(usuario.getNombre());
        holder.tvEmail.setText(usuario.getEmail());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNombre, tvEmail;
        ImageButton btnEliminar;

        public UsuarioViewHolder(@NonNull View itemView, OnUsuarioClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdUsuario);
            tvNombre = itemView.findViewById(R.id.tvnombreuser);
            tvEmail = itemView.findViewById(R.id.tvcorreouser);
            btnEliminar = itemView.findViewById(R.id.btnEliminarUsuario);

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
