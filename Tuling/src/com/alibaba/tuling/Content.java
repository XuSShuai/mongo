package com.alibaba.tuling;

public class Content {
	private String contentString;
	public static final int SEND = 1;
	public static final int RECEIVE = 2;
	private int flag;
	
	public Content(String contentString, int flag) {
		// TODO Auto-generated constructor stub
		setContentString(contentString);
		setFlag(flag);
	}
	
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
	public String getContentString() {
		return contentString;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
}
