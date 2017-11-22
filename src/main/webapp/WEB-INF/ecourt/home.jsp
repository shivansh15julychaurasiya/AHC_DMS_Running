<jsp:include page="../content/header2.jsp"></jsp:include>

	<div id="content" class="content">
		<div class="container-fluid" ng-controller="ECourtHomeCtrl" >
		
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Application</h4>
						<h4>{{applicationcount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/1">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Daily</h4>
						<h4>{{dailycount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/3">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Correction</h4>
						<h4>{{correctioncount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/2">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Backlog</h4>
						<h4>{{backlogcount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/4">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Fresh</h4>
						<h4>{{freshcount}}</h4>
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/5">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>			
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Supplementry</h4>
						<h4>{{supplementarycount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/6">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon"><i class="fa fa-desktop"></i></div>
					<div class="stats-info">
						<h4>Additional</h4>
						<h4>{{additionalcount}}</h4>								
					</div>
					<div class="stats-link">
						<a targert="_new" href="/dms/causelist/type/7">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
					</div>
				</div>
			</div>			
		</div>
	</div>
</div>
	
</body>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/scripts/controllers/EcourtHomeController.js"></script>

	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>