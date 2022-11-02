package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);

        List<Dificultad> miLista = new LinkedList<>();
        miLista.add(new Dificultad("fácil",10,8,8));
        miLista.add(new Dificultad("medio",20,10,10));
        miLista.add(new Dificultad("dificil",50,15,15));
        miLista.add(new Dificultad("personalizado"));

        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv.setAdapter(new MiAdaptador(miLista));
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rv);
    }
}