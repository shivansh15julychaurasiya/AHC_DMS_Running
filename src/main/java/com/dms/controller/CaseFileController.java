package com.dms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.CaseFileDetail;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.MetaData;
import com.dms.model.SubDocument;
import com.dms.model.User;
import com.dms.service.CaseFileDetailService;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.service.SubDocumentService;
import com.dms.utility.GlobalFunction;
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
		Long indexFieldId= Long.parseLong(request.getParameter("sd_if_mid"), 10);
		String order_date=request.getParameter("sd_submitted_date");
		Date orderDate=null;
		try {
			orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MultipartFile mpf = null;
    	Iterator<String> itr = request.getFileNames();
    	String newfilepath="";
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = formatter.format(date);
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
			subDocument.setSd_submitted_date(orderDate);
			subDocument.setSd_minor_sequence(count);
			
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newfilepath));
				File source=new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
		       	subDocument.setSd_no_of_pages(no_of_pages);
				
		       	subDocumentService.save(subDocument);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
    	response.setResponse("TRUE");
    	jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value="/downloadfiles/{id}",method=RequestMethod.GET)
	public void downloadCaseFile(@PathVariable("id") Long caseFileId,HttpServletResponse response,HttpSession session)
	{	
		List<SubDocument> subDocuments=subDocumentService.getSubDocuments(caseFileId);
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
	public  String view(@PathVariable("id") Long docId,HttpSession session,HttpServletRequest request,Model model) {
		model.addAttribute("doc_id", docId);
		
		CaseFileDetail caseFileDetail=caseFileDetailService.getCaseFileDetail(docId);
		SubDocument subDocument = subDocumentService.getSubDocument(docId);
		User user=(User) session.getAttribute("USER");		
		
		String returnview="/casefile/view";
		
		model.addAttribute("document_name", subDocument.getSd_document_name());

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

