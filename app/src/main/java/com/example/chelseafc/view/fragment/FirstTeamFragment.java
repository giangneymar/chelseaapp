package com.example.chelseafc.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chelseafc.view.adapter.FirstTeamAdapter;
import com.example.chelseafc.model.FirstTeam;
import com.example.chelseafc.R;

import java.util.ArrayList;

public class FirstTeamFragment extends Fragment {

    private View view;
    ListView listViewFirstTeam;
    ArrayList<FirstTeam> firstTeamArrayList;
    FirstTeamAdapter firstTeamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_team, container, false);

        init();
        setListViewFirstTeam();

        return view;
    }



    private void setListViewFirstTeam() {
        firstTeamAdapter = new FirstTeamAdapter(getActivity(), R.layout.row_first_team, firstTeamArrayList);
        listViewFirstTeam.setAdapter(firstTeamAdapter);
    }

    private void init() {
        firstTeamArrayList = new ArrayList<>();
        listViewFirstTeam = (ListView) view.findViewById(R.id.listViewFirstTeam);
        firstTeamArrayList.add(new FirstTeam("Thomas Tuchel", 0, R.drawable.coach_tuchel, "Germany", "29/08/1973 ( 48 tuổi )","HLV"));


        firstTeamArrayList.add(new FirstTeam("Kepa Arrizabalaga", 1, R.drawable.player_kepa, "Spain", "03/10/1994 (27 tuổi)","GK"));
        firstTeamArrayList.add(new FirstTeam("Marcus Bettinelli", 13, R.drawable.player_bettinelli, "England", "24/05/1992 (29 tuổi)","GK"));
        firstTeamArrayList.add(new FirstTeam("Edouard Mendy", 16, R.drawable.player_mendy, "Senegal", "01/03/1992 (29 tuổi)","GK"));


        firstTeamArrayList.add(new FirstTeam("Antonio Rudiger", 2, R.drawable.player_rudiger, "Germany", "03/03/1993 (28 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Marcos Alonso", 3, R.drawable.player_alonso, "Spain", "28/12/1990 (31 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Andreas Christensen", 4, R.drawable.player_christensen, "Denmark", "10/04/1996 (25 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Thiago Silva", 6, R.drawable.player_silva, "Brazil", "22/09/1984 (37 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Trevoh Chalobah", 14, R.drawable.player_chalobah, "Sierra Lenone", "05/07/1999 (22 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Ben Chilwell", 21, R.drawable.player_chilwell, "England", "21/12/1996 (25 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Reece James", 24, R.drawable.player_james, "England", "08/12/1999 (22 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Cesar Azpilicueta", 28, R.drawable.player_dave, "Spain", "28/08/1989 (32 tuổi)","DF"));
        firstTeamArrayList.add(new FirstTeam("Malang Sarr", 31, R.drawable.player_sarr, "France", "23/01/1999 (23 tuổi)","DF"));


        firstTeamArrayList.add(new FirstTeam("Jorginho", 5, R.drawable.player_jorginho, "Brazil", "20/12/1991 (30 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("N'Golo Kante", 7, R.drawable.player_kante, "France", "29/03/1991 (30 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("Mateo Kovacic", 8, R.drawable.player_kovacic, "Austria", "06/05/1994 (27 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("Ruben Loftus-Cheek", 12, R.drawable.player_cheek, "England", "23/01/1996 (26 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("Saul Niguez", 17, R.drawable.player_saul, "Spain", "21/11/1994 (27 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("Ross Barkley", 18, R.drawable.player_barkley, "England", "05/12/1993 (28 tuổi)","MF"));
        firstTeamArrayList.add(new FirstTeam("Mason Mount", 19, R.drawable.player_mount, "England", "10/01/1999 (23 tuổi)","MF"));


        firstTeamArrayList.add(new FirstTeam("Romelu Lukaku",9,R.drawable.player_lukaku,"Belgium","13/05/1993 (28 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Christian Pulisic",10,R.drawable.player_pulisic,"USA","18/09/1998 (23 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Timo Werner",11,R.drawable.player_werner,"Germany","06/03/1996 (25 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Callum Hudson-Odoi",20,R.drawable.player_odoi,"England","07/11/2000 (21 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Hakim Ziyech",22,R.drawable.player_hakim,"Netherlands","19/03/1993 (28 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Kenedy",23,R.drawable.player_kenedy,"Brazil","08/02/1996 (25 tuổi)","FW"));
        firstTeamArrayList.add(new FirstTeam("Kai Havertz",29,R.drawable.player_havert,"Germany","11/06/1999 (22 tuổi)","FW"));
    }
}
