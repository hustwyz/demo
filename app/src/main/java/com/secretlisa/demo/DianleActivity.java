package com.secretlisa.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.youmi.android.AdManager;
import net.youmi.android.offers.EarnPointsOrderInfo;
import net.youmi.android.offers.EarnPointsOrderList;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsChangeNotify;
import net.youmi.android.offers.PointsEarnNotify;
import net.youmi.android.offers.PointsManager;

/**
 * Created by hehaitao on 2015/6/4.
 */
public class DianleActivity extends Activity implements View.OnClickListener,PointsChangeNotify,PointsEarnNotify {
     TextView mTextViewPonts;
     ImageView img;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.dianleactivity);

      img= (ImageView) findViewById(R.id.btn_show_offerswall);
      img.setOnClickListener(this);
      mTextViewPonts= (TextView) findViewById(R.id.pointsBalance);
      int pointsBalance=PointsManager.getInstance(this).queryPoints();
      mTextViewPonts.setText("积分余额" + pointsBalance);

        AdManager.getInstance(this).init("cfdbdd2786ea88ea", "d8edde7d10dd0073");
        AdManager.getInstance(this).setUserDataCollect(true);
        OffersManager.getInstance(this).onAppLaunch();
        PointsManager.getInstance(this).registerNotify(this);
        PointsManager.getInstance(this).registerPointsEarnNotify(this);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PointsManager.getInstance(this).unRegisterNotify(this);
        PointsManager.getInstance(this).unRegisterPointsEarnNotify(this);
        OffersManager.getInstance(this).onAppExit();

    }

    @Override
    public void onPointBalanceChange(int i) {
        mTextViewPonts.setText("积分余额：" + i);
    }

    @Override
    public void onPointEarn(Context context, EarnPointsOrderList earnPointsOrderList) {
        for(int i=0;i<earnPointsOrderList.size();++i)
        {
            EarnPointsOrderInfo info=earnPointsOrderList.get(i);
            Toast.makeText(this, info.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_show_offerswall:
                OffersManager.getInstance(this).showOffersWall(
                  /*      new Interface_ActivityListener() {
                            @Override
                            public void onActivityDestroy(Context context) {
                                Toast.makeText(context,"全屏积分墙退出",Toast.LENGTH_SHORT).show();
                            }
                        }*/

                );
                break;
            default:
                break;
        }
    }

}
