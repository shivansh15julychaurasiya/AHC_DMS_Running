package com.dms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.ApplicationNotice;
import com.dms.model.CaseNotice;
import com.dms.model.Lookup;
import com.dms.model.SubDocument;
import com.dms.model.User;
import com.dms.service.CaseFileDetailService;
import com.dms.service.LookupService;
import com.dms.service.MasterService;
import com.dms.service.NoticeService;
import com.dms.service.UserService;
import com.dms.utility.PDFGenerate;
import com.dms.utility.SendEmailWithAttachment;
import com.dms.utility.SendMail;
import com.itextpdf.text.pdf.PdfReader;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	static String emailToRecipient, emailSubject, emailMessage;
    static final String emailFromRecipient = "bilalkhan0408@gmail.com";

	@Autowired
    private JavaMailSender mailSenderObj;
	
	@Autowired
	private LookupService lookupService;

	@Autowired
	private MasterService masterService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private CaseFileDetailService caseFileDetailService;

	@Autowired
	private UserService usermaster;

	@Autowired
	private PDFGenerate pdfGenrate;

	@RequestMapping(value = "/getCaseForNotice", method = RequestMethod.GET)
	public String download_manage() {

		return "/notice/manage";
	}

	@RequestMapping(value = "/case_notice",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	public void addCaseNotice(@RequestBody CaseNotice caseNotice,HttpSession session) {
		
		/*SendMail sm =new SendMail();
		sm.sendMail("sushant", "sushantmishra09@gmail.com","what are you doind","Heloooooo");
		*/
		
		User u =(User)session.getAttribute("USER");
		String path="D:/notice/case.pdf";
		try {  
			pdfGenrate.createCaseNoticeRptPdf(caseNotice, path);

			File file = new File(path);  
			if (file.exists()) {  
				System.out.println("New File is created!"); 
				caseNotice.setCn_cr_by(u.getUm_id());
				caseNotice.setCn_cr_date(new Date());
				caseNotice.setCn_rec_status(1);
				noticeService.save(caseNotice);
			} else {  
				System.out.println("something went wrong!!!.");  
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		
	}

	@RequestMapping(value = "/application_notice",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	public void addApplicationNotice(@RequestBody ApplicationNotice applicationNotice, HttpSession session) {
		
		
		
		 emailSubject ="check highcourt notice";
	      emailMessage = "mail send without attachment";
	        emailToRecipient = "bilalkhan0408@gmail.com";
	 
	        // Logging The Email Form Parameters For Debugging Purpose
	     //   System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
	        System.out.println("mail send without attachment");
	       mailSenderObj.send(new MimeMessagePreparator() {
				
				
				
			
			
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	 
	                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");             
	                mimeMsgHelperObj.setTo(emailToRecipient);
	                mimeMsgHelperObj.setFrom(emailFromRecipient);               
	                mimeMsgHelperObj.setText(emailMessage);
	                mimeMsgHelperObj.setSubject(emailSubject);
	                
	                File attachFileObj =new File("D:/application/application.pdf");
	                
	                /*if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {
	                    System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
	                    mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {                   
	                        public InputStream getInputStream() throws IOException {
	                            return attachFileObj.getInputStream();
	                        }
	                    });
	                } else {
	                    System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");
	                }*/
	            }
	        });
		
		User u = (User) session.getAttribute("USER");
		
		String path="D:/application/application.pdf";
		try {  
			pdfGenrate.createApplicationNoticeRptPdf(applicationNotice, path);

			File file = new File(path);  
			if (file.exists()) {  
				System.out.println("New File is created!");  
				applicationNotice.setAn_rec_status(1);
				applicationNotice.setAn_cr_by(u.getUm_id());
				applicationNotice.setAn_cr_date(new Date());
				
				noticeService.save(applicationNotice);
			} else {  
				System.out.println("something went wrong!!!.");  
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  

		
	}

	@RequestMapping(value = "/uploadNotice", method = RequestMethod.POST)
	public @ResponseBody String uploadJudgement(
			MultipartHttpServletRequest request, HttpSession session)
					throws DocumentException {
		ActionResponse<SubDocument> response = new ActionResponse<SubDocument>();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH"); 

		Integer at_id=null;



		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		String newfilepath = "D:\\noticeform\\a.pdf";


		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			SubDocument subDocument = new SubDocument();

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(
						newfilepath));
				File source = new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());   
				Integer no_of_pages = reader.getNumberOfPages();
				subDocument.setSd_no_of_pages(no_of_pages);
				System.out.println("file saveddddddddddddddddddddddd");
				//SendEmailWithAttachment.sendMail("bilalkhan0408@gmail.com","bilal.khan@nexsussolutions.com" , source);

				SendMail sm =new SendMail();
				sm.sendMail("sushant", "sushantmishra09@gmail.com","what are you doind","Heloooooo");


				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		response.setResponse("TRUE");

		return jsonData;
	}

}
