package com.dms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.CauseList;
import com.dms.model.CourtUserMapping;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CauseListService;
import com.dms.service.CourtMasterService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/ecourt")
public class EcourtController {

	private GlobalFunction globalfunction;
	
	@Autowired
	CauseListService causeListService;
	
	@Autowired
	CourtMasterService courtMasterService;
	
	public EcourtController() {
		// TODO Auto-generated constructor stub
		globalfunction = new GlobalFunction();
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminHome() {
		
		return "/ecourt/home";

	}
	@RequestMapping(value="/getreport",method = RequestMethod.GET)
	public  @ResponseBody String getDashBoardReport(HttpSession session,HttpServletRequest request){
	
		String result = null;
	
		ActionResponse<CauseList> response = new ActionResponse<CauseList>();
		CauseList causeList=new CauseList();
		User user=(User) session.getAttribute("USER");
		List<UserRole> userroles=user.getUserroles();
		String userRole="";
		for(UserRole userrole:userroles){
			userRole=userrole.getLk().getLk_longname();
		}
		List list=new ArrayList();
		
	
		if(userRole.equals("ECOURT")){
			CourtUserMapping mapping=courtMasterService.getCourtMapping(user.getUm_id());
			
			causeList.setCl_court_no(mapping.getCum_court_mid());
			
			list=causeListService.getListByType(causeList);
		}else{
			list=causeListService.getListByType(causeList);	
		}
		List<CauseList> cList=new ArrayList<>();
		Integer dailycount=0;
		for(int i=0;i<list.size();i++){
			CauseList c=new CauseList();
			Object[] row1 = (Object[]) list.get(i);
			if(row1[1].toString().equals("1") || row1[1].toString().equals("2") || row1[1].toString().equals("3")){
				c.setCl_list_type_mid(1L);
				dailycount+=Integer.parseInt(row1[0].toString());
				c.setCount(dailycount);
			}
			if(row1[1].toString().equals("4")){
				c.setCl_list_type_mid(4L);
				c.setCount(Integer.parseInt(row1[0].toString()));
			}
			if(row1[1].toString().equals("5")){
				c.setCl_list_type_mid(5L);
				c.setCount(Integer.parseInt(row1[0].toString()));
			}
			if(row1[1].toString().equals("6")){
				c.setCl_list_type_mid(6L);
				c.setCount(Integer.parseInt(row1[0].toString()));
			}
			if(row1[1].toString().equals("7")){
				c.setCl_list_type_mid(7L);
				c.setCount(Integer.parseInt(row1[0].toString()));
			}
			cList.add(c);
		}
		response.setModelList(cList);
		
		if (list != null) {
			response.setResponse("TRUE");
			result = globalfunction.convert_to_json(response);
		} else {
			response.setResponse("FALSE");
		}
	
		
		return result;
	
	}

}
