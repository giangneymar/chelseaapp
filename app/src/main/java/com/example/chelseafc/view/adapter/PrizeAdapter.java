package com.example.chelseafc.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chelseafc.model.Prize;
import com.example.chelseafc.R;

import java.util.List;

public class PrizeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Prize> prizeList;

    public PrizeAdapter(Context context, int layout, List<Prize> prizeList) {
        this.context = context;
        this.layout = layout;
        this.prizeList = prizeList;
    }

    @Override
    public int getCount() {
        return prizeList.size();
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
        ImageView imgPrize;
        TextView txtPrizeName, txtPrizeYear;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imgPrize = (ImageView) view.findViewById(R.id.imgPrize);
            viewHolder.txtPrizeName = (TextView) view.findViewById(R.id.txtNamePrize);
            viewHolder.txtPrizeYear = (TextView) view.findViewById(R.id.txtYearPrize);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Prize prize = prizeList.get(position);
        viewHolder.txtPrizeName.setText(prize.getNamePrize());
        viewHolder.txtPrizeYear.setText(prize.getYearPrize());
        viewHolder.imgPrize.setImageResource(prize.getImgPrize());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}
