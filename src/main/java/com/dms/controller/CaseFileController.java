package com.dms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.MetaData;
import com.dms.model.OrderReport;
import com.dms.model.SubDocument;
import com.dms.model.User;
import com.dms.service.CaseFileDetailService;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.service.OrderReportService;
import com.dms.service.SubDocumentService;
import com.dms.utility.GlobalFunction;
import com.dms.utility.PDFCreator;
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
	
	private GlobalFunction globalfunction;	
	
	public CaseFileController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String Manage() {

		return "/casefile/manage";
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
		Integer at_id= Integer.parseInt(request.getParameter("at_id"), 10);
		String ord_remark=request.getParameter("ord_remark");
		String order_date=request.getParameter("sd_submitted_date");
		Long indexFieldId=39L;
		Date orderDate=new Date();
		if(at_id==100001 || at_id==100002){
			try {
				orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public  String view(@PathVariable("id") Long docId,Model model) {
		
		
		SubDocument subDocument = subDocumentService.getSubDocument(docId);
		String returnview="/casefile/view";
		if(subDocument==null){
			returnview="/casefile/notfound";	
		}else{
			
			CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
			model.addAttribute("document_name", subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication",null);
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
        
	
}

