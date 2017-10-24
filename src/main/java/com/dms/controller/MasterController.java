package com.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.CaseType;
import com.dms.model.IndexField;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/master")
public class MasterController {

	static int seq = 0;
	private GlobalFunction globalfunction;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private MasterService masterService;
	
	public MasterController() {
		// TODO Auto-generated constructor stub
		globalfunction = new GlobalFunction();
	}

	@RequestMapping(value = "/getcasetypes", method = RequestMethod.GET)
	public @ResponseBody String getCaseTypes() {
		String jsonData="";
		ActionResponse<CaseType> response=new ActionResponse<>();
		List<CaseType> caseTypes=masterService.getCaseTypes();
		response.setModelList(caseTypes);
		
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	
	@RequestMapping(value = "/getindexfields", method = RequestMethod.GET)
	public @ResponseBody String getIndexFields() {
		String jsonData="";
		ActionResponse<IndexField> response=new ActionResponse<>();
		List<IndexField> indexFields=masterService.getIndexFields();
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
}
