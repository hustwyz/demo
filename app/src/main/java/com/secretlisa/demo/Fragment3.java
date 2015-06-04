package com.secretlisa.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by hehaitao on 2015/6/1.
 */
public class Fragment3 extends Fragment implements View.OnClickListener {

  private   ImageView imageView1, imageView2;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View  view=  inflater.inflate(R.layout.fragment31, container, false);
        imageView1 = (ImageView) view.findViewById(R.id.dianlei);
        imageView2 =(ImageView) view.findViewById(R.id.duomeng);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
           return  view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dianlei:
                Intent intent=new Intent(getActivity(),DianleActivity.class);
                startActivity(intent);
                break;

            case  R.id.duomeng:
                Intent intent1=new Intent(getActivity(),DuomengActivity.class);
                startActivity(intent1);
                break;

        }

    }


}
