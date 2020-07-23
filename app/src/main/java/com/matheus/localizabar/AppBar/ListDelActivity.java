package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

public class ListDelActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView listaClientes;
    private BasicAdapter listAdpter;
    private List<Cliente> clientes;
    private long id;
    private int listId;
    private Intent it;
    private Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_del);

        clientes = new ArrayList<Cliente>();
        clientes = ClienteDao.getClienteDao(this).selecionaTodos();
        listAdpter = new BasicAdapter(this, clientes);
        it = getIntent();
        b = it.getExtras();

        listaClientes = (ListView) findViewById(R.id.listDelClientes);
        listaClientes.setAdapter(listAdpter);
        listaClientes.setOnItemClickListener(this);
    }

    //Método invocado pelo android quando um item da lista é clicado
    // i - posição do array
    // l - id do elemento na base de dados
    @Override
    public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {

        // Variável com id do registro na base de dados
        id = l;
        // Variável com id do objeto na listview
        listId = i;

        if ( b.getString("apagar").equals("confirme") ) {

            //Gera uma caixa de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define icone da caixa
            builder.setIcon(R.mipmap.usuario);
            //Define título da caixa
            builder.setTitle("Confirme");
            //mensagem a ser exibida na caixa
            builder.setMessage("Deseja Realemente Deletar?");
            //trata o evento de clique no botão sim
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // criando uma instância de ClienteDao e invocando o método delete
                    ClienteDao.getClienteDao(getApplicationContext()).delete((int)id);
                    // Criando um Toast (mensagem rápida) de confirmação
                    Toast.makeText(getApplicationContext(),
                            "Cliente Deletado Com Sucesso!!",
                            Toast.LENGTH_LONG).show();
                    // Removendo o cliente deletado da base de dados tb no array
                    clientes.remove(listId);
                    // notificando adapter que ouve mudança nos dados a serem exibidos
                    listAdpter.notifyDataSetChanged();

                    return;

                }
            });
            //Trata evento de clique no botão não
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}