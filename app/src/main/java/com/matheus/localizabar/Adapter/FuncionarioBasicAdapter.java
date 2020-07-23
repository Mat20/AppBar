package com.matheus.localizabar.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheus.localizabar.Dao.FuncionarioDao;
import com.matheus.localizabar.Model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioBasicAdapter extends BaseAdapter {

    private Context context;
    //List para armazenar objetos Cliente
    private List<Funcionario> funcionarios = new ArrayList<>();

    private FuncionarioDao funcionarioDao;

    // Construtor da classe recebendo p contexot e um list carregando
    public FuncionarioBasicAdapter(Context context, List<Funcionario> funcionarios) {
        super();
        this.context = context;
        this.funcionarios = funcionarios;

    }

    //Método subscrito da classe BaseAdapter
    //Retorna a quantidade de elementos no array
    @Override
    public int getCount() {
        return funcionarios.size();

    }

    //Método subscrito da classe BaseAdapter
    //Retorna um objeto da posição informada no parametro position
    @Override
    public Object getItem(int position) {
        return funcionarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return funcionarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtFuncionario =  new TextView(context);
        float dip = 50;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * densidade + 0.5f);
        txtFuncionario.setHeight(px);

        txtFuncionario.setText(funcionarios.get(position).getNome());
        //txtFuncionario.setText(funcionarios.get(position).getCpf());

        return txtFuncionario;
    }
}
