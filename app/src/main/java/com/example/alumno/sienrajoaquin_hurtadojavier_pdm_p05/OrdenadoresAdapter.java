package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrdenadoresAdapter extends BaseAdapter{
    private Context context;

    public OrdenadoresAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ImagenOrdenador.ITEMS.length;
    }

    @Override
    public ImagenOrdenador getItem(int position) {
        return ImagenOrdenador.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagenOrdenador);
        TextView nombreCoche = (TextView) view.findViewById(R.id.nombreOrdenador);

        final ImagenOrdenador item = getItem(position);
        Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawable())
                .into(imagenCoche);

        nombreCoche.setText(item.getNombre());

        return view;
    }
}
