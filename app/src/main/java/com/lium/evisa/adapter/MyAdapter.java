package com.lium.evisa.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lium.evisa.R;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.entity.Country;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.ui.BannerView;

import java.util.List;

/**
 * Created by lenovo on 2016/4/14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CountryRecyclerViewHolder>{

    private List<Country> list;
    private Context context;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;


    public int footer_type=1;
    private  int height;
    public  View mFooterView;

    private OnRecyclerViewItemClickListener mListener;

    public interface OnRecyclerViewItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnRecyclerViewItemClickListener mOnItemClickLitener)
    {
        this.mListener = mOnItemClickLitener;
    }

    public View getFooterView(){
        if(mFooterView!=null){
            return  mFooterView;
        }
        return null;
    }

    public MyAdapter(Context context, List<Country> list){
        this.context=context;
        this.list=list;
    }

    private  ValueAnimator create(final View view, int start, int end){
        ValueAnimator valueAnimator=ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value=(Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams params=view.getLayoutParams();
                params.height=value;
                view.setLayoutParams(params);
            }
        });
        return valueAnimator;
    }



    @Override
    public void onBindViewHolder(final CountryRecyclerViewHolder holder, int position) {
        // TODO Auto-generated method stub
        if(getItemViewType(position)==TYPE_HEADER){
           // holder.headView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
           // holder.headView.setBackgroundResource(R.drawable.ic_launcher);
        }
        else if(getItemViewType(position)==TYPE_FOOTER){

            if(footer_type== GlobalValue.SUCCESS){
                holder.mProgressBar.setVisibility(View.VISIBLE);
                Log.e("progressbar", height+" success");

              //   if(holder.mProgressBar.getHeight()!=0){
              //      height=holder.mProgressBar.getHeight();
              //  }
               // Log.e("progressbar", height+"");
               // ValueAnimator valueAnimator=create(holder.mProgressBar, 0, height);
               // valueAnimator.setDuration(2000);
               // valueAnimator.start();
             }
            else{
               // ValueAnimator valueAnimator=create(holder.mProgressBar, height, 0);
               // valueAnimator.setDuration(2000);
              //  valueAnimator.start();
                holder.mProgressBar.setVisibility(View.GONE);
                Log.e("progressbar", height+" gone");
            }

        }
        else{
            DisplayImageApi displayImageApi= DisplayImageApiFactory.getInstance();;
            Country country=list.get(position-1);
           // Log.e("ImageUrl",country.getImaUrl());
           // holder.iv.setBackgroundResource(country.getImageId());
            displayImageApi.display(holder.iv, country.getImaUrl(),R.drawable.ic_launcher);
            holder.tv_name.setText(country.getName());
           // holder.tv_introduce.setText(country.getIntroduce());
           // holder.tv_period.setText("有效期："+country.getPeriod());
            //holder.tv_price.setText("费用："+country.getPrice());
            Log.e("size", country.getIntroduce().length()+"");
            if(country.getIntroduce().length()>50){
                holder.tv_introduce.setText(country.getIntroduce().substring(0,50)+"。。。");
            }
            else {
                holder.tv_introduce.setText(country.getIntroduce());
            }
            if(mListener!=null){

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mListener.onItemClick(holder.itemView,pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mListener.onItemLongClick(holder.itemView,pos);
                        return false;
                    }
                });
            }
        }


    }

    @Override
    public CountryRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO Auto-generated method stub
      //  CountryRecyclerViewHolder holder=new CountryRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.ui_recyclerview_item, parent,false));
       // return holder;
        View mView=null;
        switch(viewType){
            case TYPE_HEADER:
                mView = LayoutInflater.from(context).inflate(R.layout.ui_recyclerview_header, null);

                break;
            case TYPE_NORMAL:
                mView = LayoutInflater.from(context).inflate(R.layout.ui_recyclerview_item, null);

                break;
            case TYPE_FOOTER:
                mView = LayoutInflater.from(context).inflate(R.layout.ui_recyclerview_footer, null);
                mFooterView=mView;
                break;

        }
        return new CountryRecyclerViewHolder(mView,viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        if(position+1==getItemCount())
            return TYPE_FOOTER;
        return  TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return list.size()+2;
    }

    class CountryRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv_name;
        TextView tv_introduce;
        TextView tv_price;
        TextView tv_period;
        ProgressBar mProgressBar;
        BannerView headView;

        public CountryRecyclerViewHolder(View view){
            super(view);
            init(view);
        }

        public CountryRecyclerViewHolder(View view,int viewType){
            super(view);
            init(view ,viewType);
        }

        private void init(View view) {
            iv=(ImageView)view.findViewById(R.id.iv_country);
            tv_name=(TextView)view.findViewById(R.id.tv_country);
            tv_introduce=(TextView)view.findViewById(R.id.tv_country_introduce);
            tv_price=(TextView)view.findViewById(R.id.tv_price);
            tv_period=(TextView)view.findViewById(R.id.tv_period);

        }

        private void init(View view,int viewType) {
            switch (viewType) {
                case TYPE_HEADER:
                    headView = (BannerView) view.findViewById(R.id.ry_header_banner);
                    break;
                case TYPE_NORMAL:
                    iv = (ImageView) view.findViewById(R.id.iv_country);
                    tv_name = (TextView) view.findViewById(R.id.tv_country);
                    tv_introduce = (TextView) view.findViewById(R.id.tv_country_introduce);
                    tv_price = (TextView) view.findViewById(R.id.tv_price);
                    tv_period = (TextView) view.findViewById(R.id.tv_period);
                    break;
                case TYPE_FOOTER:
                    mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    break;

            }

        }

    }

}
