package com.example.reto4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyArrayAdapter extends ArrayAdapter<String> {

    public static final String[] COMIDAS={"Comida A","Comida B","Comida C","Comida D"};
    public static final String[] BEBIDAS={"Bebida A","Bebida B","Bebida C","Bebida D"};
    public static final String[] COMPLEMENTOS={"Complemento A","Complemento B","Complemento C","Complemento D"};
    public static final int[] PRECIOS={20, 40, 60, 80};


    private Context context;
    public MyArrayAdapter(Context context, String[] objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            listItem= layoutInflater.inflate(R.layout.layout_textviews_dentrodelistview, parent, false);
        }

        return listItem;

    }
}
