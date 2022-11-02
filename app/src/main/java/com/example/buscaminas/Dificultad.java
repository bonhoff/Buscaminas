package com.example.buscaminas;

public class Dificultad {
    public int tam_x,tam_y,num_minas;
    public boolean custom;
    public String nombre;


    public Dificultad(String nombre, int num_minas, int tam_x, int tam_y) {
        custom=false;
        this.nombre=nombre;
        this.num_minas = num_minas;
        this.tam_x = tam_x;
        this.tam_y=tam_y;

    }

    public Dificultad(String nombre) {
        custom=true;
        this.nombre=nombre;

    }
}
