package com.example.buscaminas;

import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.BreakIterator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Datos {

    //public static int [][] tablero;
    public static int tam_x = 10,tam_y = 10;
    public static int num_minas = 10;
    public static boolean primeraVez;
    public static Celda[][] tableroCeldas;
    public static List<Celda> minas;
    public static List<Celda> banderas;
    public static TextView marcador;
    public static boolean jugando;
    public static int quedan;


    public static void genera(Celda celda){
        Random rnd = new Random();

        for ( int mina = 0; mina < num_minas; mina++){
            int mina_x = rnd.nextInt(tam_x);
            int mina_y = rnd.nextInt(tam_y);
            while((mina_x == celda.x && mina_y == celda.y) || tableroCeldas[mina_x][mina_y].valor==-1){
                mina_x= rnd.nextInt(tam_x);
                mina_y= rnd.nextInt(tam_y);
            }
            minas.add(tableroCeldas[mina_x][mina_y]);
            tableroCeldas[mina_x][mina_y].valor=-1;
        }

        for(int i = 0;i<tam_x;i++){
            for (int j = 0;j<tam_y;j++){
                if(tableroCeldas[i][j].valor!=-1){
                    int num = 0;
                    for(int ii = i>0?i-1:i ; ii<=(i<tam_x-1?i+1:i); ii++){
                        for(int jj = j>0?j-1:j ; jj<=(j<tam_y-1?j+1:j); jj++){
                            if(tableroCeldas[ii][jj].valor == -1){
                                num++;
                            }
                        }
                    }
                    tableroCeldas[i][j].valor = num;
                    //Log.e("("+i+","+j+")", String.valueOf(num));
                }
            }
        }
    }

    public static void inicializar(int minas, int tam_x, int tam_y){
        Datos.num_minas = minas;
        Datos.tam_x = tam_x;
        Datos.tam_y = tam_y;
        //Datos.tablero = new int[tam_x][tam_y];
        tableroCeldas = new Celda[tam_x][tam_y];
        Datos.minas = new LinkedList<>();
        Datos.banderas = new LinkedList<>();
        Datos.primeraVez = true;
        Datos.jugando = true;
        quedan = tam_x*tam_y-minas;
    }

    public static void muestraMinas(Celda celda) {
        Datos.jugando=false;
        Iterator<Celda> iterador = minas.iterator();
        while (iterador.hasNext()){
            Celda mina = iterador.next();

            if (!mina.equals(celda)){
                Datos.banderas.remove(mina);
                mina.cambioTipo(Celda.Tipo.Mina);
            }
        }
        iterador = banderas.iterator();
        while(iterador.hasNext()){
            Celda bandera = iterador.next();
            bandera.cambioTipo(Celda.Tipo.MinaX);
        }
    }

    public static void ponBanderas(){
        Iterator<Celda> iterador = minas.iterator();
        while(iterador.hasNext()){
            Celda bandera = iterador.next();
            bandera.cambioTipo(Celda.Tipo.Bandera);
        }
    }

}
