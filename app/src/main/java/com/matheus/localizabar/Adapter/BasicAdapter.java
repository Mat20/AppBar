package com.matheus.localizabar.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheus.localizabar.Dao.ClienteDao;
import com.matheus.localizabar.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BasicAdapter extends BaseAdapter {

    private Context context;
    //List para armazenar objetos Cliente
    private List<Cliente> clientes = new ArrayList<>();

    private ClienteDao clienteDao;

    // Construtor da classe recebendo p contexot e um list carregando
    public BasicAdapter(Context context, List<Cliente> clientes) {
        super();
        this.context = context;
        this.clientes = clientes;

    }

    //Método subscrito da classe BaseAdapter
    //Retorna a quantidade de elementos no array
    @Override
    public int getCount() {
        return clientes.size();

    }

    //Método subscrito da classe BaseAdapter
    //Retorna um objeto da posição informada no parametro position
    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return clientes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtCliente =  new TextView(context);
        float dip = 60;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * densidade + 0.6f);
        txtCliente.setHeight(px);

        txtCliente.setText(clientes.get(position).getNome());
        return txtCliente;
    }
}
