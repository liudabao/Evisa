package com.lium.evisa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lium.evisa.R;
import com.lium.evisa.entity.Comment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lenovo on 2016/4/19.
 */
public class CommentAdapter extends BaseAdapter{
    private Context context;
    private List<Comment> list;

    public CommentAdapter(Context context,List<Comment> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_commenadapter_item,null);
            viewHolder.tv_author=(TextView)convertView.findViewById(R.id.tv_author);
            viewHolder.tv_comment=(TextView)convertView.findViewById(R.id.tv_content);
            viewHolder.tv_date=(TextView)convertView.findViewById(R.id.tv_date);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
//        Log.e("Comment",list.get(position).getAuthor());
        viewHolder.tv_author.setText(list.get(position).getAuthor());
        viewHolder.tv_comment.setText(list.get(position).getConent());
//        viewHolder.tv_date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(position).getDay()));
        return convertView;
    }

    class ViewHolder{
        public TextView tv_author;
        public TextView tv_comment;
        public TextView tv_date;
    }

}
