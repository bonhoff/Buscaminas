package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView texto = findViewById(R.id.Pruebas);
        //Celda miCelda = new Celda(this).putPosicion(5,6);
        //Datos.inicializar(4,8,8);
        //Datos.primeraVez=true;
        LinearLayout verticalLayout = (LinearLayout) findViewById(R.id.verticalLayout);
        Datos.marcador = (TextView) findViewById(R.id.marcador);


        for (int j = 0; j< Datos.tam_y;j++){
            LinearLayout layoutAuxiliar = new LinearLayout(this);
            layoutAuxiliar.setOrientation(LinearLayout.HORIZONTAL);
            layoutAuxiliar.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );

            for (int i = 0 ; i<Datos.tam_x; i++) {
                Celda celdaAuxiliar = new Celda(this).putPosicion(i, j);
                celdaAuxiliar.setLayoutParams(
                        new LinearLayout.LayoutParams(
                                200,
                                200
                        )
                );

                celdaAuxiliar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!Datos.jugando){return;}
                        Celda estaCelda = (Celda) view;
                        if (Datos.primeraVez) {
                            Datos.primeraVez = false;
                            Datos.genera(estaCelda);
                        }
                        estaCelda.pulsar();
                    }
                });

                celdaAuxiliar.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (Datos.jugando = true) {
                            Celda estaCelda = (Celda) view;
                            estaCelda.pulsarLargo();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                if (i > 0) {
                    celdaAuxiliar.vecinos.add(Datos.tableroCeldas[i - 1][j]);
                    Datos.tableroCeldas[i - 1][j].vecinos.add(celdaAuxiliar);
                }
                if (i > 0 && j > 0) {
                    celdaAuxiliar.vecinos.add(Datos.tableroCeldas[i - 1][j - 1]);
                    Datos.tableroCeldas[i - 1][j - 1].vecinos.add(celdaAuxiliar);
                }
                if (j > 0) {
                    celdaAuxiliar.vecinos.add(Datos.tableroCeldas[i][j - 1]);
                    Datos.tableroCeldas[i][j - 1].vecinos.add(celdaAuxiliar);
                }
                if (j > 0 && i < Datos.tam_y - 1){
                    celdaAuxiliar.vecinos.add(Datos.tableroCeldas[i + 1][j - 1]);
                    Datos.tableroCeldas[i + 1][j - 1].vecinos.add(celdaAuxiliar);
                }
                Datos.tableroCeldas[i][j]=celdaAuxiliar;

                layoutAuxiliar.addView(celdaAuxiliar);
            }

            verticalLayout.addView(layoutAuxiliar);

        }

    }
}