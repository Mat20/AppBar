package com.matheus.localizabar.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheus.localizabar.Dao.MenuDao;
import com.matheus.localizabar.Model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBasicAdapter extends BaseAdapter {

    private Context context;
    //List para armazenar objetos Cliente
    private List<Menu> menus = new ArrayList<>();

    private MenuDao menuDao;

    // Construtor da classe recebendo p contexot e um list carregando
    public MenuBasicAdapter(Context context, List<Menu> menus) {
        super();
        this.context = context;
        this.menus = menus;

    }

    //Método subscrito da classe BaseAdapter
    //Retorna a quantidade de elementos no array
    @Override
    public int getCount() {
        return menus.size();

    }

    //Método subscrito da classe BaseAdapter
    //Retorna um objeto da posição informada no parametro position
    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menus.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtMenu =  new TextView(context);
        float dip = 50;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * densidade + 0.5f);
        txtMenu.setHeight(px);

        txtMenu.setText(menus.get(position).getProduto());
        return txtMenu;
    }
}
