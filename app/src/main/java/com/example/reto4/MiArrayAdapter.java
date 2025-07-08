package com.example.reto4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MiArrayAdapter extends ArrayAdapter<String> {

    public static final String[] COMIDAS={"Comida A","Comida B","Comida C","Comida D"};
    public static final String[] BEBIDAS={"Bebida A","Bebida B","Bebida C","Bebida D"};
    public static final String[] COMPLEMENTOS={"Complemento A","Complemento B","Complemento C","Complemento D"};
    public static final String[] PRECIOS={"$20.00", "$40.00", "$60.00", "$80.00"};


    private Context context;
    String[] losPrecios;
    ArrayList<String> losItems;
    public MiArrayAdapter(Context context, ArrayList<String> items, String[] precios) {
        super(context, 0, items);
        this.context=context;
        losPrecios=precios;
        losItems=items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem==null){
            listItem  = LayoutInflater.from(context).inflate
                    (R.layout.layout_textviews_dentrodelistview, parent, false);
        }
        TextView textView1 = listItem.findViewById(R.id.itemName);
        TextView textView2 = listItem.findViewById(R.id.itemPrice);

        textView1.setText(losItems.get(position));
        textView2.setText(losPrecios[position]);


        return listItem;

    }
}
