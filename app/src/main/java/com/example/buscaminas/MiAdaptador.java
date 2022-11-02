package com.example.buscaminas;

import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {
    List<Dificultad> dificultades;
    public MiAdaptador(List<Dificultad> dificultades){
        this.dificultades = dificultades;
    }
    @NonNull
    @Override
    public MiAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selector_dificultad,parent,false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiViewHolder holder, int position) {
       LinearLayout miLayout = holder.getLayout();
       Dificultad miDificultad = dificultades.get(position);
       Datos.inicializar(miDificultad.num_minas,miDificultad.tam_x,miDificultad.tam_y);
       if(miDificultad.custom){
           EditText numMinasET = new EditText(holder.itemView.getContext());
           numMinasET.setHint("Minas");
           numMinasET.setInputType(InputType.TYPE_CLASS_NUMBER);

           EditText numFilasET = new EditText(holder.itemView.getContext());
           numFilasET.setHint("Filas");
           numFilasET.setInputType(InputType.TYPE_CLASS_NUMBER);

           EditText numColumnasET = new EditText(holder.itemView.getContext());
           numColumnasET.setHint("Columnas");
           numColumnasET.setInputType(InputType.TYPE_CLASS_NUMBER);

           Button nuevoBoton = new Button(holder.itemView.getContext());
           nuevoBoton.setLayoutParams(
                   new ViewGroup.LayoutParams(
                           ViewGroup.LayoutParams.MATCH_PARENT,
                           ViewGroup.LayoutParams.MATCH_PARENT
                   )
           );
           nuevoBoton.setText(miDificultad.nombre);
           nuevoBoton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Datos.inicializar(Integer.parseInt(String.valueOf(numMinasET.getText())),
                   Integer.parseInt(String.valueOf(numFilasET.getText())),
                   Integer.parseInt(String.valueOf(numColumnasET.getText())));

                   holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), MainActivity.class));


               }
           });

           miLayout.addView(numMinasET);
           miLayout.addView(numFilasET);
           miLayout.addView(numColumnasET);
           miLayout.addView(nuevoBoton);
       }else{
           Button nuevoBoton = new Button(holder.itemView.getContext());
           nuevoBoton.setLayoutParams(
                   new ViewGroup.LayoutParams(
                           ViewGroup.LayoutParams.MATCH_PARENT,
                           ViewGroup.LayoutParams.MATCH_PARENT
                   )
           );
           nuevoBoton.setText(miDificultad.nombre);
           nuevoBoton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                Datos.inicializar(miDificultad.num_minas, miDificultad.tam_x, miDificultad.tam_y);
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),MainActivity.class));
               }
           });
           miLayout.addView(nuevoBoton);
       }
    }

    @Override
    public int getItemCount() {
        return dificultades.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.layoutDificultad);
        }

        public LinearLayout getLayout() {
            return layout;
        }
    }
}
