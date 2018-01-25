<%@ include file="../content/header2.jsp"%>
<div id="content" class="content" ng-controller="causeListController">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Manage Cause List</h4>
		       </div>
				<div class="panel-body">
<!-- 				<div> -->
					<table id="data-table" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>						
							<td width="25%" >
								<div class="input-group" >
									<select ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes"
										class="form-control" ng-model="model.cl_list_type_mid"><option value="">Select List Type</option></select>
								</div>	
							</td>
							<td width="25%">
								<div class="input-group" >
								<select ng-options="c.cm_id as c.cm_name for c in courtList"
									class="form-control" ng-model="model.cl_court_no"><option value="">Select Court</option></select>
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
									<button type="submit" class="btn btn-sm btn-success" ng-click="getList(model)">Search</button>
									<button type="submit" class="btn btn-sm btn-success" ng-click="downloadList(model)">Download</button>
								</div>
							</td>
							
						</tr>
					</thead>
					</table>
<!-- 					</div> -->
				
					<div class="row">
						<div class="col-xs-offset-7">
							<button type="button" class="btn btn-sm btn-success" ng-click="refreshModel()" data-toggle="modal" data-target="#causeListCreate">
								Upload CauseList
							</button>
							<button type="button" class="btn btn-sm btn-success" ng-click="getCaseTypes()" data-toggle="modal" data-target="#caseAdd">
								Add Case
							</button>
							<button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#causeListUpdate">
								Update Court
							</button>
							<button type="button" class="btn btn-sm btn-success" ng-click="deleteall()">
								Delete All
							</button>
						</div>
					</div>
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Sl No.</th>
						<th style="text-align:center">Case No</th>
						<th style="text-align:center">Petitioner vs Respondent</th>
						<th style="text-align:center">Petitioner Council</th>
						<th style="text-align:center">Respondent Council</th>
						<th style="text-align:center">Court No.</th>
						<th>Select All
							<input id="{{data.cl_id}}" type="checkbox" value="{{data.cl_id}}" name="cl_id" ng-click="checkAll()" ng-model="selectedAll" />
						</th>
						<th>Action</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in displayedCollection">
						<td>{{data.cl_serial_no}}</td>
						<td style="text-align:center;cursor: pointer;text-decoration: underline;" ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</td>
						<td style="text-align:center">{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
						<td style="text-align:center">{{data.courtMaster.cm_name}}</td>
						<td>
                        	<input type="checkbox" name="checked" id="checked" value={{data.cl_id}} ng-model="data.checked" />
                         </td>
						<td>
							<button type="button" class="btn btn-sm btn-success" ng-click="deletecase(data.cl_id)">Delete</button>
							
						</td>
						</tr>
						<tr ng-if="displayedCollection.length==0 && search" >
							<td colspan="8" class="alert alert-danger">No Records Found</td>
						</tr>
						</tbody>
						</table>
					</div>
					<div class="modal fade" id="causeListCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong> Upload Cause List </strong></h4>
						      </div>	     
					  			<%@ include file="../causelist/_master_form.jsp"%>
						    </div>
						  </div>
					</div>
					<div class="modal fade" id="causeListUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong>Update Cause List </strong></h4>
						      </div>	     
					  			<%@ include file="../causelist/_update_causelist.jsp"%>
						    </div>
						  </div>
					</div>
					<div class="modal fade" id="caseAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong>Add Cause List </strong></h4>
						      </div>	     
					  			<%@ include file="../causelist/_add_case.jsp"%>
						    </div>
						  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CauseListController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</body>
</html>