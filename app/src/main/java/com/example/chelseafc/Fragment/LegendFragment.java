package com.example.chelseafc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.chelseafc.Adapter.LegendAdapter;
import com.example.chelseafc.Fragment.Legend.LegendCechFragment;
import com.example.chelseafc.Fragment.Legend.LegendDrogbaFragment;
import com.example.chelseafc.Fragment.Legend.LegendLampardFragment;
import com.example.chelseafc.Fragment.Legend.LegendTerryFragment;
import com.example.chelseafc.Fragment.Legend.LegendZolaFragment;
import com.example.chelseafc.Model.Legend;
import com.example.chelseafc.R;

import java.util.ArrayList;

public class LegendFragment extends Fragment {

    private View view;
    ListView listViewLegend;
    ArrayList<Legend> legendArrayList;
    LegendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_legend, container, false);

        init();
        setListViewLegend();

        return view;
    }

    public void setListViewLegend(){
        adapter = new LegendAdapter(getActivity(), R.layout.row_legend, legendArrayList);
        listViewLegend.setAdapter(adapter);
        listViewLegend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = i;
                if(id == 0){
                    replaceFragment(new LegendZolaFragment());
                }
                if(id == 1){
                    replaceFragment(new LegendTerryFragment());
                }
                if(id == 2){
                    replaceFragment(new LegendLampardFragment());
                }
                if(id == 3){
                    replaceFragment(new LegendDrogbaFragment());
                }
                if(id == 4){
                    replaceFragment(new LegendCechFragment());
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void init() {
        listViewLegend = (ListView) view.findViewById(R.id.listViewLegend);
        legendArrayList = new ArrayList<>();
        legendArrayList.add(new Legend("Gianfranco Zola","05/07/1966",25,R.drawable.legend_zola,"Italy",229,"1996-2003"));
        legendArrayList.add(new Legend("John Terry","07/12/1980",26,R.drawable.legend_terry,"England",717,"1998-2017"));
        legendArrayList.add(new Legend("Frank Lampard","20/06/1978",8,R.drawable.legend_lampard,"England",648,"2001-2014"));
        legendArrayList.add(new Legend("Didier Drogba","11/03/1978",11,R.drawable.legend_drogba,"Côte d’Ivoire",341,"2004-2012, 2014-2015"));
        legendArrayList.add(new Legend("Petr Čech","20/05/1982",1,R.drawable.legend_cech,"Czech Republic",494,"2004-2015"));
    }
}
