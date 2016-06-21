package com.lium.evisa.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lium.evisa.R;


/**
 * Created by lium on 2016/4/12.
 */
public class ScrollRecyclerView extends LinearLayout {

    private RecyclerView recyclerView;
    private LinearLayout ll;
    private Context context;


    public ScrollRecyclerView(Context context){
        super(context);
    }
    public ScrollRecyclerView(Context context,AttributeSet attributes){
        super(context,attributes);
        this.context=context;
        initView();
    }

    private void initView(){
        View.inflate(context,R.layout.ui_scrollrecyclerview,this);
        recyclerView=(RecyclerView)findViewById(R.id.rv_country);
        ll=(LinearLayout)findViewById(R.id.ll_banner);
        recyclerView.setFocusable(false);
    }

    public RecyclerView getRecycleView(){
        return recyclerView;
    }

    public LinearLayout getLinearLayout(){
        return ll;
    }

}
