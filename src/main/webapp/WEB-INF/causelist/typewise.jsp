
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
		          <h4 class="panel-title">${listType.getClt_description()} Cause List</h4>
		       </div>
				<div class="panel-body">
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Sl No.</th>
						<th style="text-align:center">Case No</th>
						<th style="text-align:center">Petitioner vs Respondent</th>
						<th style="text-align:center">Petitioner Council</th>
						<th style="text-align:center">Respondent Council</th>
						<th style="text-align:center">Action</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in displayedCollection">
						<td>{{data.cl_serial_no}}</td>
						<td style="text-align:center;cursor: pointer;text-decoration: underline;">
						
						<span ng-if="data.listType.clt_name=='A' || data.listType.clt_name=='C'" ng-click="viewCaseFile(data.cl_fd_mid)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/></span><span ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						
						</td>
						<td style="text-align:center">{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
						<td style="text-align:center" >
							<button type="button" ng-if="data.cl_sequence==null" class="btn btn-primary btn-sm" ng-click="updatePriority(data.cl_id)">Update Priority</button>
						</td>
						</tr>
						</tbody>
						<tfoot>
									<tr>
										<td colspan="5" class="text-center">
											<div st-pagination="" st-items-by-page="5000"  st-displayed-pages="4" ></div>
										</td>
									</tr>
						</tfoot>
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