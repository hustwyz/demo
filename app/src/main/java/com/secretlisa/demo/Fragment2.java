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
private ListView  listView2;
private List<student> student_list;
 private  student   student;
 private  String   url="http://api.secretlisa.com/app/rec_apps?app=com.secretlisa.xueba";

    private   Handler  hander=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            { listView2.setAdapter(new MyAdapter(getActivity(),student_list));

            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View  view= inflater.inflate(R.layout.fragment2, container, false);
        listView2= (ListView) view.findViewById(R.id.listview2);
        Log.e("ddd", "-----------------------------------------");
        getInfo();
       return   view;
    }


    public  void   getInfo(){
        HttpUtils   utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                student_list=new ArrayList<student>();
                try {
                    JSONObject  obj1=new JSONObject(responseInfo.result);
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
                    System.out.print("----------------------------------------------------------------------"+student_list);
                      hander.sendEmptyMessage(1);
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
