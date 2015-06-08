package com.secretlisa.demo;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.aow.android.DAOW;
import cn.aow.android.DCloseListener;
import cn.aow.android.DListener;
import cn.aow.android.SDKLoadListener;

/**
 * Created by hehaitao on 2015/6/4.
 */
public class DuomengActivity extends Activity  implements View.OnClickListener {
    private   String   Tag=DuomengActivity.class.toString();
    private TextView  showPointTv;
    private  EditText  pointEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duomengactivity);
        findViewById(R.id.open_offerwall).setOnClickListener(this);
        showPointTv= (TextView) findViewById(R.id.showPoints);

 /*       DAOW.getInstance(this).init("96ZJ2b8QzehB3wTAwQ", new SDKLoadListener() {
            @Override
            public void onStart() {
                Log.v(Tag,"积分初始化开始");
            }
            @Override
            public void onSuccess() {
                Log.v(Tag,"积分初始化完成");
            }
            @Override
            public void onFail() {
            Log.v(Tag,"积分墙初始化失败");
            }
            @Override
            public void onLoading() {
              Log.v(Tag,"积分初始化中");
            }
        });*/

        DAOW.getInstance(this).init("96ZJ2b8QzehB3wTAwQ", new SDKLoadListener() {
            @Override
            public void onStart() {
                Log.v("--", "积分初始化开始");
            }

            @Override
            public void onSuccess() {
                Log.v("--","积分初始化完成");
            }

            @Override
            public void onFail() {
                Log.v("--","积分墙初始化失败");
            }

            @Override
            public void onLoading() {
                Log.v("--","积分初始化中");
            }
        });





/*
    DAOW.getInstance(this).setOnCloseListener(new DCloseListener() {
    @Override
      public void onClose() {
        Toast.makeText(DuomengActivity.this,"积分墙退出",Toast.LENGTH_SHORT).show();
    }
});*/
          checkPoints();

    }

    @Override
    public void onClick(View v) {
        int  id=v.getId();
        switch (id)
        {
            case R.id.open_offerwall:
                openOfferWall();
                break;
            default:
                break;

        }

    }
    private  void   openOfferWall()
    {
        DAOW.getInstance(this).show(this);
    }

    private    void   checkPoints(){
        DAOW.getInstance(this).checkPoints(new DListener() {
            @Override
            public void onResponse(Object... point) {
                Integer  totalPoint=(Integer)point[1];
                Integer consumPoint = (Integer) point[0]; // 用户的剩余积分数
                int lastPoint = totalPoint - consumPoint;
                showPointTv.setText("总积分:" + totalPoint + "\n已消费积分:"
                        + consumPoint + "\n剩余积分:" + lastPoint);
            }
            @Override
            public void onError(String s) {

            }
        });


    }

}
