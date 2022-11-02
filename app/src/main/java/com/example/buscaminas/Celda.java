package com.example.buscaminas;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Celda extends androidx.appcompat.widget.AppCompatImageButton {
    public int x,y;
    public int valor;
    private Tipo tipoActual;
    public List<Celda> vecinos;
    //public List<Celda> ;

    public Celda(Context context) {
        super(context);
        tipoActual = Tipo.MinaRoja;
        cambioTipo(Tipo.Boton);
        this.setScaleType(ScaleType.FIT_XY);
        this.setAdjustViewBounds(false);
        this.setPadding(0,0,0,0);
        vecinos = new LinkedList<>();
        modificaMarcador();
    }

    public Celda putPosicion(int x, int y) {
        this.x= x;
        this.y=y;
        return this;
    }

    public void cambioTipo(Tipo tipoNuevo){
        if(tipoActual == tipoNuevo) return;
        tipoActual=tipoNuevo;
        switch (tipoActual){
            case N0:
                this.setImageResource(R.mipmap.n0);
                break;
            case N1:
                this.setImageResource(R.mipmap.n1);
                break;
            case N2:
                this.setImageResource(R.mipmap.n2);
                break;
            case N3:
                this.setImageResource(R.mipmap.n3);
                break;
            case N4:
                this.setImageResource(R.mipmap.n4);
                break;
            case N5:
                this.setImageResource(R.mipmap.n5);
                break;
            case N6:
                this.setImageResource(R.mipmap.n6);
                break;
            case N7:
                this.setImageResource(R.mipmap.n7);
                break;
            case N8:
                this.setImageResource(R.mipmap.n8);
                break;
            case Boton:
                this.setImageResource(R.mipmap.btn);
                break;
            case Mina:
                this.setImageResource(R.mipmap.mina1);
                break;
            case MinaRoja:
                this.setImageResource(R.mipmap.mina2);
                break;
            case MinaX:
                this.setImageResource(R.mipmap.mina3);
                break;
            case Bandera:
                this.setImageResource(R.mipmap.bandera);
                break;
            default:
                Log.e("Clase celda","Hay un error en la función cambiaTipo");
        }
    }

    public void pulsar(){
        if(tipoActual==Tipo.Boton){
            switch (Datos.tableroCeldas[x][y].valor){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    Datos.quedan--;
                    if (Datos.quedan == 0) {
                        Datos.jugando = false;
                        Datos.marcador.setText(R.string.ganado);
                        Datos.ponBanderas();

                    }
            }
            Tipo nuevo = null;

            switch ( Datos.tableroCeldas[x][y].valor){
                case -1:
                    nuevo = Tipo.MinaRoja;
                    Datos.muestraMinas(this);
                    Datos.marcador.setText(getResources().getString(R.string.perdido));
                    break;
                case 0:nuevo = Tipo.N0;break;
                case 1:nuevo = Tipo.N1; break;
                case 2:nuevo = Tipo.N2; break;
                case 3:nuevo = Tipo.N3; break;
                case 4:nuevo = Tipo.N4; break;
                case 5:nuevo = Tipo.N5; break;
                case 6:nuevo = Tipo.N6; break;
                case 7:nuevo = Tipo.N7; break;
                case 8:nuevo = Tipo.N8; break;
            }
            cambioTipo(nuevo);
            if(tipoActual == Tipo.N0){
                Iterator<Celda> iterador = vecinos.iterator();
                while(iterador.hasNext()){
                    Celda aux = iterador.next();
                    aux.pulsar();
                }
            }else{
                int numBanderas = 0;
                Iterator<Celda> vecino = vecinos.iterator();
                while(vecino.hasNext()){
                    Celda celdaVecina = vecino.next();
                    if(celdaVecina.tipoActual == Tipo.Bandera){
                        numBanderas++;
                    }
                }
                switch(Datos.tableroCeldas[x][y].valor){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        if(Datos.tableroCeldas[x][y].valor == numBanderas){
                            vecino = vecinos.iterator();
                            while(vecino.hasNext()){
                                Celda celdaVecina = vecino.next();
                                if(celdaVecina.tipoActual == Tipo.Boton){
                                    celdaVecina.pulsar();
                                }
                            }
                        }
                }
            }
        }
    }

    public void pulsarLargo() {
        switch (tipoActual){
            case Boton:
                if (Datos.banderas.size()<Datos.num_minas) {
                    cambioTipo(Tipo.Bandera);
                    Datos.banderas.add(this);
                    break;
                }

            case Bandera:
                cambioTipo(Tipo.Boton);
                Datos.banderas.remove(this);
                break;

        }
        modificaMarcador();
    }

    private void modificaMarcador() {
        String texto = getResources().getString(R.string.quedan);
        texto += Datos.num_minas - Datos.banderas.size();
        texto += getResources().getString(R.string.minas);
        Datos.marcador.setText(texto);
    }

    enum Tipo{
        N0,N1,N2,N3,N4,N5,N6,N7,N8,Boton,Mina,MinaRoja,MinaX,Bandera
    }


}
