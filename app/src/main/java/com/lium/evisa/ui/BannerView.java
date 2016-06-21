package com.lium.evisa.ui;

import java.util.ArrayList;
import java.util.List;

import com.lium.evisa.R;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BannerView  extends FrameLayout {



    private List<ImageView> imageList;

    private List<View> dotList;
    
    private ViewPager viewPager;

	private TextView tv;

    private int currentItem  = 0;
    
    private Context _context;

	private DisplayImageApi displayImageApi;
    
    public BannerView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    
	public BannerView(Context context, AttributeSet attrs, int defStyle) {
		
		super(context,attrs, defStyle);
		// TODO Auto-generated constructor stub
		_context=context;
		initView();
		
	}
		
	public void initView(){
		LayoutInflater.from(_context).inflate(R.layout.ui_layout_banner, this, true);
		imageList=new ArrayList<ImageView>();
		dotList=new ArrayList<View>();
		LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
		dotLayout.removeAllViews();
		for(int i=0;i< GlobalValue.imageUrls.length;i++){
			ImageView image=new ImageView(_context);
			imageList.add(image);
			displayImageApi = DisplayImageApiFactory.getInstance();
			displayImageApi.display(image,GlobalValue.imageUrls[i],R.drawable.ic_launcher);
			/*RequestQueue mQueue = Volley.newRequestQueue(_context);
			ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
			ImageListener listener = ImageLoader.getImageListener(image,R.drawable.ic_launcher, R.drawable.ic_launcher);			
			imageLoader.get(imageUrls[i], listener);*/

			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	ImageView dotView=new ImageView(_context);
        	if(i==0){
        		dotView.setBackgroundResource(R.drawable.bg_view_focused);
        	}        	
        	else{
        		dotView.setBackgroundResource(R.drawable.bg_view_blured);
        	}
			params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
        	dotList.add(dotView);
		}
		tv=(TextView)findViewById(R.id.banner_tv);
		viewPager = (ViewPager) findViewById(R.id.banner_Pager);
        //viewPager.setFocusable(true);
		tv.setText(GlobalValue.imageText[0]);
		BannerAdapter adapter=new BannerAdapter(_context,imageList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new BannerPageChangeListener());

        
	}
	
	class BannerAdapter extends PagerAdapter{

		List<ImageView> _list;
		Context _ct;
		public BannerAdapter(Context ct,List<ImageView> list){
			Log.e("TAG", "list size is: "+list.size());
			_list=list;
			_ct=ct;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return _list.size();
		}

		@Override
        public Object instantiateItem(View container, final int position) {
			ImageView v=_list.get(position);
			((ViewPager)container).addView(v);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.e("Click",position+" click");
					PackageManager manager= GlobalContext.getContext().getPackageManager();
					List<ApplicationInfo> appList=manager.getInstalledApplications(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
					for(ApplicationInfo app:appList){
						Log.e("APP", app.loadLabel(manager)+" "+app.packageName);
					}
					Intent intent=manager.getLaunchIntentForPackage("ctrip.android.view");
					if(intent!=null){
						GlobalContext.getContext().startActivity(intent);
					}
					else {
						Toast.makeText(GlobalContext.getContext(), "哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
					}
				}
			});
            return v;
        }
		
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(_list.get(position));
        }

		
	}
	
	class BannerPageChangeListener implements OnPageChangeListener{

		//@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		//@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		//@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			currentItem = position;
            for(int i=0;i < dotList.size();i++){
                if(i == position){
                    ((View)dotList.get(position)).setBackgroundResource(R.drawable.bg_view_focused);
					tv.setText(GlobalValue.imageText[position]);
                }else {
                	((View)dotList.get(i)).setBackgroundResource(R.drawable.bg_view_blured);
                }
            }
		}
		
	}

}
