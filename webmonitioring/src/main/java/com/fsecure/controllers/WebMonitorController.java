package com.fsecure.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsecure.services.FileReadWriteService;

@Controller
public class WebMonitorController {

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("result",FileReadWriteService.readDataFromFileForUI());
		return "index";
	}

	

	@ResponseBody
	@RequestMapping(value="/monitor", produces="application/json",method = RequestMethod.GET)
	public String getMonitorData() {
		return FileReadWriteService.readDataFromFile().toString();
	}

}
