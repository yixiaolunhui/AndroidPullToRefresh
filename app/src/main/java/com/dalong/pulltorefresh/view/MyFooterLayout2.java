package com.dalong.pulltorefresh.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalong.pulltorefresh.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by zhouweilong on 2016/11/7.
 */

public class MyFooterLayout2 extends FrameLayout {

    private  LinearLayout footer_base;
    private TextView footerTv;

    public MyFooterLayout2(Context context) {
        this(context, PullToRefreshBase.Mode.PULL_FROM_END);
    }

    public MyFooterLayout2(Context context, PullToRefreshBase.Mode mode) {
        super(context);
        init(context,mode);
    }

    private void init(Context mContext,PullToRefreshBase.Mode mode) {
        LayoutInflater.from(mContext).inflate(R.layout.my_refresh_footer, this);
        footer_base=(LinearLayout)findViewById(R.id.footer_base);
        footerTv=(TextView)findViewById(R.id.footer);

        LayoutParams lp = (LayoutParams) footer_base.getLayoutParams();
        lp.gravity = mode == PullToRefreshBase.Mode.PULL_FROM_END ? Gravity.TOP : Gravity.BOTTOM;
        footerTv.setText("正在加载");
    }

}
