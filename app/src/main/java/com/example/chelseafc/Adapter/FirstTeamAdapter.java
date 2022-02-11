package com.example.chelseafc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chelseafc.Model.FirstTeam;
import com.example.chelseafc.R;

import java.util.List;

public class FirstTeamAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<FirstTeam> firstTeamList;

    public FirstTeamAdapter(Context context, int layout, List<FirstTeam> firstTeamList) {
        this.context = context;
        this.layout = layout;
        this.firstTeamList = firstTeamList;
    }

    @Override
    public int getCount() {
        return firstTeamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgPlayer;
        TextView txtNamePlayer, txtNumber, txtDOB, txtNational, txtPosition;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();

            holder.imgPlayer = (ImageView) view.findViewById(R.id.imgPlayer);
            holder.txtDOB = (TextView) view.findViewById(R.id.txtDOB);
            holder.txtNamePlayer = (TextView) view.findViewById(R.id.txtNamePlayer);
            holder.txtNational = (TextView) view.findViewById(R.id.txtNational);
            holder.txtNumber = (TextView) view.findViewById(R.id.txtNumber);
            holder.txtPosition = (TextView) view.findViewById(R.id.txtPosition);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        FirstTeam firstTeam = firstTeamList.get(i);
        holder.txtNamePlayer.setText(firstTeam.getNamePlayer());
        holder.txtNumber.setText(String.valueOf(firstTeam.getNumber()));
        holder.txtNational.setText(firstTeam.getNationalPlayer());
        holder.txtDOB.setText(firstTeam.getDOB());
        holder.txtPosition.setText(firstTeam.getPosition());
        holder.imgPlayer.setImageResource(firstTeam.getImgPlayer());

        return view;
    }
}
