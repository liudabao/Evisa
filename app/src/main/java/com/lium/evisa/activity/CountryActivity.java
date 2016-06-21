package com.lium.evisa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lium.evisa.R;
import com.lium.evisa.adapter.CommentAdapter;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.entity.Comment;
import com.lium.evisa.entity.Country;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountryActivity extends Activity{

	private Button btn_apply;
	private ImageView iv_country;
	private TextView tv_name;
	private TextView tv_period;
	private TextView tv_price;
	private TextView tv_tips;
	private TextView tv_introduce;
	private TextView tv_top;
	private ListView lv_com;
	private ImageButton backImage;
	private CommentAdapter mCommentAdapter;

	private Country mCountry;
	private List<Comment> mList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_country);
		initData();
		initView();
	}

	private void initData(){
		Intent intent=getIntent();
		mCountry=(Country) intent.getSerializableExtra(GlobalValue.COUNTRY_KEY);
		mList=new ArrayList<Comment>();
		for(int i=0;i<1;i++){
			Comment comment=new Comment();
			//comment.setAuthor("adoby");
			comment.setConent("暂无评论");
			//comment.setDay(new Date());
			mList.add(comment);
		}
	}

	private void initView(){
		backImage=(ImageButton)findViewById(R.id.iv_country_back);
		iv_country=(ImageView)findViewById(R.id.iv_country);
		tv_name=(TextView)findViewById(R.id.tv_visa_name);
		tv_period=(TextView)findViewById(R.id.tv_visa_period);
		tv_price=(TextView)findViewById(R.id.tv_visa_price);
		tv_tips=(TextView)findViewById(R.id.tv_tips);
		tv_introduce=(TextView)findViewById(R.id.tv_visa_introduce);
		tv_top=(TextView)findViewById(R.id.tv_country_top);
		lv_com=(ListView)findViewById(R.id.lv_comment);
		//iv_country.setBackgroundResource(R.drawable.ic_launcher);
		mCommentAdapter=new CommentAdapter(this,mList);
		lv_com.setAdapter(mCommentAdapter);
		lv_com.setFocusable(false);

		tv_top.setText(mCountry.getName());
		tv_name.setText(mCountry.getName());
		tv_period.setText("有效期："+mCountry.getPeriod()+"天");
		tv_price.setText("费用："+mCountry.getPrice()+"美元");
		tv_tips.setText(mCountry.getTips()+"");
        tv_introduce.setText(mCountry.getIntroduce());
        DisplayImageApi displayImageApi= DisplayImageApiFactory.getInstance();
		displayImageApi.display(iv_country,mCountry.getImaUrl());

		/*Log.e("Country",country.getName());
		Log.e("Country",country.getPeriod()+"");
		Log.e("Country",country.getPrice()+"");
		Log.e("Country",country.getTips()+"");*/

		btn_apply=(Button)findViewById(R.id.btn_apply);
		btn_apply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CountryActivity.this,VisaActivity.class);
				intent.putExtra(GlobalValue.URL_KEY,mCountry.getUrl());
				intent.putExtra(GlobalValue.COUNTRY_KEY,mCountry.getName());
				startActivity(intent);
			}
		});
		backImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
}
