package com.secretlisa.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.secretlisa.demo.adapter.MyAdapter;
import com.secretlisa.demo.adapter.Vp_fragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hehaitao on 2015/6/1.
 */
public class Fragment4 extends Fragment {
    @Nullable
private ViewPager    viewPager;
    private int index=0;
   private ListView  listview1;
    private Handler   handler=new Handler(){
        public  void  handleMessage(android.os.Message msg)
        {
            index++;
            viewPager.setCurrentItem(index%3);
            handler.sendEmptyMessageDelayed(1,2000);
        }
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment4, container, false);
        viewPager=(ViewPager) view.findViewById(R.id.fragment4_viewpager);
        viewPager.setAdapter(new MyAdapter(getFragmentManager()));

        handler.sendEmptyMessageDelayed(1, 2000);
        listview1= (ListView) view.findViewById(R.id.listview1);
        List<String> data=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            data.add("数据---"+i);

        }

       ArrayAdapter<String>  adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        listview1.setAdapter(adapter);


        return view;


    }
    private   class  MyAdapter   extends FragmentPagerAdapter{


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Vp_fragment4.getFragment(position);
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}
