package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matheus.localizabar.Dao.ClienteDao;
import com.matheus.localizabar.R;

public class CardClienteActivity extends AppCompatActivity {

    Intent cad, listar, up, del;
    private ClienteDao clienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_cliente);
    }


    public void cadastrar(View view) {
        cad = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(cad);
    }


    public void listarTodos(View view) {
        listar = new Intent(getApplicationContext(), ListClienteActivity.class);
        startActivity(listar);
    }

    public void atualizar(View view) {
        up = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(up);
    }

    public void apagar(View view) {
        Bundle msg = new Bundle();
        msg.putString("apagar", "confirme");
        del = new Intent(getApplicationContext(), ListDelActivity.class);
        del.putExtras(msg);
        startActivity(del);
    }
}