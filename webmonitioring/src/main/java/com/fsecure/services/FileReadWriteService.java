package com.fsecure.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsecure.config.ConfigClass;
import com.fsecure.models.LogDataFormat;

@Service
public class FileReadWriteService {
	

	private  static ResourceLoader resourceLoader=new DefaultResourceLoader();
	
	
	public static void  writeDataToFile(LogDataFormat logDataFormat) {
		ObjectMapper mapper = new ObjectMapper();
		try {  
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ConfigClass.PATH_LOGGED_MONITORED_DATA, true)));
			out.write(mapper.writeValueAsString(logDataFormat)+",\n");
			out.flush();
			out.close();
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}

	public  static JSONArray readDataFromFile() {
		StringBuffer data=new StringBuffer("[");
		JSONArray jsonArr = new JSONArray();
		JSONArray jsonArrMax100Result = new JSONArray();
		
		try {
			data.append(readFile(ConfigClass.PATH_LOGGED_MONITORED_DATA, StandardCharsets.UTF_8));
			data.deleteCharAt(data.length()-1);  
			data.append("]");	
			jsonArr = new JSONArray(data.toString().trim());
			if(jsonArr.length()>100) {
				for(int lastIndex=Math.abs(jsonArr.length()-1);lastIndex>=0 && lastIndex>=Math.abs(jsonArr.length()-100);lastIndex--) {
					jsonArrMax100Result.put(jsonArr.get(lastIndex));
				}
			}else {
				for(int lastIndex=Math.abs(jsonArr.length()-1);lastIndex>=0 && lastIndex<100;lastIndex--) {
					jsonArrMax100Result.put(jsonArr.get(lastIndex));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonArrMax100Result;
	}
	
	public  static List<LogDataFormat> readDataFromFileForUI(){
		ObjectMapper mapper = new ObjectMapper();
		JSONArray jsonArray=readDataFromFile();
		List<LogDataFormat> data=null;
		try {
			data = mapper.readValue(jsonArray.toString(), new TypeReference<List<LogDataFormat>>(){});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return data;
	}
	public static void removeOldDataFromFile() {
		
		try {
			double fileSize=Files.size(Paths.get(ConfigClass.PATH_LOGGED_MONITORED_DATA));
			if( (fileSize/ 1024)>ConfigClass.MAX_FILE_SIZE_FOR_MONITOR_DATA) {
				JSONArray jsonArray=readDataFromFile();
				PrintWriter out;
				out = new PrintWriter(new BufferedWriter(new FileWriter(ConfigClass.PATH_LOGGED_MONITORED_DATA, false)));
				for(int counter=jsonArray.length()-1;counter>=0;counter--) {
					out.write(jsonArray.get(counter)+",\n");
				}
				out.flush();
				out.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(String path, Charset encoding) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(path), encoding);
        return String.join(System.lineSeparator(), lines);
    }
}

