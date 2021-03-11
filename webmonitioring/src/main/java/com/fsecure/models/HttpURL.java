package com.fsecure.models;

public class HttpURL {
	
	private String url;
	private String page_content;
	private int time_out_setting;
	private int frequency_time;
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPage_content() {
		return page_content;
	}

	public void setPage_content(String page_content) {
		this.page_content = page_content;
	}

	public Integer getTime_out_setting() {
		return time_out_setting;
	}

	public void setTime_out_setting(Integer time_out_setting) {
		this.time_out_setting = time_out_setting;
	}

	public Integer getFrequency_time() {
		return frequency_time;
	}

	public void setFrequency_time(Integer frequency_time) {
		this.frequency_time = frequency_time;
	}

	@Override
	public String toString() {
		return "HttpURL [url=" + url + ", page_content=" + page_content + ", time_out_setting=" + time_out_setting
				+ ", frequency_time=" + frequency_time + "]";
	}

	
}
