<%@ include file="../content/header2.jsp"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<div id="content" class="content" ng-controller="causeListController">
<input type="hidden" class="form-control" value=${type_id} id="type_id" name="type_id"> 
	<div class="row">
			<div class="col-md-12 ">
			<div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">${listType.getClt_description()}</h4>
		       </div>
				<div class="panel-body">
				<div class="table-responsive">
				<% if(role.equals("Private_Secretory")){ %>
				
						<table id="data-table" ng-init="getApplicationStages()" st-table="displayedCollection" class="table table-striped table-bordered nowrap table-hover">
							<thead>
								<tr>
									<td>
										<select class="form-control" ng-model="report.cl_stage_id" ng-options="lookup.lk_id as lookup.lk_longname for lookup in applicationStages  | orderBy:'lk_longname'">
											<option value="">Select Status</option>
										</select>
									</td>
									<td width="25%">
										<div class="input-group date" >
										<input type="text" class="form-control"
											datepicker-popup="{{format}}" ng-model="report.cl_date"
											is-open="opened1" datepicker-options="dateOptions"
											ng-disabled="true" /> <span class="input-group-addon"
											ng-click="open($event,'opened1')"><i
											class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</td>
									<td>
										<button id="search" type="submit" class="btn btn-primary btn-sm" ng-click="submitReport()">Submit</button>
									</td>
								</tr>

							</thead>
						</table>
						<% } %>
						
						<!-- switch	 -->
						
					<!-- 	<div class="tw-toggle">
  <input type="radio" name="toggle" value="false" ng-model="filterType">
  <label class="toggle toggle-yes">efile</label>
  <input checked type="radio" name="toggle" value="-1" ng-model="filterType">
  <label class="toggle toggle-yes">all</label>
  <input type="radio" name="toggle" value="true" ng-model="filterType">
  <label class="toggle toggle-yes">physical</label>
  <span></span>  
</div> -->

<font size="2" face="Arial, Helvetica, sans-serif" color="#666362" >
						
						<table st-table="displayedCollection" style="font-size:14px;" st-safe-src="masterdata"  class="table table-bordered">
						<thead>
						<tr>
						<th>Sr. No.</th>
						<th style="text-align:center">Case Detail</th>
						<th style="text-align:center">Party Name</th>
						<th style="text-align:center">Petitioner's Counsel</th>
						<th style="text-align:center">Respondent's Counsel</th>
						<th style="text-align:center">Case Status</th>
						<% if(role.equals("Private_Secretory")){ %>
						<th style="text-align:center">Status</th>
						<th style="text-align:center">Action</th>
						<% } %>	
						</tr>
						</thead>
						<div ng-if="showLoader" style="height: 60px">
							<div id="loader" class="center"></div>
						</div>
						<tbody>
						<tr ng-repeat-start="data in displayedCollection | filter: filterExpression"  >
						<tr ng-show="displayedCollection[$index-1].cl_case_stage !==data.cl_case_stage" >    
                        <td ng-show="displayedCollection[$index-1].cl_case_stage !==data.cl_case_stage" colspan="6" style="text-align: center;text-color:brown">
                        <span><b>{{data.cl_case_stage}}</b></span></td></tr>
                        <tr ng-show="displayedCollection[$index-1].cl_bunch_name !==data.cl_bunch_name" >    
                        <td ng-show="displayedCollection[$index-1].cl_bunch_name !==data.cl_bunch_name" colspan="6" style="text-align: left;text-color:brown">
                        <span><b>{{data.cl_bunch_name}}</b></span></td></tr>
                        <tr bgcolor= "#E1EBEE">
						 <td>
						 <span ng-show="displayedCollection[$index-1].cl_serial_no==data.cl_serial_no && data.cl_list_type_mid!=19"><b>with</b></span>
						 <span ng-show="displayedCollection[$index-1].cl_serial_no==data.cl_serial_no && data.cl_list_type_mid==19">{{data.cl_serial_no}}</span>
						
							<span ng-show="displayedCollection[$index-1].cl_serial_no!=data.cl_serial_no ">{{data.cl_serial_no}}</span>
							<br/>
						 <span ng-show="displayedCollection[$index-1].cl_serial_no!=data.cl_serial_no">{{data.cl_short_order}}</span>
						</td>
						<td ng-show ='data.cl_fd_mid  && data.petAvailable==false && data.appAvailable==false'  style="text-align:center;cursor: pointer;text-decoration: underline;">
					<!-- 	<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data.cl_ano,data.cl_ayr,data.cl_fd_mid)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span> -->
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2 || data.cl_list_type_mid==23 || data.cl_list_type_mid==24 || data.cl_list_type_mid==28 || data.cl_list_type_mid==21 || data.cl_list_type_mid==20|| data.cl_list_type_mid==24" ng-click="viewApplication(data)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2 && data.cl_list_type_mid!=23 && data.cl_list_type_mid!=24 && data.cl_list_type_mid!=21 && data.cl_list_type_mid!=28 && data.cl_list_type_mid!=20 && data.cl_list_type_mid!=24" ng-click="viewCaseFile(data)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<br/>
						<span ng-show ="data.cl_file_source.trim()==='O'" class ="text-info">E-filed</span>
						</td>
						<td  ng-show ='!data.cl_fd_mid && data.petAvailable==false' style="text-align:center;cursor: pointer;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2 || data.cl_list_type_mid==28  || data.cl_list_type_mid==20 || data.cl_list_type_mid==24 || data.cl_list_type_mid==21"  >Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2 && data.cl_list_type_mid!=28 && data.cl_list_type_mid!=20 && data.cl_list_type_mid!=24 && data.cl_list_type_mid!=21">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</br>
						<span class ="text-danger">Case File not in DMS</span>
						</td>
						
						<td  ng-show ='data.cl_fd_mid && data.petAvailable==true && data.appAvailable==false' style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2 || data.cl_list_type_mid==28  || data.cl_list_type_mid==20 || data.cl_list_type_mid==24 || data.cl_list_type_mid==21" ng-click="viewApplication(data)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2 && data.cl_list_type_mid!=28 && data.cl_list_type_mid!=20 && data.cl_list_type_mid!=24 && data.cl_list_type_mid!=21" ng-click="viewCaseFile(data)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<br/>
						<span class ="text-danger">Petition Not Available</span>
						</td>
						
						<td  ng-show ='data.cl_fd_mid && data.appAvailable==true' style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2 || data.cl_list_type_mid==28  || data.cl_list_type_mid==20 || data.cl_list_type_mid==24 || data.cl_list_type_mid==21"  ng-click="viewApplication(data)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2 && data.cl_list_type_mid!=28 && data.cl_list_type_mid!=20 && data.cl_list_type_mid!=24 && data.cl_list_type_mid!=21" ng-click="viewCaseFile(data)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<br/>
						<span class ="text-danger">Application Not E-Filed</span>
						</td>
						<td style="text-align:center">{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
						<td style="text-align:center" ng-show="data.cl_ccms_id"><a href="http://192.168.0.97/status/view-case-detail/{{data.cl_ccms_id}}" target=”_blank”>Status</a></td>
						
						<% if(role.equals("Private_Secretory")){ %>
						<td style="text-align:center">{{data.applicationStage.lk_longname}}</td>						
						<td>
                        	<input type="checkbox" name="checked" id="checked" value={{row.cl_id}} ng-model="data.checked" />
                         </td>
                         <% } %>	
                         </tr>
						</tr>
					<tr  ng-repeat-end ng-show="displayedCollection[$index-1].cl_serial_no!=data.cl_serial_no && (
                        data.cl_notice_no !==null || data.cl_lcr_no !==null || data.cl_crime_no !==null || data.cl_crime_ps !==null || data.cl_crime_dist !==null)">    
                        <td colspan="5" ng-show="data.cl_list_type_mid!==1">
                         <span class="font-italic" ng-show="data.cl_list_type_mid!==1 && data.cl_notice_no!==null" >Notice no: <p  style="color :purple;">{{data.cl_notice_no}}</p></span><br/>
                        <span class="font-italic" style="color :purple;" ng-show="data.cl_list_type_mid!==1 && data.cl_lcr_no !== null">TC No.: {{data.cl_lcase_name}}/{{data.cl_lcr_no}}/{{data.cl_lcr_year}}</span><br/>                        
                       
                        <span class="font-italic" ng-show="data.cl_list_type_mid!==1 && data.cl_crime_no !==null"><b>Crime No.</b>- {{data.cl_crime_no}}/{{data.cl_crime_year}},</span>
                         <span class="font-italic" ng-show="data.cl_list_type_mid!==1 && data.cl_crime_ps !==null"><b>Police St.</b>- {{data.cl_crime_ps}},</span>
                         <span class="font-italic" ng-show="data.cl_list_type_mid!==1 && data.cl_crime_dist !=null"> <b>District</b>- {{data.cl_crime_dist}},</span></b><br/>
                         
                          
                           <div ng-show="data.sameLcrDetails !==null">                       
                         -Details of Cases filed earlier with same Trial Court Case No.,Case Type & District (Subject to further verification by Section from original Records):<br/>
                          .............................................................................
                            <div ng-repeat="sld in data.sameLcrDetails">                      
                          <span class="font-italic">
                          <span ng-show ="sld.sld_fd_mid"  style="text-align:center;cursor: pointer;text-decoration: underline;color:red;" 
                          ng-click="viewCaseFileExtra(sld.sld_fd_mid)">
                          {{sld.sld_display}}</span>
                          <span ng-show ="!sld.sld_fd_mid"  >
                          {{sld.sld_display}}</span>, Title- {{sld.sld_party}}  ,Status-{{sld.sld_status}}({{sld.sld_disp_date}})
                          </span><br/>
                          </div ></div> <br>
                          
                          
                             <div ng-show="data.sameCrimDetails !=null">
                          -Details of Cases filed earlier with same Police Station,Crime No. & District<br/>
                          (Subject to further verification by Section from original Records):<br/>
                          .............................................................................
                            <div ng-repeat="scd in data.sameCrimDetails">                      
                          <span class="font-italic">
                          <span ng-show ="scd.scd_fd_mid"  style="text-align:center;cursor: pointer;text-decoration: underline;color:red;" 
                          ng-click="viewCaseFileExtra(scd.scd_fd_mid)">
                          {{scd.scd_display}}</span>
                          <span ng-show ="!scd.scd_fd_mid"  >
                          {{scd.scd_display}}</span>, Title- {{scd.scd_pet_name}} vs {{scd.scd_res_name}} ,Status-{{scd.scd_case_status}},  Police St.-{{scd.scd_ps_name}}
                          </span><br/>
                          </div ></div>
                         
                        </td><td colspan="5" ng-show="data.cl_list_type_mid==1">
                         <span class="font-italic" ng-show="data.cl_app_last_date.trim()!==''"> Last Listing : {{data.cl_app_last_date}},</span>
                          <span class="font-italic" ng-show="data.cl_next_list.trim()!==''"> Next Date In System : {{data.cl_next_list}},</span>
                          <span class="font-italic" ng-show="data.cl_applied_by.trim()!==''"> Applied by : {{data.cl_applied_by}},</span></b><br/></td>
                      
                        </tr>
						</tbody>
						<!-- <tfoot>
							<tr>
								<td colspan="5" class="text-center">
									<div st-pagination="" st-items-by-page="5000"  st-displayed-pages="4" ></div>
								</td>
							</tr>
						</tfoot> -->
						</table></font>
					</div>
					<div ng-show ="showList">
									<table  style="font-size:14px;"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Lists</th>
						<th style="text-align:center">Count</th>
							
						</tr>
						</thead>
						<tbody>
						<!-- <tr ng-repeat="data in displayedCollection1"> -->
						<tr ng-repeat="data in listData | orderBy:'cl_list_type_mid'">
						
						
						<td  style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span  ng-click="viewList(data.cl_list_type_mid)">{{data.cType.clt_description}}</span>
						</td>
						<td>
						{{data.count}}
						</td>
						<!-- <td   style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data.cl_ano,data.cl_ayr,data.cl_fd_mid)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td>
						<td  ng-show ='!data.cl_fd_mid' style="text-align:center;cursor: pointer;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" >Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td> -->
						
							
						</tr>
						</tbody>
						<!-- <tfoot>
							<tr>
								<td colspan="5" class="text-center">
									<div st-pagination="" st-items-by-page="5000"  st-displayed-pages="4" ></div>
								</td>
							</tr>
							
						</tfoot> -->
						</table>
						</div>
				</div>
			</div>
		</div>
	</div>
</div> 
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CauseListController.js?v=5"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"> -->
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
	
	<style>
<!--
.tw-toggle {
  /* background: #95A5A6; */
  display: inline-block;
  padding: 2px 3px;
  border-radius: 20px;
  position:relative;
  border: 2px solid #95A5A6;
}

.tw-toggle label {
  text-align: center;
  font-family: sans-serif;
  display: inline-block;
  color: #95A5A6;
  position:relative;
  z-index:2;
  margin: 0;
  text-align: center;
  padding: 2px 3px;
  font-size: 15px;
  /* cursor: pointer; */
}

.tw-toggle input {
  /* display: none; */
  position: absolute;
  z-index: 3;
  opacity: 0;
  cursor: pointer;
}

.tw-toggle span {
  line-height: 21px;
  border-radius: 50%;
  background:#fff;
  display:block;
  position:absolute;
  left: 22px;
  top: 2px;
  transition:all 0.3s ease-in-out;
}

.tw-toggle input[value="false"]:checked ~ span{

  height: 29px;
  width: 38px;
  background:#e74c3c;
  left:2px;
  color:#fff;
}

.tw-toggle input[value="true"]:checked ~ span{

  height: 29px;
  width: 61px;
  background:#27ae60;
  left: 66px;
}
.tw-toggle input[value="-1"]:checked ~ span{

  height: 29px;
  width: 25px;
  background:#95A5A6;
  left: 40px;
}

.tw-toggle input[value="false"]:checked + label,.tw-toggle input[value="true"]:checked + label{
  color:#fff;
}
.tw-toggle input[value="-1"]:checked + label{
  color:#fff;
}
-->
</style>
</body>
</html>