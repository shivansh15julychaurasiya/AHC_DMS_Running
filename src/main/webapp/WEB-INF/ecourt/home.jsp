<jsp:include page="../content/header2.jsp"></jsp:include>

<
<style>
<!--
.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
	margin-right: auto;
	margin-left: auto;
}
-->
</style>




<div id="content" class="content" ng-controller="ECourtHomeCtrl">


<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/5">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Fresh Cases</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{freshcount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/5">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
		</a>
	</div>



	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/3">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Daily Cause List</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{dailycount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/3">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/1">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 14px;">Daily IA</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{applicationcount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/1">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/2">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Correction Application</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{correctioncount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/2">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/35">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">'As-Fresh' List</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{putFreshcount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/35">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
		</a>
	</div>
	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/4">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Backlog</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{backlogcount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/4">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/6">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Fresh Supplementry</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{supplementarycount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/6">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/7">
				<div class="widget widget-stats bg-green">
					<div class="stats-icon">
						<i class="fa fa-desktop"></i>
					</div>
					<div class="stats-info">
						<h4 style="font-size: 15px;">Additional/Unlisted</h4>
						<h4 style="font-size: 15px;">Total Case Listed :
							{{additionalcount}}</h4>
					</div>
			</a>
			<div class="stats-link">
				<a target="_new" href="/dms/causelist/type/7">View Detail <i
					class="fa fa-arrow-circle-o-right"></i></a>
			</div>
		</div>
<br><br><br>
	</div>



<div class="container-fluid">
  <div class="row">
    <div class="col-md-3 col-sm-6 d-flex" ng-repeat="item in removedAdditional">
      <div class="widget widget-stats bg-green w-75 d-flex flex-column"
        style="display:flex; flex-direction:column; justify-content:space-between; height:120px;width:238px;">
        
        <div class="stats-icon">
          <i class="fa fa-desktop"></i>
        </div>

        <div class="stats-info flex-grow-1">
          <h4 style="font-size:15px;">{{item.listTypeName}}</h4>
          <h4 style="font-size:15px;">Total Case Listed: {{item.count}}</h4>
        </div>

        <div class="stats-link">
          <a target="_new" href="/dms/causelist/type/{{item.cl_list_type_mid}}">
            View Detail <i class="fa fa-arrow-circle-o-right"></i>
          </a>
        </div>

      </div>
    </div>
  </div>
</div>




	



<!-- 				                                                 <button id="doownloadCauseList" type="button" class="btn btn-primary btn-sm" ng-click="doownloadCauseList()">Cause List</button>
 -->

<!-- Sushant -->

<!-- 		<div class="col-md-3 col-sm-6">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4>Click To view Previous Date Causlist</h4>
					<h4></h4>
				</div>
				<div class="stats-link">
					<a targert="_new" ng-click="show()">View Detail <i
						class="fa fa-arrow-circle-o-right"></i></a>
				</div>
			</div>
		</div> -->

<%-- <div class="modal fade" id="user_Modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<span ng-if="!masterentity.um_id"><strong> Calendar</strong></span>
			</div>
			<%@ include file="../ecourt/calender_form.jsp"%>
		</div>
	</div>
</div> --%>
<div class="container-fluid" ng-show="divShow">
	<label class="control-label col-md-4">Date of Decision: <span
		class="text-danger"> * </span></label>
	<div class="col-md-4">
		<input type="text" class="form-control" datepicker-popup="{{format1}}"
			name="fromDate1" ng-change="getCauseList(cause_list_date)"
			ng-model="cause_list_date" is-open="fromDate1" max-date="maxDate"
			datepicker-options="dateOptions" ng-disabled="true"
			date-disabled="disabled(date, mode)" close-text="Close"
			show-button-bar="false" /> <span class="input-group-addon"
			ng-click="open1($event,'fromDate1')"><i
			class="glyphicon glyphicon-calendar"></i></span>
	</div>





</div>

</div>

<div style="position: absolute;">
	<marquee width=100% direction="left">
		<font size="2" face="verdana" color="red"> The list (in new
			format) have been populated based on CCMS data. Any changes/
			modification in case listing data in CCMS, the same will be reflected
			in e-Court also.</font>
	</marquee>
</div>


</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/EcourtHomeController.js?v=9"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>
</html>