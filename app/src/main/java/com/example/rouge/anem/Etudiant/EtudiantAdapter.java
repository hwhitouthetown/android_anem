package com.example.rouge.anem.Etudiant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rouge.anem.Entity.Utilisateur;
import com.example.rouge.anem.R;

import java.util.List;

/**
 * Created by rouge on 04/01/2017.
 */

public class EtudiantAdapter extends BaseAdapter {
    private List<Utilisateur> listEtudiant;
    private LayoutInflater layoutInflater;

    public EtudiantAdapter(Context context, List<Utilisateur> vListEtudiant) {
        layoutInflater = LayoutInflater.from(context);
        listEtudiant = vListEtudiant;
    }
    @Override
    public int getCount() {
        return listEtudiant.size();
    }

    @Override
    public Object getItem(int position) {
        return listEtudiant.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EtudiantAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new EtudiantAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.liste_etudiant, null);
            holder.textViewNom = (TextView) convertView.findViewById(R.id.vueNom);
            convertView.setTag(holder);
        } else {
            holder = (EtudiantAdapter.ViewHolder) convertView.getTag();
        }

        holder.textViewNom.setText(listEtudiant.get(position).getNom());
        return convertView;
    }

    private class ViewHolder {
        TextView textViewNom;
    }
}
