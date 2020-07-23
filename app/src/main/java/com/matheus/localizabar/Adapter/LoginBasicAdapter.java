package com.matheus.localizabar.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheus.localizabar.Dao.LoginDao;
import com.matheus.localizabar.Model.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginBasicAdapter extends BaseAdapter {

    private Context context;
    //List para armazenar objetos Cliente
    private List<Login> logins = new ArrayList<>();

    private LoginDao loginDao;

    // Construtor da classe recebendo p contexot e um list carregando
    public LoginBasicAdapter(Context context, List<Login> logins) {
        super();
        this.context = context;
        this.logins = logins;

    }

    //Método subscrito da classe BaseAdapter
    //Retorna a quantidade de elementos no array
    @Override
    public int getCount() {
        return logins.size();

    }

    //Método subscrito da classe BaseAdapter
    //Retorna um objeto da posição informada no parametro position
    @Override
    public Object getItem(int position) {
        return logins.get(position);
    }

    @Override
    public long getItemId(int position) {
       return logins.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtLogin =  new TextView(context);
        float dip = 60;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * densidade + 0.6f);
        txtLogin.setHeight(px);

        txtLogin.setText(logins.get(position).getUsuario());
        return txtLogin;
    }
}
