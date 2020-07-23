package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.matheus.localizabar.R;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;

    Intent cardcliente, cardfuncionario, cardmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }


    public void btncardcliente(View view) {
        cardcliente = new Intent(getApplicationContext(), CardClienteActivity.class);
        startActivity(cardcliente);
    }

    public void btncardfuncionario(View view) {
        cardfuncionario = new Intent(getApplicationContext(), CardFuncionarioActivity.class);
        startActivity(cardfuncionario);
    }

    public void btncardmenu(View view) {
        cardmenu = new Intent(getApplicationContext(), CardMenuActivity.class);
        startActivity(cardmenu);
    }

}