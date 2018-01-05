<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../content/header2.jsp"></jsp:include>

	<div id="content" class="content">
		<div class="container-fluid" ng-controller="CaseFileCtrl" >
			<div class="row">
				<div class="panel panel-inverse" style="min-height:1000px;">
					<div class="panel-heading">
					<div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>			            
			        </div>
	                     <h4 class="panel-title">Case File Details {{casefile.caseType.ct_label}}/{{casefile.fd_case_no}}/{{casefile.fd_case_year}}</h4>
	                     <c:if test="${isImpugnedOrder==1}">
	                     <h4 class="panel-title">Impugned Order Details ${casetype}/${caseno}/${caseyear}</h4>
	                     </c:if>
	                </div>
	                <div class="panel-body">
	                	<input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id"> 
						<input type="hidden" class="form-control" value=${document_name} id="document_name" name="document_name">
						<div class="col-md-4">
							<jsp:include page="treeview.jsp"></jsp:include>
							
						</div>
						<div class="col-md-8">
							<jsp:include page="viewer.jsp"></jsp:include>
						</div>
	                </div>
	            </div>
			</div>
		</div>
	</div>
	<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
</div>
	
</body>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileViewController.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
	<style>
	 .st-sort-ascent:before {
	    content: '\25B2';
	  }
	
	  .st-sort-descent:before {
	    content: '\25BC';
	  }
	</style>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>