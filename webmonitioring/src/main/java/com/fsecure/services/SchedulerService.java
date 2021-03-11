package com.fsecure.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsecure.config.ConfigClass;
import com.fsecure.models.HttpURL;


@Configuration
@EnableScheduling
public class SchedulerService {

	ObjectMapper mapper = new ObjectMapper();
	HttpCallService httpCallService=new HttpCallService();
	private  static ResourceLoader resourceLoader=new DefaultResourceLoader();

	
	@Scheduled(fixedDelayString  = "${job.monitor.frequency.time.in_mili_sec}")
	void webMonitor() {
		try {
			Resource resource = resourceLoader.getResource("classpath:"+ConfigClass.PATH_URL_TO_BE_MONITORED_DATA_FILE);
			InputStream file=resource.getInputStream();
			HttpURL[] listOfMonitoredUrl = mapper.readValue(file, HttpURL[].class);
			httpCallService.monitiorWebUrls(Arrays.asList(listOfMonitoredUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Scheduled(fixedDelayString  = "${frequency.to.remove.old.data}")
	void removeOldDataFormJsonFile() {

		//FileReadWriteService.removeOldDataFromFile();

	}


}
