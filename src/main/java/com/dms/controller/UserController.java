package com.dms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.CourtMaster;
import com.dms.model.CourtUserMapping;
import com.dms.model.Lookup;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CourtMasterService;
import com.dms.service.LookupService;
import com.dms.service.UserRoleService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.UserMstrValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	
	@Autowired
	private LookupService lookUpService;
	
	@Autowired
	private UserRoleService urService;
	
	@Autowired
	private UserMstrValidator umvalidation;
	
	@Autowired
	private CourtMasterService courtMasterService;
	
	private List<Long> childfolders=new ArrayList<Long>();
	
	HttpSession session;
	
	private GlobalFunction globalfunction;
	
	public UserController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/user/manage")
	public String manage(Model model) {
		return "/user/manage";
	}
	
	@RequestMapping(value = "/user/edit")
	public String edit(Model model) {
		return "/views/editProfile";
	}
	
	@RequestMapping(value = "/user/getallusers")
	public @ResponseBody String getUserData(HttpSession session) {
		String jsonData = null;
		User user=new User();
    	user=(User) session.getAttribute("USER");
    	List<Long> userids= urService.getSytemAdminUsers();
    	
    	List<User> users=new ArrayList<User>();
    	
    	if(userids.contains(user.getUm_id())){
    		// if logged in user is system admin then get all users
    		users=userservice.getAllByRole();
    	}
		
    	if(users != null){
		jsonData = globalfunction.convert_to_json(users);
		}
		
		System.out.println(jsonData);
		return jsonData;
	}
	
	@RequestMapping(value="/user/getmasters",method=RequestMethod.GET)
	public @ResponseBody String getrolelist(Model model)
	{
		String jsonData=null;
		String roleData="";
		String designationData="";
		String departmentData="";
		
		List<Lookup> getrole=lookUpService.getAll("DMS_ROLE");
		
		if(getrole!=null)
		{
			roleData=globalfunction.convert_to_json(getrole);
		}
		
		List<Lookup> getdesignationlist=lookUpService.getAll("DESIGNTION");
		
		if(getdesignationlist!=null)
		{
			designationData=globalfunction.convert_to_json(getdesignationlist);
		}
		
		List<Lookup> getdepartmentlist=lookUpService.getAll("DEPARTMENT");
		
		if(getdepartmentlist!=null)
		{
			departmentData=globalfunction.convert_to_json(getdepartmentlist);
		}
		List<CourtMaster> courts=courtMasterService.getCourtLists();
		String courtsData=globalfunction.convert_to_json(courts);
		
		jsonData = "{\"roleData\":"+roleData+",\"designationData\":"+designationData+",\"departmentData\":"+departmentData+",\"courtsData\":"+courtsData+"}";
		System.out.println(jsonData);
		return jsonData;

	}
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)	
	public @ResponseBody String create(@RequestBody User um,HttpSession session) {	
		String jsonData="";
		ActionResponse<User> response = umvalidation.doValidation(um);		
		User temp_um = um;
		User user = (User) session.getAttribute("USER");
		
		if(response.getResponse() == "TRUE"){					
			//um.setCr_date(new Date());
			
			um.setPassword(globalfunction.md5encryption(um.getPassword()));
			
			um.setCr_by(user.getUm_id());
			um.setCr_date(new Date());
			 
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
			um.setUm_pass_validity_date(cal.getTime());
	     	 
			um = userservice.save(um);	
			if(um.getUm_id()!=null){
				UserRole ur = urService.getByUserId(um.getUm_id());			
				if(ur.getUr_id()==null){
					ur.setUr_um_mid(um.getUm_id());
					ur.setUr_role_id(temp_um.getUm_role_id());
					ur.setUr_cr_date(new Date());
					ur.setUr_rec_status(1);
				}else{
					ur.setUr_role_id(temp_um.getUm_role_id());
					ur.setUr_mod_date(new Date());
				}
				
				CourtUserMapping cum=courtMasterService.getCourtMapping(um.getUm_id());
				
					cum.setCum_court_mid(temp_um.getCourt_id());
					cum.setCum_user_mid(um.getUm_id());				
				courtMasterService.saveMapping(cum);
				urService.save(ur);
			}
			um=userservice.getUser(um.getUm_id());
			response.setModelData(um);
		}	
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/user/update", method=RequestMethod.POST)
	public @ResponseBody String update(@RequestBody User um,HttpSession session)
	{
		String jsonData="";
		ActionResponse<User> response = umvalidation.doValidation(um);
		User user = (User) session.getAttribute("USER");
		if(response.getResponse()=="TRUE")
		{
			 
			/*if(um.getPassword().equals(user1.getPassword()))
			{
				um.setUm_department_id(um.getUm_department_id());
				um.setUm_fullname(um.getUm_fullname());
				um.setRec_status(um.getRec_status());
				//um.setUm_pass_validity_date();
			}
			else
			{*/
			//	um.setPassword(globalfunction.md5encryption(um.getPassword()));
				um.setUm_fullname(um.getUm_fullname());
				
			 um=userservice.save(um);
			response.setModelData(um);
			//um.setPassword(globalfunction.md5encryption(um.getPassword()));
		}
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
		
	}
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
	public @ResponseBody String changePassword(@RequestBody User u,HttpSession session) 
	{ 
		String jsonData = null;
		User user=(User) session.getAttribute("USER");
		System.out.println(user.getUm_fullname());
		ActionResponse<User> response =new ActionResponse<User>() ;
		response = umvalidation.doValidationForPassword(u);
		
		String pass=u.getPassword();
		//System.out.println(u.getPassword()+"pass");
		if (response.getResponse() == "TRUE") {
			user.setPassword(globalfunction.md5encryption(pass));
			System.out.println(pass);
			userservice.update(user);
		}
		
		jsonData = globalfunction.convert_to_json(response);
		System.out.println("last "+response.getResponse());
	    return jsonData;
	}		
}
