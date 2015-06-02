package com.secretlisa.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretlisa.demo.LoadBitmap;
import com.secretlisa.demo.R;

import com.secretlisa.demo.bean.student;

import java.util.List;

/**
 * Created by hehaitao on 2015/6/1.
 */
public class MyAdapter extends BaseAdapter{
    private List<student>  list;
    private Context context;
    public   static  int   index=1;



    public MyAdapter(Context context, List<student> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return 3*index ;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder   holder=null;
        if(convertView==null)
        {

            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            holder=new ViewHolder();
            holder.iv= (ImageView) convertView.findViewById(R.id.item_image);
            holder.tv1= (TextView) convertView.findViewById(R.id.tv1);
            holder.tv2=(TextView)convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);


        }
        else {
             holder= (ViewHolder) convertView.getTag();
        }
           student   stu=list.get(position);
             holder.tv1.setText(stu.getName());
             holder.tv2.setText(stu.getIntro());

        LoadBitmap.getBitmap(stu.getIco(),holder.iv,context);



        return convertView;
    }
    class ViewHolder{
        ImageView  iv;
        TextView   tv1;
        TextView   tv2;


    }



}
