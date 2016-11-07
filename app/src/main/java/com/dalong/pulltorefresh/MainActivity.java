package com.dalong.pulltorefresh;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dalong.pulltorefresh.view.MyFooterLayout;
import com.dalong.pulltorefresh.view.MyHeaderLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshRecyclerView mPullRefreshRecyclerView;
    private RecyclerView mRecyclerView;
    private List<String> mDatas=new ArrayList<>();
    private String[] images=new String[]{
            "http://pic2.58.com/zp_images/allimg/131225/21_131225140438_1.jpg",
            "http://pic1.shejiben.com/case/2015/07/22/20150722123623-d4a14a28.jpg",
            "http://3.pic.58control.cn/p1/big/n_v1bkuyfvoqb2rvnkbf2rnq_4efbf870af0a0e2d.jpg",
            "http://images.zx123.cn/uploadfile/2015/0706/20150706110006_43517.jpg",
            "http://timg.ddmapimg.com/topic/20120817/20120817_144524_66.jpg",
            "http://www.qblzs.com/wp-content/uploads/2015/04/39-930x405.jpg",
            "http://img6.hfhouse.com/news/content/2014-11-04/0984536001415085411.jpg",
            "http://img.xtuan.com/upload/xiaoguotu/20121029/20121029101554100944_w.jpg",
            "http://images.zx123.cn/uploadfile/2015/0416/20150416151835_20786.jpg",
            "http://images.zx123.cn/uploadfile/2015/0410/20150410104855_86627.jpg",
            "http://d.hiphotos.baidu.com/zhidao/pic/item/f11f3a292df5e0fe8228c1775c6034a85edf7297.jpg",
            "http://img.fht360.com/content/image/20150824687db0e825c249e2a7f83fc73233cb9a.png",
            "http://news.homekoo.com/imgdata/htmlimg/20120911/093419.jpg"
    };
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDatas();
        mPullRefreshRecyclerView=(PullToRefreshRecyclerView)findViewById(R.id.recyclerview);
        mPullRefreshRecyclerView.setHeaderLayout(new MyHeaderLayout(this));
        mPullRefreshRecyclerView.setFooterLayout(new MyFooterLayout(this));
        mRecyclerView=mPullRefreshRecyclerView.getRefreshableView();
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter=new CommonAdapter<String>(this, R.layout.item_list, mDatas)
        {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ImageView myImageView= holder.getView(R.id.item_img);
                Glide.with(MainActivity.this)
                        .load(s)
                        .centerCrop()
                        .crossFade()
                        .into(myImageView);
            }
        };
        mRecyclerView.setAdapter(adapter);
        mPullRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mHandler.removeMessages(0);
                mHandler.sendEmptyMessageDelayed(0,3000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessageDelayed(1,3000);
            }
        });
    }

    Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mDatas.clear();
                    getDatas();
                    adapter.notifyDataSetChanged();
                    mPullRefreshRecyclerView.onRefreshComplete();
                    break;
                case 1:
                    getDatas();
                    adapter.notifyDataSetChanged();
                    mPullRefreshRecyclerView.onRefreshComplete();
                    break;
            }
        }
    };
    public void  getDatas(){
        for (int i=0;i<images.length;i++){
            mDatas.add(images[i]);
        }
    }
}
