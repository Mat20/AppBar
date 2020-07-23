package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.matheus.localizabar.Adapter.FuncionarioBasicAdapter;
import com.matheus.localizabar.Dao.FuncionarioDao;
import com.matheus.localizabar.Model.Funcionario;
import com.matheus.localizabar.R;

import java.util.ArrayList;
import java.util.List;

public class ListFuncionarioActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView listaFuncionarios;
    private List<Funcionario> funcionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_funcionario);

        listaFuncionarios = (ListView) findViewById(R.id.listFuncionarios);
        funcionarios = new ArrayList<Funcionario>();
        funcionarios = FuncionarioDao.getFuncionarioDao(this).selecionaTodos();
        listaFuncionarios.setAdapter(new FuncionarioBasicAdapter(this, funcionarios));
        listaFuncionarios.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),
                "Cliente Cadastrado",
                Toast.LENGTH_LONG).show();
    }
}