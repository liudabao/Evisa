package com.lium.evisa.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lium.evisa.R;
import com.lium.evisa.entity.Country;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CountryViewHolder>{
	

	private List<Country> list;
	private Context context;

	public RecyclerViewAdapter(Context context, List<Country> list){
		this.context=context;
		this.list=list;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public void addItem(Country country, int position) {
		list.add(position, country);
		notifyItemInserted(position);
	}

	public void removeItem(int position) {
		list.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public void onBindViewHolder(CountryViewHolder holder, int position) {
		// TODO Auto-generated method stub
		Country country=list.get(position);
		holder.iv.setBackgroundResource(country.getImageId());
		holder.tv_name.setText(country.getName());
		holder.tv_introduce.setText(country.getIntroduce());
		holder.tv_period.setText("有效期："+country.getPeriod());
		holder.tv_price.setText("费用："+country.getPrice());

	}

	@Override
	public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		CountryViewHolder holder=new CountryViewHolder(LayoutInflater.from(context).inflate(R.layout.ui_recyclerview_item, parent,false));
		return holder;
	}

	class CountryViewHolder extends ViewHolder {

		ImageView iv;
		TextView tv_name;
		TextView tv_introduce;
		TextView tv_price;
		TextView tv_period;
		//ProgressBar mProgressBar;
		public CountryViewHolder(View view) {
			super(view);
			// TODO Auto-generated constructor stub
			iv=(ImageView)view.findViewById(R.id.iv_country);
			tv_name=(TextView)view.findViewById(R.id.tv_country);
			tv_introduce=(TextView)view.findViewById(R.id.tv_country_introduce);
			tv_price=(TextView)view.findViewById(R.id.tv_price);
			tv_period=(TextView)view.findViewById(R.id.tv_period);
		}
		/*public CountryViewHolder(View view,int viewType) {
			super(view);
			// TODO Auto-generated constructor stub
			init(view,viewType);
		}
		private void init(View view, int viewType) {
			switch (viewType) {
				case LOADMORE:
					mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
					break;
				case NORMAL:
					iv=(ImageView)view.findViewById(R.id.iv_country);
					tv_name=(TextView)view.findViewById(R.id.tv_country);
					tv_introduce=(TextView)view.findViewById(R.id.tv_country_introduce);
					tv_price=(TextView)view.findViewById(R.id.tv_price);
					tv_period=(TextView)view.findViewById(R.id.tv_period);
					break;
				default:
					break;
			}
		}*/
	}
}
