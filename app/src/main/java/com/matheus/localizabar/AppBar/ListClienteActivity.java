package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.matheus.localizabar.Adapter.BasicAdapter;
import com.matheus.localizabar.Dao.ClienteDao;
import com.matheus.localizabar.Model.Cliente;
import com.matheus.localizabar.R;

import java.util.ArrayList;
import java.util.List;

public class ListClienteActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView listaClientes;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);

        listaClientes = (ListView) findViewById(R.id.listClientes);
        clientes = new ArrayList<Cliente>();
        clientes = ClienteDao.getClienteDao(this).selecionaTodos();
        listaClientes.setAdapter(new BasicAdapter(this, clientes));
        listaClientes.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),
                "Cliente Cadastrado",
                Toast.LENGTH_LONG).show();
    }
}