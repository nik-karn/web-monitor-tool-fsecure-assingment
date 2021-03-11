package com.fsecure.models;

import java.util.Date;

public class LogDataFormat {
	
	private String requestTime;
	private String url;
	private String pageContent;
	private int statusCode;
	private String responseTime;
	private boolean isPageContentFound;
	
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPageContent() {
		return pageContent;
	}
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public boolean isPageContentFound() {
		return isPageContentFound;
	}
	public void setPageContentFound(boolean isPageContentFound) {
		this.isPageContentFound = isPageContentFound;
	}
	@Override
	public String toString() {
		return "Web Request Time=" + requestTime + ", Request URL=" + url + ", Page Content to verify=" + pageContent
				+ ", Status Code=" + statusCode + ", Response Time=" + responseTime + ", Page Content Found="
				+ isPageContentFound;
	}
	
	
	
	
	
	
}
