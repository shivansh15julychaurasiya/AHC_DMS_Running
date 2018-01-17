<%@ include file="../content/header2.jsp"%>
<div id="content" class="content" ng-controller="causeListController">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Search Cause List</h4>
		       </div>
				<div class="panel-body">
<!-- 				<div> -->
					<table id="data-table" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>						
							<td width="25%" >
								<div class="input-group" >
									<select ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes | orderBy:'clt_description'"
										class="form-control" ng-model="model.cl_list_type_mid"><option value="">Select List Type</option></select>
								</div>	
							</td>
							<td width="25%">
								<div class="input-group date" >
								<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="model.cl_dol"
									is-open="opened1" datepicker-options="dateOptions"
									ng-disabled="true" /> <span class="input-group-addon"
									ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</td>
							
							<td width="25%">
								<div class='input-group'>
									<button type="submit" class="btn btn-sm btn-success"
										ng-click="getList(model)">
										<span class="glyphicon glyphicon-plus-sign"></span>
										Submit
									</button>									
								</div>
							</td>
							
						</tr>
					</thead>
					</table>
<!-- 					</div> -->
				
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Sl No.</th>
						<th style="text-align:center">Case No</th>
						<th style="text-align:center">Petitioner vs Respondent</th>
						<th style="text-align:center">Petitioner Council</th>
						<th style="text-align:center">Respondent Council</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in displayedCollection">
						<td><span ng-show="displayedCollection[$index-1].cl_serial_no==data.cl_serial_no"><b>with</b></span>
							<span ng-show="displayedCollection[$index-1].cl_serial_no!=data.cl_serial_no">{{data.cl_serial_no}}</span>
						</td>
						<td style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data.cl_ano,data.cl_ayr)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td>
						<td style="text-align:center">{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
						
						</tr>
						<tr ng-if="displayedCollection.length==0 && search" >
							<td colspan="5" class="alert alert-danger">No Records Found</td>
						</tr>
						</tbody>
						</table>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
</div> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/SearchCauseListController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</body>
</html>