package com.fsecure.services;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.fsecure.models.HttpURL;
import com.fsecure.models.LogDataFormat;

@Component
public class HttpCallService {

	private Executor executor=Executors.newFixedThreadPool(10);
	
	
	public void monitiorWebUrls(List<HttpURL> listOfUrl) {
		//define all async requests and give them to injected Executor
		List<GetRequestTask> tasks = new ArrayList<GetRequestTask>();		

		for(HttpURL httpURL:listOfUrl) {
			tasks.add(new GetRequestTask(httpURL.getUrl(),httpURL.getPage_content(), this.executor));
		}

		while(!tasks.isEmpty()) {
			for(Iterator<GetRequestTask> it = tasks.iterator(); it.hasNext();) {
				GetRequestTask task = it.next();
				if(task.isDone()) {
					it.remove();
				}
			}
			if(!tasks.isEmpty())
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		

	}

	class GetRequestTask {
		private GetRequestWork work;
		private FutureTask<String> task;
		public GetRequestTask(String url,String pageContent, Executor executor) {
			this.work = new GetRequestWork(url,pageContent);
			this.task = new FutureTask<String>(work);
			executor.execute(this.task);
		}
		public String getRequest() {
			return this.work.getUrl();
		}
		public boolean isDone() {
			return this.task.isDone();
		}
		public String getResponse() {
			try {
				return this.task.get();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	class GetRequestWork implements Callable<String> {
		private final String url;
		private final String pageContent;

		public GetRequestWork(String url,String pageContent) {
			this.url = url;
			this.pageContent=pageContent;
		}
		public String getUrl() {
			return this.url;
		}
		public String call() throws Exception {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					builder.build());
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
					sslsf).build();

			HttpGet httpGet = new HttpGet(getUrl());
			StopWatch watch=new StopWatch();
			LogDataFormat logDataFormat=new LogDataFormat();
			Date date = new Date();
			CloseableHttpResponse response;
			logDataFormat.setRequestTime(new Timestamp(date.getTime()).toString());

			try {
				watch.start();
				response= httpclient.execute(httpGet);
				watch.stop();

				logDataFormat.setPageContent(pageContent);
				logDataFormat.setPageContentFound(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8).contains(pageContent));
				logDataFormat.setResponseTime(Double.toString(watch.getTotalTimeSeconds()));
				logDataFormat.setUrl(url);
				logDataFormat.setStatusCode(response.getStatusLine().getStatusCode());

				FileReadWriteService.writeDataToFile(logDataFormat);
				return response.toString();
			} catch (UnknownHostException e) {
				watch.stop();

				logDataFormat.setPageContent(pageContent);
				logDataFormat.setPageContentFound(false);
				logDataFormat.setResponseTime(Double.toString(watch.getTotalTimeSeconds()));
				logDataFormat.setUrl(url);
				logDataFormat.setStatusCode(503);

				FileReadWriteService.writeDataToFile(logDataFormat);
				return "";
			}
		}
		
	}
}

