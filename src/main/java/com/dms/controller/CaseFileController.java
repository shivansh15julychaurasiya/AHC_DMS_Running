package com.dms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.CaseFileDetail;
import com.dms.model.CaseType;
import com.dms.model.DownloadFile;
import com.dms.model.DownloadModel;
import com.dms.model.DownloadReport;
import com.dms.model.ImpugnedOrder;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.MetaData;
import com.dms.model.OrderReport;
import com.dms.model.Petitioner;
import com.dms.model.PetitionerCounsel;
import com.dms.model.Respondent;
import com.dms.model.RespondentCounsel;
import com.dms.model.SubDocument;
import com.dms.model.User;
import com.dms.service.CaseFileDetailService;
import com.dms.service.CasetypeService;
import com.dms.service.DownloadFileService;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.service.OrderReportService;
import com.dms.service.SubDocumentService;
import com.dms.utility.GlobalFunction;
import com.dms.utility.PDFCreator;
import com.dms.utility.PDFMerger;
import com.efiling.model.EfilingCaseFileDetail;
import com.itextpdf.text.pdf.PdfReader;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/casefile")
public class CaseFileController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private CaseFileDetailService caseFileDetailService;
	
	@Autowired
	private OrderReportService orderReportService;
	
	@Autowired
	private SubDocumentService subDocumentService;
	
	@Autowired
	private CasetypeService casetypeService;
	
	@Autowired
	private DownloadFileService downloadService;
	
	private GlobalFunction globalfunction;	
	
	public CaseFileController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String Manage() {

		return "/casefile/manage";
	}
	@RequestMapping(value = "/viewdetail/{id}", method = RequestMethod.GET)
	public  String viewDetail(@PathVariable("id") Long docId,Model model) {
		model.addAttribute("doc_id",docId);
		return "/casefile/viewdetail";
	}
	@RequestMapping(value = "/downloadlist/{id}", method = RequestMethod.GET)
	public  String downloadlist(@PathVariable("id") Long docId,Model model) {
		model.addAttribute("doc_id",docId);
		return "/casefile/downloadlist";
	}
	@RequestMapping(value = "/showfile/{id}", method = RequestMethod.GET)
	public  String showfile(@PathVariable("id") Long docId,Model model) {
		SubDocument subDocument = subDocumentService.getByPK(docId);
		String returnview="/casefile/showfile";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
			
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(subDocument.getSd_fd_mid());
			model.addAttribute("document_name", subDocument.getSd_document_name());
			System.out.println("filename="+subDocument.getSd_document_name());
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			
			File source = new File(srcPath);	
	
			String uploadPath = context.getRealPath("");		
			File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");
	
			try {
			    FileUtils.copyFile(source, dest);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			}
		}
		

		return returnview;
	}
	@RequestMapping(value = "/getCaseFileList", method = RequestMethod.POST)
	public @ResponseBody String getCaseList(@RequestBody CaseFileDetail casefile,HttpSession session) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> casefileList=caseFileDetailService.getCaseFiles(casefile);
		response.setResponse("TRUE");
		response.setModelList(casefileList);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/uploadJudgement", method = RequestMethod.POST)
	public @ResponseBody String uploadJudgement(MultipartHttpServletRequest request,HttpSession session) throws DocumentException 
	{
		ActionResponse<SubDocument> response = new ActionResponse();
		User u=(User) session.getAttribute("USER");
		String jsonData="";
		Lookup lookup=lookupService.getLookUpObject("REPOSITORYPATH");
		Long caseFileId=Long.parseLong(request.getParameter("sd_fd_mid"), 10);
		Long indexFieldId=Long.parseLong(request.getParameter("if_id"), 10);
		Integer at_id= Integer.parseInt(request.getParameter("at_id"), 10);
		String ord_remark=request.getParameter("ord_remark");
		String order_date=request.getParameter("sd_submitted_date");
		Integer sd_document_no = null;
		Integer sd_document_year = null;
		try {
			sd_document_no = Integer.parseInt(request.getParameter("sd_document_no"));
			sd_document_year = Integer.parseInt(request.getParameter("sd_document_year"));
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Date orderDate=new Date();
		
			try {
				orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		MultipartFile mpf = null;
    	Iterator<String> itr = request.getFileNames();
    	String newfilepath="";
    	CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(caseFileId);
		IndexField indexField=masterService.getIndexField(indexFieldId);
		Integer count=subDocumentService.getCount(caseFileId);
		count=count+1;
    	while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			String filename=caseFileDetail.getFd_document_name()+"_"+indexField.getIf_type_code()+"_"+count;
			newfilepath=lookup.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+indexField.getIf_name()+File.separator+filename+".pdf";
			SubDocument subDocument=new SubDocument();
			subDocument.setSd_cr_by(u.getUm_id());
			subDocument.setSd_cr_date(new Date());
			subDocument.setSd_fd_mid(caseFileId);
			subDocument.setSd_if_mid(indexFieldId);
			subDocument.setSd_version(1);
			subDocument.setSd_document_name(filename);
			subDocument.setSd_document_id(at_id);
			subDocument.setSd_submitted_date(orderDate);
			subDocument.setSd_rec_status(1);
			subDocument.setSd_minor_sequence(count);
			subDocument.setSd_document_no(sd_document_no);
			subDocument.setSd_document_year(sd_document_year);
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newfilepath));
				File source=new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
		       	subDocument.setSd_no_of_pages(no_of_pages);
				
		       	subDocument=subDocumentService.save(subDocument);
		       	reader.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			if(at_id==100003 && (ord_remark != null && !ord_remark.isEmpty())){
				OrderReport or=new OrderReport();
	    		or.setOrd_created(new Date());
	    		or.setOrd_remark(ord_remark);
	    		or.setOrd_fd_mid(caseFileId);
	    		or.setOrd_sd_mid(subDocument.getSd_id());
	    		or.setOrd_created_by(u.getUm_id());
	    		orderReportService.save(or);	
			}
		}
    	
    	response.setResponse("TRUE");
    	jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value = "/addreportdata", method = RequestMethod.POST)
	public @ResponseBody String addReportData(HttpServletRequest request,HttpSession session) throws DocumentException 
	{
		ActionResponse<SubDocument> response = new ActionResponse();
		User u=(User) session.getAttribute("USER");
		String jsonData="";
		Long caseFileId=Long.parseLong(request.getParameter("sd_fd_mid"), 10);
		Long indexFieldId=39L;
		
		String ord_remark=request.getParameter("ord_remark");
    	if(indexFieldId.longValue()==39L){
    		OrderReport or=new OrderReport();
    		or.setOrd_created(new Date());
    		or.setOrd_remark(ord_remark);
    		or.setOrd_fd_mid(caseFileId);
    		or.setOrd_created_by(u.getUm_id());
    		orderReportService.save(or);
    		
    		
    	}
    	response.setResponse("TRUE");
    	jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value="/downloadfiles/{id}",method=RequestMethod.GET)
	public void downloadCaseFile(@PathVariable("id") Long caseFileId,HttpServletResponse response,HttpSession session)
	{	
		List<SubDocument> subDocuments=subDocumentService.getAllSubDocuments(caseFileId);
		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		Lookup lookUpZip=lookupService.getLookUpObject("ZIP_DOWNLOAD_PATH");
		
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
		}
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
	@RequestMapping(value = "/vieworders/{id}", method = RequestMethod.GET)
	public  String viewOrders(@PathVariable("id") Long docId,Model model) {
		Long metafieldId=39L;
		List<SubDocument> subDocuments = subDocumentService.getByField(docId,metafieldId);
		String returnview="/casefile/view";
		if(subDocuments.size()==0){
			returnview="/casefile/notfound";	
		}else{
			String uploadPath = context.getRealPath("");
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
			String filename="ORDS"+caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year();
			String orderswithbk="Orders"+caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year();
			String outputFilePath=uploadPath+File.separator+"uploads"+File.separator+filename;
			String bookmarkFile=uploadPath+File.separator+"uploads"+File.separator+orderswithbk;
			String basePath="";
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			
			basePath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label();
			
			List<InputStream> list = new ArrayList<InputStream>();
			Map<String,Integer> map=new HashMap<String, Integer>();
			Integer pagecount=0;
			int i=0;
	        try {
	        	if(subDocuments.size()>1){
	        	OutputStream out = new FileOutputStream(new File(outputFilePath+".pdf"));
	        	for(SubDocument subDocument:subDocuments){
	    			String srcPath=basePath+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
	    			String label=subDocument.getIndexField().getIf_label();
	    			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    			if(subDocument.getSd_submitted_date()!=null){
	    			String date = formatter.format(subDocument.getSd_submitted_date());
	    			label=label+"_"+date;
	    			}
	    			if(i==0){
	    				String lastfilePath=srcPath;
	        			PdfReader reader = new PdfReader(lastfilePath);
	    				map.put(label, 1);    			
	    				reader.close();
	    			}
	    			if(i>0){
	    				
	    				String lastfilePath=basePath+File.separator+subDocuments.get(i-1).getIndexField().getIf_name()+File.separator+subDocuments.get(i-1).getSd_document_name()+".pdf";
	        			PdfReader reader = new PdfReader(lastfilePath);
	        			pagecount+=reader.getNumberOfPages();
	        			map.put(label, pagecount+1);    			
	    				reader.close();
	    			}
	    			list.add(new FileInputStream(new File(srcPath)));
	    			i++;
	    		}
	        	PDFMerger.doMerge(list, out);
	        
	        	//globalfunction.doBookmark(outputFilePath+".pdf",bookmarkFile+".pdf",map);
	            //File f=new File(outputFilePath+".pdf");
	            //f.delete();
	        }else{
	        	String srcPath=basePath+File.separator+subDocuments.get(0).getIndexField().getIf_name()+File.separator+subDocuments.get(0).getSd_document_name();
        		File src=new File(srcPath+".pdf");
        		File dest=new File(bookmarkFile+".pdf");
        		FileUtils.copyFile(src, dest);
        	}
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        	
			model.addAttribute("document_name", filename);
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication",null);
			model.addAttribute("isImpugnedOrder",null);
		}
		
		return returnview;
	}
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public  String view(@PathVariable("id") Long docId,Model model) {
		
		
		SubDocument subDocument = subDocumentService.getPetitionSubDocument(docId,1);
		String returnview="/casefile/view";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
			
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
			model.addAttribute("document_name", subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication",null);
			model.addAttribute("isImpugnedOrder",null);
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			
			File source = new File(srcPath);	
	
			String uploadPath = context.getRealPath("");		
			File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");
	
			try {
			    FileUtils.copyFile(source, dest);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			}
		}
		
		return returnview;
	}
	@RequestMapping(value = "/impugnedorder/{id}", method = RequestMethod.GET)
	public  String impugnedorder(@PathVariable("id") Long ioId,Model model) {
		
		ImpugnedOrder io=caseFileDetailService.getImpugnedOrder(ioId);
		Integer caseYear = null;
		try {
			caseYear = Integer.parseInt(io.getIo_case_year());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CaseFileDetail cfd=caseFileDetailService.getCaseFileDetail(io.getIo_hc_case_type(),io.getIo_case_no(),caseYear);
		Long docId=io.getIo_fd_mid();
		
		SubDocument subDocument = subDocumentService.getPetitionSubDocument(cfd.getFd_id(),1);
		String returnview="/casefile/view";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
			
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(cfd.getFd_id());
			model.addAttribute("document_name", subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication",null);
			model.addAttribute("isImpugnedOrder",1);
			model.addAttribute("casetype",cfd.getCaseType().getCt_label());
			model.addAttribute("caseno",cfd.getFd_case_no());
			model.addAttribute("caseyear",cfd.getFd_case_year());
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			
			File source = new File(srcPath);	
	
			String uploadPath = context.getRealPath("");		
			File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");
	
			try {
			    FileUtils.copyFile(source, dest);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			}
		}
		
		return returnview;
	}
	@RequestMapping(value = "/subdocument/{id}", method = RequestMethod.GET)
	public  String subdocument(@PathVariable("id") Long subDocId,Model model) {
		SubDocument subDocument = subDocumentService.getByPK(subDocId);
				
		String returnview="/casefile/view";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
			Long docId=subDocument.getSd_fd_mid();
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
			
			model.addAttribute("doc_id", docId);
			model.addAttribute("document_name", subDocument.getSd_document_name());
			model.addAttribute("isApplication",null);
			model.addAttribute("isImpugnedOrder",null);
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			
			File source = new File(srcPath);	
	
			String uploadPath = context.getRealPath("");		
			File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");
	
			try {
			    FileUtils.copyFile(source, dest);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			}
		}
		return returnview;
	}
	@RequestMapping(value = "/applicationview", method = RequestMethod.GET)
	public  String subdocument(@RequestParam("app_no") Integer app_no,@RequestParam("app_year") Integer app_year,Model model) {
		SubDocument subDocument = subDocumentService.getApplication(app_no,app_year);
		String returnview="/casefile/view";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
		Long docId=subDocument.getSd_fd_mid();
		CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
		Date date = subDocument.getSd_submitted_date();
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    String submitted_date= ""; 
	    if(date!=null){
	    	submitted_date= formatter.format(date);
	    }
	    
		model.addAttribute("doc_id", docId);
		model.addAttribute("document_name", subDocument.getSd_document_name());
		model.addAttribute("isApplication",1);
		model.addAttribute("isImpugnedOrder",null);
		model.addAttribute("application_type", subDocument.getDocumentType().getAt_name());
		model.addAttribute("application_no", subDocument.getSd_document_no());
		model.addAttribute("application_year", subDocument.getSd_document_year());
		model.addAttribute("submitted_date", submitted_date);
		model.addAttribute("party", subDocument.getSd_party());
		model.addAttribute("name", subDocument.getSd_description());
		model.addAttribute("counsel", subDocument.getSd_counsel());

		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
		
		File source = new File(srcPath);	

		String uploadPath = context.getRealPath("");		
		File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");

		try {
		    FileUtils.copyFile(source, dest);
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
		}
		return returnview;
	}

	@RequestMapping(value = "/getsubdocuments/{id}", method = RequestMethod.GET)
	public @ResponseBody String getSubDocuments(@PathVariable("id") Long fd_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<SubDocument> response=new ActionResponse<>();
		
		List<SubDocument> subDocuments=subDocumentService.getSubDocuments(fd_id);
		response.setResponse("TRUE");
		response.setModelList(subDocuments);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/getcasefiledetails/{id}", method = RequestMethod.GET)
	public @ResponseBody String getcasefiledetails(@PathVariable("id") Long fd_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		CaseFileDetail casefile=caseFileDetailService.getCaseFileDetail(fd_id);
		response.setResponse("TRUE");
		response.setModelData(casefile);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/getorderreports/{id}", method = RequestMethod.GET)
	public @ResponseBody String getorderreports(@PathVariable("id") Long fd_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<OrderReport> response=new ActionResponse<>();
		
		List<OrderReport> orderReportData=orderReportService.getOrderReports(fd_id);
		response.setResponse("TRUE");
		response.setModelList(orderReportData);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	
	@RequestMapping(value = "/getmetadata/{id}", method = RequestMethod.GET)
	public @ResponseBody String getMetadata(@PathVariable("id") Long fd_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<MetaData> response=new ActionResponse<>();
		
		List<MetaData> metadata=caseFileDetailService.getMetadata(fd_id);
		response.setResponse("TRUE");
		response.setModelList(metadata);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/viewdocument/{id}", method = RequestMethod.GET)
	public void copysubdocument(@PathVariable("id") Long sd_id,HttpServletRequest request,
		       HttpServletResponse response) throws IOException {
		String jsonData = null;
	/*	ActionResponse<SubDocument> response=new ActionResponse<>();
		
		SubDocument subDocument = subDocumentService.getByPK(sd_id);
		CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(subDocument.getSd_fd_mid());
		User user=(User) session.getAttribute("USER");		
		
		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
		
		File source = new File(srcPath);	

		String uploadPath = context.getRealPath("");		
		File dest = new File(uploadPath+File.separator+"uploads"+File.separator+subDocument.getSd_document_name()+".pdf");
		
		try {
		    FileUtils.copyFile(source, dest);
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
		response.setResponse("TRUE");
		response.setData(subDocument.getSd_document_name()+".pdf");
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;*/
		SubDocument subDocument = subDocumentService.getByPK(sd_id);
		CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(subDocument.getSd_fd_mid());
		
		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		String srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
		System.out.println("filename="+srcPath);	
		File source = new File(srcPath);	
//		response.setContentType("application/pdf");
//		response.setHeader("Content-Disposition", "inline; filename='" + srcPath + "'");
//		response.setContentLength((int) source.length());
		File file = new File(srcPath);	
		response.setHeader("content-disposition", "inline" );
		response.setContentType("application/pdf");       
		response.setContentLength((int)file.length());
		  
		ServletOutputStream out = response.getOutputStream();
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(stream);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		  
		byte[] buff = new byte[2048];
		int bytesRead;
		  
		while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) 
		{
		bos.write(buff, 0, bytesRead);
		}
		bis.close();
		stream.close();
		bos.close();
		out.close();
	}
	@RequestMapping(value = "/deletesubdocument/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSubdocument(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<SubDocument> response = new ActionResponse<SubDocument>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		SubDocument sd= subDocumentService.getByPK(id);
		sd.setSd_rec_status(0);
		sd.setSd_mod_date(new Date());
		sd.setSd_mod_by(u.getUm_id());
		subDocumentService.save(sd);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/deleteofficereport/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String officereport(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<OrderReport> response = new ActionResponse<OrderReport>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		OrderReport or= orderReportService.getOrderReport(id);
		or.setOrd_rec_status(0);
		or.setOrd_mod_by(u.getUm_id());
		or.setOrd_mod_date(new Date());
		orderReportService.save(or);
		if(or.getSubDocument()!=null){
			SubDocument sd= subDocumentService.getByPK(or.getSubDocument().getSd_id());
			sd.setSd_rec_status(0);
			sd.setSd_mod_date(new Date());
			sd.setSd_mod_by(u.getUm_id());
			subDocumentService.save(sd);
		}
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/updatecasetype", method = RequestMethod.POST)
	public @ResponseBody String updateCaseType(HttpServletRequest request,HttpSession session) throws DocumentException 
	{
		ActionResponse<CaseFileDetail> response = new ActionResponse();
		User u=(User) session.getAttribute("USER");
		String jsonData="";
		Long caseFileId=Long.parseLong(request.getParameter("fd_id"), 10);
		Long newCaseTypeId=Long.parseLong(request.getParameter("new_case_type"), 10);
		String newCaseNo=request.getParameter("new_case_no");
		CaseFileDetail cfd=caseFileDetailService.getCaseFileDetail(caseFileId);
		List<SubDocument> subdocuments=subDocumentService.getAllSubDocuments(caseFileId);
		Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		CaseType newCaseType=casetypeService.getById(newCaseTypeId);
		boolean copyflag=false;
		for(SubDocument subDocument:subdocuments){
		
			String srcPath=lookupRepo.getLk_longname()+File.separator+cfd.getCaseType().getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
			String filename=subDocument.getSd_document_name();
			filename=filename.replace(cfd.getCaseType().getCt_label(), newCaseType.getCt_label());
			filename=filename.replace(cfd.getFd_case_no(), newCaseNo);
			String destPath=lookupRepo.getLk_longname()+File.separator+newCaseType.getCt_label()+File.separator+subDocument.getIndexField().getIf_name()+File.separator+filename+".pdf";
			subDocument.setSd_document_name(filename);
			
			File source=new File(srcPath);
			File dest=new File(destPath);
			try {
				if(!dest.exists()){
				// if copy of file does not exist in new case type folder
					FileUtils.copyFile(source, dest);
					subDocumentService.save(subDocument);
					source.delete();
					copyflag=true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(copyflag){
			if(cfd.getFd_file_source().equals("O")){
				// update online case type
				EfilingCaseFileDetail ecfd=caseFileDetailService.getEfilingCaseFileDetail(cfd.getFd_case_type(), cfd.getFd_case_no(), cfd.getFd_case_year());
				ecfd.setFd_case_type(newCaseTypeId);
				ecfd.setFd_case_no(newCaseNo);
				caseFileDetailService.saveCaseFile(ecfd);
			}
			String documentname=cfd.getFd_document_name();
			documentname=documentname.replace(cfd.getCaseType().getCt_label(), newCaseType.getCt_label());
			documentname=documentname.replace(cfd.getFd_case_no(), newCaseNo);
			
			cfd.setFd_document_name(documentname);
			cfd.setFd_case_type(newCaseTypeId);
			cfd.setFd_case_no(newCaseNo);
			caseFileDetailService.save(cfd);
	    	response.setResponse("TRUE");
		}else{
			response.setResponse("FALSE");	
		}
    	jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/deletepetitioner/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletepetitioner(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<Petitioner> response = new ActionResponse<Petitioner>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		Petitioner pet= caseFileDetailService.getPetitioner(id);
		pet.setPt_rec_status(0);
		pet.setPt_mod_by(u.getUm_id());
		pet.setPt_mod_date(new Date());
		caseFileDetailService.save(pet);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/deleterespondent/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleterespondent(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<Respondent> response = new ActionResponse<Respondent>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		Respondent res= caseFileDetailService.getRespondent(id);
		res.setRt_rec_status(0);
		res.setRt_mod_by(u.getUm_id());
		res.setRt_mod_date(new Date());
		caseFileDetailService.save(res);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/deletepcounsel/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletepcounsel(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<PetitionerCounsel> response = new ActionResponse<PetitionerCounsel>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		PetitionerCounsel pc= caseFileDetailService.getPetitionerCounsel(id);
		pc.setPc_rec_status(0);
		pc.setPc_mod_by(u.getUm_id());
		pc.setPc_mod_date(new Date());
		caseFileDetailService.save(pc);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/deletercounsel/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletercounsel(@PathVariable("id")  Long id, HttpSession session) {
		ActionResponse<RespondentCounsel> response = new ActionResponse<RespondentCounsel>();
		String jsonData = null;
		User u=(User) session.getAttribute("USER");
		RespondentCounsel rc= caseFileDetailService.getRespondentCounsel(id);
		rc.setRc_rec_status(0);
		caseFileDetailService.save(rc);
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/uploadpetition", method = RequestMethod.POST)
	public @ResponseBody String uploadPetition(MultipartHttpServletRequest request,HttpSession session) throws DocumentException 
	{
		ActionResponse<SubDocument> response = new ActionResponse();
		response.setResponse("FALSE");
		User u=(User) session.getAttribute("USER");
		String jsonData="";
		Lookup lookup=lookupService.getLookUpObject("REPOSITORYPATH");
		Long caseFileId=Long.parseLong(request.getParameter("fd_id"), 10);
		SubDocument sd=subDocumentService.getPetitionSubDocument(caseFileId,1);
		if(sd!=null){
			sd.setSd_rec_status(0);
			sd=subDocumentService.save(sd);
		}else{
			sd=subDocumentService.getPetitionSubDocument(caseFileId,0);
		}
		
		MultipartFile mpf = null;
    	Iterator<String> itr = request.getFileNames();
    	String newfilepath="";
    	CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(caseFileId);
		IndexField indexField=masterService.getIndexField(1L);
		Integer count=subDocumentService.getCount(caseFileId);
		count=count+1;
		
    	while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			String filename=caseFileDetail.getFd_document_name()+"_"+indexField.getIf_type_code()+"_"+count;
			newfilepath=lookup.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+indexField.getIf_name()+File.separator+filename+".pdf";
			SubDocument subDocument=new SubDocument();
			subDocument.setSd_cr_by(u.getUm_id());
			subDocument.setSd_cr_date(new Date());
			subDocument.setSd_fd_mid(caseFileId);
			subDocument.setSd_if_mid(1L);
			subDocument.setSd_document_name(filename);
			subDocument.setSd_document_id(sd.getSd_document_id());
			subDocument.setSd_submitted_date(sd.getSd_submitted_date());
			subDocument.setSd_rec_status(1);
			subDocument.setSd_document_no(sd.getSd_document_no());
			subDocument.setSd_document_year(sd.getSd_document_year());
			subDocument.setSd_counsel(sd.getSd_counsel());
			subDocument.setSd_description(sd.getSd_description());
			subDocument.setSd_party(sd.getSd_party());
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newfilepath));
				File source=new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
		       	subDocument.setSd_no_of_pages(no_of_pages);
				
		       	subDocument=subDocumentService.save(subDocument);
		       	reader.close();
		       	response.setResponse("TRUE");
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}    	
    	
    	jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value = "/addfiles", method = RequestMethod.POST)
	public @ResponseBody String addfiles(@RequestBody DownloadModel downloadModel,HttpSession session) 
	{
		String jsonData="";
		ActionResponse<DownloadModel> response=new ActionResponse<>();
		User u=(User) session.getAttribute("USER");
		List<OrderReport> orderReports= downloadModel.getOrderReports();
		List<SubDocument> subDocuments=downloadModel.getSubDocuments();
		DownloadReport dr=new DownloadReport();
		Integer pages=0;
		Double dr_amount = 0.00;
		if(!subDocuments.isEmpty() || !orderReports.isEmpty()){
			dr.setDr_cr_date(new Date());
			dr.setDr_fd_mid(downloadModel.getFd_id());
			dr.setDr_cr_by(u.getUm_id());
			dr.setDr_rec_status(1);
			dr=downloadService.saveReport(dr);
			response.setResponse("TRUE");
			response.setData("Files added for downloading");
		}else{
			response.setResponse("FALSE");
			response.setData("Please select files for downloading");
		}
		if(!subDocuments.isEmpty()){
			for(SubDocument subDocument:subDocuments){
				DownloadFile df=new DownloadFile();
				df.setDf_dr_mid(dr.getDr_id());
				df.setDf_sd_mid(subDocument.getSd_id());
				df.setDf_pages(subDocument.getSd_no_of_pages());
				df.setDf_submitted_date(subDocument.getSd_submitted_date());
				df=downloadService.saveFile(df);
				pages=pages+subDocument.getSd_no_of_pages();
			}
		}
		if(!orderReports.isEmpty()){
			for(OrderReport orderReport:orderReports){
				DownloadFile df=new DownloadFile();
				df.setDf_dr_mid(dr.getDr_id());
				df.setDf_ord_mid(orderReport.getOrd_id());
				df.setDf_pages(1);
				df.setDf_submitted_date(orderReport.getOrd_created());
				df=downloadService.saveFile(df);
				pages=pages+1;
				if(orderReport.getSubDocument()!=null){
					SubDocument subDocument=orderReport.getSubDocument();
					DownloadFile df2=new DownloadFile();
					df2.setDf_dr_mid(dr.getDr_id());
					df2.setDf_sd_mid(subDocument.getSd_id());
					df2.setDf_pages(subDocument.getSd_no_of_pages());
					df2.setDf_submitted_date(subDocument.getSd_submitted_date());
					downloadService.saveFile(df2);
					pages=pages+subDocument.getSd_no_of_pages();
				}
			}
		}
		if(pages>0){
			if(pages<=10){
				dr_amount=15.00;
			}else{
				Double amount1=10.00;
				Integer pagesremaining=pages-10;
				Double amount2=Math.ceil(pagesremaining/5.0)*10;
				dr_amount=amount1+amount2;
			}
		}
		dr.setDr_amount(dr_amount);
		downloadService.saveReport(dr);
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value = "/getdownloadhistory/{id}", method = RequestMethod.GET)
	public @ResponseBody String getdownloadhistory(@PathVariable("id") Long fd_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadReport> response=new ActionResponse<>();
		
		List<DownloadReport> reports=downloadService.getDownloadReport(fd_id);
		response.setResponse("TRUE");
		response.setModelList(reports);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/getdownloadedfiles/{id}", method = RequestMethod.GET)
	public @ResponseBody String getdownloadedfiles(@PathVariable("id") Long dr_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadFile> response=new ActionResponse<>();
		
		List<DownloadFile> files=downloadService.getFiles(dr_id);
		response.setResponse("TRUE");
		response.setModelList(files);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/downloadfile/{id}", method = RequestMethod.GET)
	public void downloadfiles(@PathVariable("id") Long dr_id,HttpSession session,HttpServletResponse response) {
		String jsonData = null;
		
		DownloadReport report=downloadService.getById(dr_id);
		
		if(report.getDr_rec_status()==1){
			Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
			Lookup lookupDownload=lookupService.getLookUpObject("DOWNLOADPATH");
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(report.getDr_fd_mid());
			String basePath=lookupRepo.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label();
			
			List<InputStream> list = new ArrayList<InputStream>();
			
			List<DownloadFile> files=downloadService.getFiles(report.getDr_id());
			String downloadFolder=lookupDownload.getLk_longname()+File.separator+caseFileDetail.getFd_document_name();
			File dir=new File(downloadFolder);
			if(!dir.exists()){
				dir.mkdir();
			}
			String outputFilePath=downloadFolder+File.separator+File.separator+caseFileDetail.getFd_document_name();
			OutputStream out;
			try {
				out = new FileOutputStream(new File(outputFilePath+".pdf"));
			
				for(DownloadFile file:files){
					if(file.getDf_sd_mid()!=null){
						SubDocument subDocument=file.getSubDocument();
						String srcPath=basePath+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
						list.add(new FileInputStream(new File(srcPath)));
					
					}
					if(file.getDf_ord_mid()!=null){
						String pdfname=downloadFolder+File.separator+file.getDf_id()+".pdf";
						PDFCreator.createPDF(file.getOrdeReport(), pdfname);
						list.add(new FileInputStream(new File(pdfname)));
					}
				}
				PDFMerger.doMerge(list, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try
	        {
				globalfunction.zipFolder(dir.getAbsolutePath(),dir.getAbsolutePath()+".zip");
	        response.setContentType("application/zip");  
	        PrintWriter out1 = response.getWriter();  
	        String filename = dir.getName()+".zip";   
	        String filepath = dir.getAbsolutePath()+".zip";
	        File zipFile=new File(dir+".zip");
	        
	        response.setContentType("APPLICATION/OCTET-STREAM");   
	        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
	          
	        FileInputStream fileInputStream = new FileInputStream(filepath);  
	                    
	        int i;   
	        while ((i=fileInputStream.read()) != -1) {  
	        out1.write(i);   
	        }   
	        fileInputStream.close();   
	        out1.close();
	        //FileUtils.deleteDirectory(dir); 
	        zipFile.delete();
	        report.setDr_rec_status(2);
	        downloadService.saveReport(report);
	        }catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
		}
	}
	
}

