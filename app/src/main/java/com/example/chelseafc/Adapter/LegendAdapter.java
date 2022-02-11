package com.example.chelseafc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chelseafc.Model.Legend;
import com.example.chelseafc.R;

import java.util.List;

public class LegendAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Legend> legendList;

    public LegendAdapter(Context context, int layout, List<Legend> legendList) {
        this.context = context;
        this.layout = layout;
        this.legendList = legendList;
    }

    @Override
    public int getCount() {
        return legendList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgLegend;
        TextView txtNameLegend, txtNumber, txtSeason, txtDOB, txtNational, txtNumberMatch;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();

            holder.imgLegend = (ImageView) view.findViewById(R.id.imgLegend);
            holder.txtNameLegend = (TextView) view.findViewById(R.id.txtNameLegend);
            holder.txtDOB = (TextView) view.findViewById(R.id.txtDOB);
            holder.txtNational = (TextView) view.findViewById(R.id.txtNational);
            holder.txtNumber = (TextView) view.findViewById(R.id.txtNumber);
            holder.txtSeason = (TextView) view.findViewById(R.id.txtSeason);
            holder.txtNumberMatch = (TextView) view.findViewById(R.id.txtNumberMatch);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Legend legend = legendList.get(i);
        holder.txtNumberMatch.setText(String.valueOf(legend.getNumberMatch()));
        holder.txtNumber.setText(String.valueOf(legend.getNumber()));
        holder.txtSeason.setText(legend.getSeason());
        holder.txtNational.setText(legend.getNational());
        holder.txtNameLegend.setText(legend.getNameLegend());
        holder.txtDOB.setText(legend.getDOB());
        holder.imgLegend.setImageResource(legend.getImgLegend());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.startAnimation(animation);

        return view;
    }
}
