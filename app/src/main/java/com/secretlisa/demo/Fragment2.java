package com.secretlisa.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.secretlisa.demo.adapter.MyAdapter;
import com.secretlisa.demo.bean.student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends   Fragment {
private PullToRefreshListView listView2;
private List<student> student_list;
 private  student   student;
   private   MyAdapter  adapter;
 private  String   url="http://api.secretlisa.com/app/rec_apps?app=com.secretlisa.xueba";

    private   Handler  handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            {
                adapter=new MyAdapter(getActivity(),student_list);
                listView2.setAdapter(adapter);

            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View  view= inflater.inflate(R.layout.fragment2, container, false);
        listView2= (PullToRefreshListView) view.findViewById(R.id.listview2);
        Log.e("ddd", "-----------------------------------------");
        getInfo();
        init(view);
       return   view;
    }
 private   void  init(View view)
 {
     listView2.setMode(PullToRefreshBase.Mode.BOTH);
     listView2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
         @Override
         public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
             Toast.makeText(getActivity(),"上啦刷新",Toast.LENGTH_SHORT).show();
           //  adapter.notifyDataSetChanged();
             listView2.onRefreshComplete();
         }

         @Override
         public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
           Toast.makeText(getActivity(),"下拉加载",Toast.LENGTH_SHORT).show();
             MyAdapter.index++;
             new Thread(){
                 @Override
                 public void run() {
                     try {
                         Thread.sleep(600);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }.start();
             adapter.notifyDataSetChanged();
             listView2.onRefreshComplete();

         }
     });



 }






    public  void   getInfo(){
        HttpUtils   utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                student_list=new ArrayList<student>();
                try {
                    JSONObject  obj1=new JSONObject(objectResponseInfo.result);
                    JSONArray arr=obj1.getJSONArray("data");
                    for(int i=0;i<arr.length();i++)
                    {
                        student=new student();
                        JSONObject obj3=arr.getJSONObject(i);
                        student.setName(obj3.getString("name"));
                        student.setIntro(obj3.getString("intro"));
                        student.setIco(obj3.getString("icon"));
                        student_list.add(student);
                    }
                    System.out.print("----------------------------------------------------------------------"+student_list.toString());
                      handler.sendEmptyMessage(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), "____________________________________________________________失败", Toast.LENGTH_SHORT).show();
            }
        });
    }





}
