package com.example.corresponsal.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corresponsal.R;
import com.example.corresponsal.entidades.Clientes;
import com.example.corresponsal.entidades.HistorialTransacciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorHistorial extends RecyclerView.Adapter<AdaptadorHistorial.HistorialViewHolder> {
    ArrayList<HistorialTransacciones>historialTransacciones;
    ArrayList<HistorialTransacciones>historialTransaccionesOriginal;
    Context context;



    public AdaptadorHistorial(Context context,  ArrayList<HistorialTransacciones> historialTransacciones) {
        this.historialTransacciones = historialTransacciones;
        this.context = context;
        historialTransaccionesOriginal = new ArrayList<>();
        historialTransaccionesOriginal.addAll(historialTransacciones);
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_historial, null, false);
        return new HistorialViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        int position01 = position;

        holder.idItem.setText(String.valueOf(historialTransacciones.get(position01).getIdHistorial()));
        holder.fehaTtem.setText(historialTransacciones.get(position01).getHoraTransaccion());
        holder.tipotransaccionItem.setText(historialTransacciones.get(position01).getTipoTransaccion());
        holder.montotransaccionItem.setText(historialTransacciones.get(position01).getMontoTransaccion());
        holder.numeroccItem.setText(historialTransacciones.get(position01).getCedulaHistorial());
    }

    public void filtrado (final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0){
            historialTransacciones.clear();
            historialTransacciones.addAll(historialTransaccionesOriginal);

        }else  {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                  List<HistorialTransacciones> collecion = historialTransacciones.stream().filter(i -> i.getCedulaHistorial().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());

                 historialTransacciones.clear();
                 historialTransacciones.addAll(collecion);
            }else {
                for (HistorialTransacciones c: historialTransaccionesOriginal){
                    if (c.getCedulaHistorial().toLowerCase().contains(txtBuscar));
                    historialTransacciones.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return historialTransacciones.size();
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder {


        TextView idItem;
        TextView fehaTtem;
        TextView tipotransaccionItem;
        TextView montotransaccionItem;
        TextView numeroccItem;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);

            idItem = itemView.findViewById(R.id.idHistroialItem);
            fehaTtem = itemView.findViewById(R.id.fechaHistorialitem);
            tipotransaccionItem = itemView.findViewById(R.id.tipoTransaccionHisitem);
            montotransaccionItem = itemView.findViewById(R.id.montoTransaccionHisitem);
            numeroccItem = itemView.findViewById(R.id.cedulaOcuentaHistorial);


        }
    }
}
