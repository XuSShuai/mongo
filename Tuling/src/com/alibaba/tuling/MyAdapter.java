package com.alibaba.tuling;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	private List<Content> listData = null;
	private Context context = null;
	private RelativeLayout layout = null;
	
	public MyAdapter(List<Content> list, Context context) {
		this.listData = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.listData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = LayoutInflater.from(this.context);//获得xml文件
		if( listData.get(arg0).getFlag() == Content.SEND )
			layout = (RelativeLayout) layoutInflater.inflate(R.layout.rightitem, null);
		if( listData.get(arg0).getFlag() == Content.RECEIVE )
			layout = (RelativeLayout) layoutInflater.inflate(R.layout.leftitem, null);
		TextView messageTextView = (TextView)layout.findViewById(R.id.textView_message);
		messageTextView.setText(listData.get(arg0).getContentString());
		return layout;
	}

}
