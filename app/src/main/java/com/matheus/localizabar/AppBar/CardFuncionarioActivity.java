package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matheus.localizabar.Dao.FuncionarioDao;
import com.matheus.localizabar.R;

public class CardFuncionarioActivity extends AppCompatActivity {

    Intent fun, funlist, delfun, funup;
    private FuncionarioDao funcionarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_funcionario);
    }

    public void funcionario(View view) {
        fun = new Intent(getApplicationContext(), FuncionarioActivity.class);
        startActivity(fun);
    }

    public void listarfuncionario(View view) {
        funlist = new Intent(getApplicationContext(), ListFuncionarioActivity.class);
        startActivity(funlist);
    }

    public void atualizarfuncionario(View view) {
        funup = new Intent(getApplicationContext(), FuncionarioActivity.class);
        startActivity(funup);
    }


    public void apagarfuncionario(View view) {

        Bundle msg = new Bundle();
        msg.putString("apagar", "confirme");
        delfun = new Intent(getApplicationContext(), ListDelFuncionarioActivity.class);
        delfun.putExtras(msg);
        startActivity(delfun);
    }
}