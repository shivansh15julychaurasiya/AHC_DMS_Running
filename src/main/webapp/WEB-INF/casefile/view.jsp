<jsp:include page="../content/header2.jsp"></jsp:include>

	<div id="content" class="content">
		<div class="container-fluid" ng-controller="CaseFileCtrl" >
			<div class="row">
				<div class="panel panel-inverse">
					<div class="panel-heading">
					<div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>			            
			        </div>
	                     <h4 class="panel-title">Case File Details {{casefile.caseType.ct_label}}/{{casefile.fd_case_no}}/{{casefile.fd_case_year}}</h4>
	                </div>
	                <div class="panel-body">
	                	<input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id"> 
						<input type="hidden" class="form-control" value=${document_name} id="document_name" name="document_name">
						<div class="col-md-5">
							<jsp:include page="treeview.jsp"></jsp:include>
							
						</div>
						<div class="col-md-7">
							<jsp:include page="viewer.jsp"></jsp:include>
						</div>
	                </div>
	            </div>
			</div>
		</div>
	</div>
</div>
	
</body>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileViewController.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>

	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>