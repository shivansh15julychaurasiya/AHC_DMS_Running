package com.dms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.Case;
import com.dms.model.CaseFileDetail;
import com.dms.model.CaseType;
import com.dms.model.CauseList;
import com.dms.model.CauseListType;
import com.dms.model.CourtMaster;
import com.dms.model.CourtUserMapping;
import com.dms.model.Lookup;
import com.dms.model.MetaData;
import com.dms.model.Rec;
import com.dms.model.Record;
import com.dms.model.SubDocument;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CaseFileDetailService;
import com.dms.service.CauseListService;
import com.dms.service.CourtMasterService;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.service.SubDocumentService;
import com.dms.utility.GlobalFunction;
import com.lowagie.text.DocumentException;

@Controller
public class CauseListController 
{
	@Autowired
	CauseListService causeListService;
	@Autowired
	ServletContext context;
	
	@Autowired
	CourtMasterService courtService;
	
	@Autowired
	LookupService lookupService;
	
	@Autowired
	private CaseFileDetailService caseFileDetailService;
	
	@Autowired
	MasterService masterService;
	@Autowired
	CourtMasterService courtMasterService;
	
	@Autowired
	private SubDocumentService subDocumentService;
	
	private GlobalFunction globalfunction;
	
	public CauseListController()
	{
		globalfunction=new GlobalFunction();
	}
	@RequestMapping(value = "/causelist/home", method = RequestMethod.GET)
	public String adminHome() {
		
		return "/causelist/home";

	}
	@RequestMapping(value = "/causelist/manage", method = RequestMethod.GET)
	public String manage()
	{
		return "/causelist/manage";
	}
	@RequestMapping(value = "/causelist/type/{typeId}", method = RequestMethod.GET)
	public String manage(Model model,@PathVariable Long typeId,HttpSession session)
	{
		User u=(User) session.getAttribute("USER");
		
		CauseListType listType=causeListService.findCauseListType(typeId);
		model.addAttribute("type_id", typeId);
		model.addAttribute("listType", listType);
//		Lookup extractPath=lookupService.getLookUpObject("CAUSELIST_PATH");
//		CourtUserMapping mapping=courtService.getCourtMappingByUserId(u.getUm_id());
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//		String currentDate = formatter.format(new Date());
//		String listPath=extractPath.getLk_longname()+File.separator+currentDate+File.separator+mapping.getCourtMaster().getCm_name()+File.separator+listType.getClt_description()+".html";
//		model.addAttribute("listPath", listPath);
		
//		String uploadPath = context.getRealPath("");		
//		File dest = new File(uploadPath+File.separator+"uploads"+File.separator+"causelist.txt");
//
//		try {
//		    FileUtils.copyFile(source, dest);
//		} 
//		catch (IOException e) {
//		    e.printStackTrace();
//		}

		return "/causelist/typewise";
	}
	
	@RequestMapping(value = "/causelist/getCauseList", method = RequestMethod.POST)
	public @ResponseBody String getCauseList(@RequestBody CauseList causeList,HttpSession session)
	{
		String jsonData="";
		ActionResponse<CauseList> response= new ActionResponse<CauseList>();
		User user=(User) session.getAttribute("USER");
		List<UserRole> userroles=user.getUserroles();
		String userRole="";
		for(UserRole userrole:userroles){
			userRole=userrole.getLk().getLk_longname();
		}
		if(userRole.equals("ECOURT")){
			CourtUserMapping mapping=courtMasterService.getCourtMapping(user.getUm_id());
			causeList.setCl_court_no(mapping.getCum_court_mid());
		}
		
	    List<CauseList> list=causeListService.getList(causeList);
	    response.setResponse("true");
	    response.setModelList(list);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	@RequestMapping(value = "/causelist/create", method = RequestMethod.POST)
	public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session) throws DocumentException 
	{
		ActionResponse<CauseList> response = new ActionResponse();
		String jsonData="";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = formatter.format(date);
		Lookup causelistPath=lookupService.getLookUpObject("CAUSELIST_PATH");
		MultipartFile mpf = null;
    	Iterator<String> itr = request.getFileNames();
    	String xmlPath="";
    	List<CourtMaster> cList=courtService.getCourtLists();
    	Map<Integer,Integer> courtsList=new HashMap<Integer,Integer>();
    	
    	for(CourtMaster cm:cList ){
    		courtsList.put(cm.getCm_value(),cm.getCm_id());
    	}
    	
    	List<CaseType> caseTypes= masterService.getCaseTypes();
    	Map<String,Long> caseTypeList=new HashMap<String,Long>();
    	
    	for(CaseType ct:caseTypes){
    		caseTypeList.put(ct.getCt_label(),ct.getCt_id());
    	}
    	List<CauseListType> causeListType=causeListService.getCauseListTypes();
    	Map<String,Long> causeListMap=new HashMap<String,Long>();
    	
    	for(CauseListType clt:causeListType){
    		causeListMap.put(clt.getClt_name(),clt.getClt_id());
    	}
    	
    	while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			
			xmlPath=causelistPath.getLk_longname() + File.separator +"CauseList_"+currentDate+".xml";

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(xmlPath));				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		File file = new File(xmlPath);
		
		int count = 0;
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			Record record = (Record) jaxbUnmarshaller.unmarshal(file); 
			
			List<Rec> list = record.getRec();
			
			for(Rec rec:list)
			{
				Case cases =rec.getCases();
				
				try{
				
				//case_type=causeListService.findCaseType(cases.getType(), 8L);
				
					CauseList cl=new CauseList();
					
					String sDate=rec.getDol();  
				    date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
					
					cl.setCl_case_type_mid(caseTypeList.get(cases.getType()));
					cl.setCl_case_no(cases.getNo());
					cl.setCl_case_year(cases.getYear());
					cl.setCl_court_no(courtsList.get(rec.getCourtno()));
					cl.setCl_serial_no(rec.getSlno());
					cl.setCl_list_type_mid(causeListMap.get(rec.getLtype()));
					cl.setCl_first_petitioner(rec.getPet());
					cl.setCl_first_respondent(rec.getRes());
					cl.setCl_petitioner_council(rec.getPetadv());
					cl.setCl_respondent_council(rec.getResadv());
					cl.setCl_ano(rec.getAno());
					cl.setCl_ayr(rec.getAyr());
					cl.setCl_applawp(rec.getApplawp());
					cl.setCl_applawr(rec.getApplawr());
					cl.setCl_stage(rec.getStage());
					cl.setCl_dol(date);
					
					cl=causeListService.save(cl);
					CaseFileDetail casefile=new CaseFileDetail();
					casefile.setFd_case_no(cl.getCl_case_no());
					casefile.setFd_case_type(cl.getCl_case_type_mid());
					casefile.setFd_case_year(cl.getCl_case_year());
					
					CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFile(casefile);
					if(caseFileDetail==null){
						count++;
					}else{
					cl.setCl_fd_mid(caseFileDetail.getFd_id());
					causeListService.save(cl);
					}
				
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count>0){
		response.setData(count+" no. of case files not found");
		response.setResponse("FALSE");
		}else{
			response.setResponse("TRUE");
		}
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/causelist/getCauseListTypes", method = RequestMethod.GET)
	public @ResponseBody String getCauseListTypes() 
	{
		ActionResponse<CauseListType> response=new ActionResponse<CauseListType>();
		String jsonData="";
		List<CauseListType> types=causeListService.getCauseListTypes();
		response.setData("TRUE");
		response.setModelList(types);
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/causelist/getCourtList", method = RequestMethod.GET)
	public @ResponseBody String getCourtList() 
	{
		ActionResponse<CourtMaster> response=new ActionResponse<CourtMaster>();
		String jsonData="";
		List<CourtMaster> courtList=courtService.getCourtLists();
		response.setData("TRUE");
		response.setModelList(courtList);		
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	/*@RequestMapping(value = "/causelist/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request,HttpSession session){
		String jsonData="";
		ActionResponse<CauseList> response=new ActionResponse<CauseList>();
		
		CauseList causeList=new CauseList();
		Lookup lkZipPath=lookupService.getLookUpObject("CAUSELIST_ZIP");
		Lookup extractPath=lookupService.getLookUpObject("CAUSELIST_PATH");
		String zipFilePath = lkZipPath.getLk_longname();
	    String destDirectory = extractPath.getLk_longname();
	    UnzipUtility unzipper = new UnzipUtility();
	    MultipartFile mpf = null;
	    
	    
    	Iterator<String> itr = request.getFileNames();
    	String zipFullPath="";
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol =request.getParameter("cl_date");
		destDirectory=destDirectory+File.separator+cl_dol;
		File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
		if (!destDir.exists()) {
            destDir.mkdir();
        }
		
		System.out.println("date="+cl_dol);
	    while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			
			zipFullPath=zipFilePath + File.separator +cl_dol+".zip";

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(zipFullPath));				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	    
        try {
            unzipper.unzip(zipFullPath, destDirectory);
            
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
   	 	Date judgementDate = new Date();
		try {
			judgementDate = formatter.parse(request.getParameter("cl_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        causeList.setCl_date(judgementDate);
		
		causeList.setCl_created(new Date());
		
		causeList=causeListService.save(causeList);    
		
		response.setData("TRUE");
		response.setModelData(causeList);		
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}*/
	@RequestMapping(value = "causelist/updatepriority/{id}", method = RequestMethod.GET)
	public @ResponseBody String updatePriority(@PathVariable("id") Long cl_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<CauseList> response=new ActionResponse<>();
		CauseList causeList=causeListService.getByPk(cl_id);
		Integer count=causeListService.getPrioritywise(causeList.getCl_fd_mid());
		if(count==null){
			count=0;
		}
		count=count+1;
		causeList.setCl_sequence(count);
		causeListService.save(causeList);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value="causelist/downloadfiles",method=RequestMethod.GET)
	public void downloadCaseFile(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{	
		CauseList causeList=new CauseList();
		Integer courtno=Integer.parseInt(request.getParameter("courtno"));
		String date=request.getParameter("date");
		String expectedPattern = "dd-MM-yyyy";
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
	    Date cl_dol = new Date();
		try {
			cl_dol = formatter.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		causeList.setCl_court_no(courtno);
		causeList.setCl_dol(cl_dol);
		CourtMaster courtMaster=courtMasterService.getCourt(causeList.getCl_court_no());
		
		
		List<CauseList> list=causeListService.getList(causeList);
		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		//Lookup lookUpZip=lookupService.getLookUpObject("ZIP_DOWNLOAD_PATH");
		Lookup lookUpZip=lookupService.getLookUpObject("CAUSELIST_DOWNLOAD_PATH");
		
		String dest_folder=lookUpZip.getLk_longname()+File.separator+courtMaster.getCm_name()+"_"+date;
		File destFolder = new File(dest_folder);
		
		if(!destFolder.exists())
		{
			destFolder.mkdir();
		}
		
		for(CauseList cause:list){
			List<SubDocument> subDocuments=subDocumentService.getSubDocuments(cause.getCl_fd_mid());
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(cause.getCl_fd_mid());
			String basePath=lookupRepo.getLk_longname()+caseFileDetail.getCaseType().getCt_label();
			String casefile=destFolder+File.separator+caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year()+".pdf";
			String casefilebk=destFolder+File.separator+caseFileDetail.getCaseType().getCt_label()+"_"+caseFileDetail.getFd_case_no()+"_"+caseFileDetail.getFd_case_year()+".pdf";
			File f=new File(casefile);
			globalfunction.merge(casefile,casefilebk,subDocuments,basePath);
		}
		
		/*List<SubDocument> subDocuments=subDocumentService.getSubDocuments(caseFileId);
		
		
		
		CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(caseFileId);
		String dest_folder=lookUpZip.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year();
		File destFolder = new File(dest_folder);
		
		if(!destFolder.exists())
		{
			destFolder.mkdir();
		}
		for(SubDocument subDocument:subDocuments){
			String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			String destPath=dest_folder+File.separator+subDocument.getSd_document_name()+".pdf";
			File source=new File(srcPath);
			File dest=new File(destPath);
			try {
				FileUtils.copyFile(source.getAbsoluteFile(), dest.getAbsoluteFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		 try 
			{
	        	globalfunction.zipFolder(destFolder.getAbsolutePath(),destFolder.getAbsolutePath()+".zip");
			} 
			catch (Exception e) {
			    e.printStackTrace();
			}
	        
	        // to download file
	        try
	        {
	        response.setContentType("application/zip");  
	        PrintWriter out = response.getWriter();  
	        String filename = destFolder.getName()+".zip";   
	        String filepath = destFolder.getAbsolutePath()+".zip";
	        File zipFile=new File(dest_folder+".zip");
	        
	        response.setContentType("APPLICATION/OCTET-STREAM");   
	        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
	          
	        FileInputStream fileInputStream = new FileInputStream(filepath);  
	                    
	        int i;   
	        while ((i=fileInputStream.read()) != -1) {  
	        out.write(i);   
	        }   
	        fileInputStream.close();   
	        out.close();
	        FileUtils.deleteDirectory(destFolder); 
	        zipFile.delete();
	        }catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
	    }
}
