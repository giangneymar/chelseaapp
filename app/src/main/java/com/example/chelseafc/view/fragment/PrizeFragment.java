package com.example.chelseafc.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chelseafc.view.adapter.PrizeAdapter;
import com.example.chelseafc.model.Prize;
import com.example.chelseafc.R;

import java.util.ArrayList;

public class PrizeFragment extends Fragment {

    private View view;
    ListView listViewPrize;
    ArrayList<Prize> prizeArrayList;
    PrizeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_prize, container, false);

        init();
        setListViewPrize();

        return view;
    }

    private void setListViewPrize(){
        adapter = new PrizeAdapter(getActivity(), R.layout.row_prize, prizeArrayList);
        listViewPrize.setAdapter(adapter);
    }

    private void init(){
        listViewPrize = (ListView) view.findViewById(R.id.listViewPrize);
        prizeArrayList = new ArrayList<>();
        prizeArrayList.add(new Prize("Premier League", "1954-1955",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Fa Community Shield","1955",R.drawable.prize_communityshield));
        prizeArrayList.add(new Prize("Carabao Cup","1964-1965",R.drawable.prize_carabaocup));
        prizeArrayList.add(new Prize("Fa Cup", "1969-1970",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("UEFA Cup Winners' Cup","1970-1971",R.drawable.prize_cupwinner));
        prizeArrayList.add(new Prize("Championship", "1983-1984",R.drawable.prize_championship));
        prizeArrayList.add(new Prize("Championship", "1988-1989",R.drawable.prize_championship));
        prizeArrayList.add(new Prize("Fa Cup", "1996-1997",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("Carabao Cup","1997-1998",R.drawable.prize_carabaocup));
        prizeArrayList.add(new Prize("UEFA Cup Winners' Cup","1997-1998",R.drawable.prize_cupwinner));
        prizeArrayList.add(new Prize("UEFA Super Cup","1998",R.drawable.prize_supercup));
        prizeArrayList.add(new Prize("Fa Cup", "1999-2000",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("Fa Community Shield","2000",R.drawable.prize_communityshield));
        prizeArrayList.add(new Prize("Premier League", "2004-2005",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Carabao Cup","2004-2005",R.drawable.prize_carabaocup));
        prizeArrayList.add(new Prize("Fa Community Shield","2005",R.drawable.prize_communityshield));
        prizeArrayList.add(new Prize("Premier League", "2005-2006",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Fa Cup", "2006-2007",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("Carabao Cup","2006-2007",R.drawable.prize_carabaocup));
        prizeArrayList.add(new Prize("Fa Cup", "2008-2009",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("Fa Community Shield","2009",R.drawable.prize_communityshield));
        prizeArrayList.add(new Prize("Premier League", "2009-2010",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Fa Cup", "2009-2010",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("Fa Cup", "2011-2012",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("UEFA Champion League","2011-2012",R.drawable.prize_c1));
        prizeArrayList.add(new Prize("UEFA Europa League","2012-2013",R.drawable.prize_c2));
        prizeArrayList.add(new Prize("Premier League", "2014-2015",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Carabao Cup","2014-2015",R.drawable.prize_carabaocup));
        prizeArrayList.add(new Prize("Premier League", "2016-2017",R.drawable.prize_epl));
        prizeArrayList.add(new Prize("Fa Cup", "2017-2018",R.drawable.prize_facup));
        prizeArrayList.add(new Prize("UEFA Europa League","2018-2019",R.drawable.prize_c2));
        prizeArrayList.add(new Prize("UEFA Champion League","2020-2021",R.drawable.prize_c1));
        prizeArrayList.add(new Prize("UEFA Super Cup","2001",R.drawable.prize_supercup));
    }
}
