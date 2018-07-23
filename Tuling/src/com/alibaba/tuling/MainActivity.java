package com.alibaba.tuling;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;

public class MainActivity extends Activity implements GetHttpDataInterface,Button.OnClickListener {

	List<Content> list = null;
	ListView listView = null;
	EditText message = null;
	Button sendButton = null;
	MyAdapter adapter = null;
	String welcomes[] = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		list = new ArrayList<Content>();
		listView = (ListView) findViewById(R.id.listview);
		message = (EditText) findViewById(R.id.editText_sendText);
		sendButton = (Button) findViewById(R.id.button_send);
		sendButton.setOnClickListener(this);
		adapter = new MyAdapter(list, this);
		listView.setAdapter(adapter);
		Content content = new Content(this.getWelcome(), Content.RECEIVE);
		list.add(content);
	}
	
	private String getWelcome()
	{
		welcomes = this.getResources().getStringArray(R.array.welcome);
		int index = (int) (Math.random()*(welcomes.length - 1));
		return welcomes[index];
	}

	@Override
	public void dealHttpData(String data) {
		// TODO Auto-generated method stub
		// Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
		try {
			JSONObject jsonObject = new JSONObject(data);
			Content content = new Content(jsonObject.getString("text"),
					Content.RECEIVE);
			list.add(content);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// 得到要发送的信息
		String requireString = message.getText().toString();
		message.setText("");
		// 去掉空格
		String requireString_new = requireString.replace(" ", "");
		// 去掉换行符
		requireString_new = requireString.replace("\n", "");
		Content content = new Content(requireString, Content.SEND);
		list.add(content);
		if( list.size() > 40 )
		{
			for( int i = 0; i <= 10; i++ )
			{
				list.remove(i);
			}
		}
		adapter.notifyDataSetChanged();
		String urlString = "http://www.tuling123.com/openapi/api?key=271b5d4f1e6946e5b7550f7555447018&info="
				+ requireString_new;
		HttpData httpData = (HttpData) new HttpData(urlString, this).execute();
	}
}
