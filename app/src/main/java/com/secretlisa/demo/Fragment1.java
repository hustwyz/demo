package com.secretlisa.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hehaitao on 2015/6/1.
 */
public class Fragment1 extends Fragment {
private ListView listview;
    private List<String>  list=new ArrayList<String>();
    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment1, container, false);
                   listview= (ListView) view.findViewById(R.id.listview);
       listview.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getData()));
        return view;
    }

    private   List<String> getData(){
        for(int i=0;i<20;i++)
        {
            list.add("数据"+i);
        }

        return    list;


    }




}

