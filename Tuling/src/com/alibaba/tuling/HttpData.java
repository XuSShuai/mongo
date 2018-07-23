package com.alibaba.tuling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String> {

	private HttpClient httpClient;
	private HttpGet httpGet;
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;
	private InputStream inputStream;
	private String urlString;

	private GetHttpDataInterface getHttpDataInterface;

	public HttpData(String urlString, GetHttpDataInterface getHttpDataInterface) {
		// TODO Auto-generated constructor stub
		this.urlString = urlString;
		this.getHttpDataInterface = getHttpDataInterface;
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub

		try {
			httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(urlString);
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String lineString = null;
			StringBuffer stringBuffer = new StringBuffer();
			while ((lineString = bufferedReader.readLine()) != null) {
				stringBuffer.append(lineString);
			}
			return stringBuffer.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		getHttpDataInterface.dealHttpData(result);
		super.onPostExecute(result);
	}
}
