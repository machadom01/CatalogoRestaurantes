package com.example.reto4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComidaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComidaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComidaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComidaFragment newInstance(String param1, String param2) {
        ComidaFragment fragment = new ComidaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comida, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDeBusqueda = getActivity().findViewById(R.id.searchEditText);
        botonDeBusqueda = getActivity().findViewById(R.id.imagebutton_search);
        editTextDeBusqueda = getActivity().findViewById(R.id.searchEditText);
        botonClear = getActivity().findViewById(R.id.clearButton);
        layoutBusqueda = getActivity().findViewById(R.id.searchLayout);
//
        todosLosItems = new ArrayList<>();
        todosLosItems.add(MiArrayAdapter.COMIDAS[0]);
        todosLosItems.add(MiArrayAdapter.COMIDAS[1]);
        todosLosItems.add(MiArrayAdapter.COMIDAS[2]);
        todosLosItems.add(MiArrayAdapter.COMIDAS[3]);

        itemsFiltrados = new ArrayList<>(todosLosItems);
//
        listView = view.findViewById(R.id.listview_comidas);

        adapter = new MiArrayAdapter(getContext(),
                itemsFiltrados, MiArrayAdapter.PRECIOS);
        listView.setAdapter(adapter);

        establecerFuncionalidadDeBusqueda();






        context=requireContext();
        nombreEstaClase = this.getClass().getSimpleName();
        nombreEstaClase2 = this.getClass().getName();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                esto te dirige a la descripcion correspondiente de la comida seleccionada
                Intent intent = new Intent(context, ItemDetalles_Activity.class);
                intent.putExtra("Position", position);
                intent.putExtra("ClaseOrigen", nombreEstaClase);
                intent.putExtra("ClaseAnterior", nombreEstaClase2);
                startActivity(intent);
            }
        });

    }
    private void establecerFuncionalidadDeBusqueda() {
        botonDeBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarModoBusqueda();
            }
        });
        botonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarModoBusqueda();
            }
        });
        editTextDeBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarLista(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void filtrarLista(String textoDeBusqueda) {
        itemsFiltrados.clear();

        if (textoDeBusqueda.isEmpty()){
            itemsFiltrados.addAll(todosLosItems);
        } else {
            for (String restaurante : todosLosItems) {
                if (restaurante.toLowerCase().contains(textoDeBusqueda.toLowerCase())){
                    itemsFiltrados.add(restaurante);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void ocultarModoBusqueda() {
        editTextDeBusqueda.setText("");
        layoutBusqueda.setVisibility(View.GONE);

        filtrarLista("");
    }

    private void mostrarModoBusqueda() {
        layoutBusqueda.setVisibility(View.VISIBLE);
        editTextDeBusqueda.requestFocus();
    }

    Context context;
    String nombreEstaClase;
    String nombreEstaClase2;

    private EditText editTextDeBusqueda;
    private ImageButton botonDeBusqueda, botonClear;
    private LinearLayout layoutBusqueda;
    private ArrayList<String> todosLosItems, itemsFiltrados;
    private ListView listView;
    private ArrayAdapter<String> adapter;
}