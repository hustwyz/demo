package com.secretlisa.demo.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretlisa.demo.R;

/**
 * Created by hehaitao on 2015/6/8.
 */
public class Vp_fragment4   extends Fragment {
    private  int[] imgs={R.mipmap.logo_guide_2_5,R.mipmap.logo_guide_2_4,R.mipmap.logo_guide_2_3};
   private   static  final  String  KEY="position";
    public static  Vp_fragment4 getFragment(int position)

    {
            Vp_fragment4    vp_fragment4=new Vp_fragment4();
        Bundle bundle = new Bundle();//实例化bundle对象
        bundle.putInt(KEY, position);//使用bundle对象封装要发送的信息    key
        vp_fragment4.setArguments(bundle);
        return vp_fragment4;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        ImageView  imgv=new ImageView(getActivity());
       // TextView   txt=new TextView(getActivity());
    //   txt.setText("当前页"+getArguments().getString("str"));
        imgv.setImageResource(imgs[getArguments().getInt(KEY)]);
           return   imgv;
    }
 }
