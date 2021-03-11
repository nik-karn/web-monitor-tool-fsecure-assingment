package com.fsecure.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fsecure.webmonitioring.WebmonitioringApplication;

public class ConfigClass {
	public  static final String PATH_URL_TO_BE_MONITORED_DATA_FILE="urldata.json";
	public  static final String PATH_LOGGED_MONITORED_DATA="/data_blue.json";
	public  static final String PATH_LOGGER_PROPERTIES="logger.properties";
	public  static final int MAX_FILE_SIZE_FOR_MONITOR_DATA=100;

	
	public  static long jobMonitorFrequencyTime=10000L;
	public static synchronized void setJobMonitorFrequencyTime(long inputTime) {
		jobMonitorFrequencyTime=inputTime;
    }
	public static long getJobMonitorFrequencyTime() {
		return jobMonitorFrequencyTime;
    }
	
	
	
}
