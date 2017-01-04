package com.example.rouge.anem.Entreprise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rouge.anem.Entity.Entreprise;
import com.example.rouge.anem.R;

import java.util.List;

/**
 * Created by r0b on 04/01/17.
 */

public class EntrepriseAdapter extends BaseAdapter {
    private List<Entreprise> listEntreprise;
    private LayoutInflater layoutInflater;

    public EntrepriseAdapter(Context context, List<Entreprise> vListEntreprise) {
        layoutInflater = LayoutInflater.from(context);
        listEntreprise = vListEntreprise;
    }
    @Override
    public int getCount() {
        return listEntreprise.size();
    }

    @Override
    public Object getItem(int position) {
        return listEntreprise.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.liste_entreprise, null);
            holder.textViewNom = (TextView) convertView.findViewById(R.id.vueNom);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNom.setText(listEntreprise.get(position).getNom());
        return convertView;
    }

    private class ViewHolder {
        TextView textViewNom;
    }
}
