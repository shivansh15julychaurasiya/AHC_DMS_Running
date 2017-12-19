package com.dms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.ApplicationTypes;
import com.dms.model.CaseType;
import com.dms.model.IndexField;
import com.dms.model.User;
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
	public @ResponseBody String getCaseTypes(HttpSession session) {
		User user=(User) session.getAttribute("USER");
		String role=user.getUserroles().get(0).getLk().getLk_longname();
		String jsonData="";
		ActionResponse<CaseType> response=new ActionResponse<>();
		List<CaseType> caseTypes=new ArrayList<>();
		if(role.equals("Review_Officer"))
		{
			caseTypes=masterService.getCaseTypesByUser(user.getUm_id());
		}
		else{
			caseTypes=masterService.getCaseTypes();
		}
		response.setModelList(caseTypes);
		
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	
	@RequestMapping(value = "/getindexfields", method = RequestMethod.GET)
	public @ResponseBody String getIndexFields() {
		String jsonData="";
		ActionResponse<IndexField> response=new ActionResponse<>();
		List<IndexField> indexFields=masterService.getSelectecdIndexFields();
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
	@RequestMapping(value = "/getapplications", method = RequestMethod.GET)
	public @ResponseBody String getOrdersList() {
		String jsonData="";
		ActionResponse<ApplicationTypes> response=new ActionResponse<>();
		List<ApplicationTypes> indexFields=masterService.getApplicationsByType("ORDER");
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	@RequestMapping(value = "/getapplications/{id}", method = RequestMethod.GET)
	public @ResponseBody String getApplications(@PathVariable("id") Long if_id,Model model) {
		String jsonData="";
		ActionResponse<ApplicationTypes> response=new ActionResponse<>();
		IndexField index_field=masterService.getIndexField(if_id);
		List<ApplicationTypes> indexFields=masterService.getApplicationsByType(index_field.getIf_label());
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
}
