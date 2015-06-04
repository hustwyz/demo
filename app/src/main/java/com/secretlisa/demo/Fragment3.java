package com.secretlisa.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by hehaitao on 2015/6/1.
 */
public class Fragment3 extends Fragment implements View.OnClickListener,PointsChangeNotify,PointsEarnNotify {
   private TextView  mTextViewPonts;
    private ImageView   img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View  view=  inflater.inflate(R.layout.fragment3, container, false);
        AdManager.getInstance(getActivity()).init("cfdbdd2786ea88ea", "d8edde7d10dd0073");
        AdManager.getInstance(getActivity()).setUserDataCollect(true);
        OffersManager.getInstance(getActivity()).onAppLaunch();
        PointsManager.getInstance(getActivity()).registerNotify(this);
        PointsManager.getInstance(getActivity()).registerPointsEarnNotify(this);

        img= (ImageView) view.findViewById(R.id.btn_show_offerswall);
        img.setOnClickListener(this);
        mTextViewPonts= (TextView) view.findViewById(R.id.pointsBalance);
        int pointsBalance=PointsManager.getInstance(getActivity()).queryPoints();
        mTextViewPonts.setText("积分余额" + pointsBalance);


        return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PointsManager.getInstance(getActivity()).unRegisterNotify(this);
        PointsManager.getInstance(getActivity()).unRegisterPointsEarnNotify(this);
        OffersManager.getInstance(getActivity()).onAppExit();

    }

  /*  private    void   init(){


    }*/


    @Override
    public void onClick(View arg0) {
        switch (arg0.getId())
        {
            case R.id.btn_show_offerswall:
                OffersManager.getInstance(getActivity()).showOffersWall(



                );
                  break;
            default:break;
        }

    }

    @Override
    public void onPointBalanceChange(int i) {
        mTextViewPonts.setText("积分余额："+i);

    }

    @Override
    public void onPointEarn(Context context, EarnPointsOrderList earnPointsOrderList) {
       for(int i=0;i<earnPointsOrderList.size();++i)
       {
           EarnPointsOrderInfo  info=earnPointsOrderList.get(i);
           Toast.makeText(getActivity(),info.getMessage(),Toast.LENGTH_LONG).show();
       }
    }
}
