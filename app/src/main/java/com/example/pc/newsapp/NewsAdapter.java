package com.example.pc.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.NestedScrollingChild;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pc on 2017-07-10.
 */

public class NewsAdapter extends BaseAdapter{

    private Context context;
    private NewsBean bean;

    public NewsAdapter(Context context, NewsBean bean){
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getChannel().getItem().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getChannel().getItem().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. ListView에 표시하고자 하는 layout을 inflate함
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.lay_news, null);

        //2. convertview에 뿌려줄 데이터를 취득한다.(1건)
        final NewsBean.Channel.Item item = bean.getChannel().getItem().get(position);

        TextView txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView txtDate = (TextView)convertView.findViewById(R.id.txtDate);
        TextView txtDesc = (TextView)convertView.findViewById(R.id.txtDesc);

        txtTitle.setText(item.getTitle());
        txtDate.setText(item.getPubDate());
        txtDesc.setText(item.getDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //클릭시 해당 기사로 이동
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri u = Uri.parse(item.getLink());
                intent.setData(u);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
