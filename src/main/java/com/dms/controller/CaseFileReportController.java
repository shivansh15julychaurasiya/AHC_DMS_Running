package com.dms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.DownloadFile;
import com.dms.model.DownloadReport;
import com.dms.service.CaseFileDetailService;
import com.dms.service.CommonReportsService;
import com.dms.service.DownloadFileService;
import com.dms.service.SubDocumentService;
import com.dms.utility.GlobalFunction;


@Controller
@RequestMapping("/casereports")
public class CaseFileReportController {
	
	@Autowired
	private CommonReportsService commonReportsService;
	@Autowired
	ServletContext context;
	
	@Autowired
	private DownloadFileService downloadService;
	
	private GlobalFunction globalfunction;
	
	public CaseFileReportController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String Download() {
		return "/reports/download";
	}

	
	@RequestMapping(value = "/casefilereports", method = RequestMethod.GET)
	public String caseFileReports() {
		return "/reports/casefilereports";
	}
	@RequestMapping(value = "/officeReport", method = RequestMethod.GET)
	public String searchByParty() {
		return "/reports/officeReport";
	}
	
	@RequestMapping(value = "/getdownloadhistory", method = RequestMethod.GET)
	public @ResponseBody String getdownloadhistory(@RequestParam(value="itemsPerPage")int itemsPerPage,@RequestParam(value="pagenumber")int pagenumber) {
		String jsonData = null;
		ActionResponse<DownloadReport> response=new ActionResponse<>();
		
		List<DownloadReport> reports=downloadService.getDownloadReports(itemsPerPage,pagenumber);
		Integer total_count=downloadService.getDownloadReportsCount();
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
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
	
	@RequestMapping(value ="/getfilecavapp",method =RequestMethod.GET)
	public @ResponseBody String getFilecavApp() {
		Map<String,Object> m =commonReportsService.getFilecavApp();
		System.out.println("reprttttttttttt"+m);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(m);
		return jsonData;
	}
	
	@RequestMapping(value ="/getcasetypereport",method =RequestMethod.GET)
	public @ResponseBody String getcaseTypeReport() {
		Object caseTypeReport=commonReportsService.getcaseTypeReport();
		System.out.println("reprttttttttttt"+caseTypeReport);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(caseTypeReport);
		return jsonData;
	}
	
	@RequestMapping(value ="/getcasestagereport",method =RequestMethod.GET)
	public @ResponseBody String getcaseStageReport() {
		Object caseTypeReport=commonReportsService.getcaseStageReport();
		System.out.println("reprttttttttttt"+caseTypeReport);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(caseTypeReport);
		return jsonData;
	}
	
	@RequestMapping(value ="/getcavstagereport",method =RequestMethod.GET)
	public @ResponseBody String getcavStageReport() {
		Object caseTypeReport=commonReportsService.getcavStageReport();
		System.out.println("reprttttttttttt"+caseTypeReport);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(caseTypeReport);
		return jsonData;
	}
	
	@RequestMapping(value ="/getappstagereport",method =RequestMethod.GET)
	public @ResponseBody String getappStageReport() {
		Object caseTypeReport=commonReportsService.getappStageReport();
		System.out.println("reprttttttttttt"+caseTypeReport);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(caseTypeReport);
		return jsonData;
	}
	
	/*@RequestMapping(value ="/getcasetypereport1",method =RequestMethod.GET)
	public @ResponseBody String getcaseTypeReport1() {
		//List<CaseFileTypeReportDto> caseTypeReport=cps.getcaseTypeReport1();
	//	System.out.println("reprttttttttttt"+caseTypeReport);
		String jsonData = null;
		//jsonData = globalfunction.convert_to_json(caseTypeReport);
		return jsonData;
	}*/
	
	@RequestMapping(value ="/getcaseyearlyreport",method =RequestMethod.GET)
	public @ResponseBody String getcaseYearlyReport() {
		Map<String,Object> caseYearlyReport=commonReportsService.getcaseYearlyReport();
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyreprttttttttttt"+caseYearlyReport);
		String jsonData = null;
		jsonData = globalfunction.convert_to_json(caseYearlyReport);
		return jsonData;
	}

}
