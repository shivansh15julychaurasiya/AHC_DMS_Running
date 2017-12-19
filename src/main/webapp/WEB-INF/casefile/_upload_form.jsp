
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
	<div class="modal-body">
		<div ng-if="errorlist" class="alert alert-block alert-danger">
				<ul>
					<span ng-repeat="errors in errorlist "> <span
						ng-repeat="n in errors track by $index">
							<li>{{(n)}}</li>
					</span>
					</span>
				</ul>
		</div>
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">Select Field Type<span class="star">*</span></label>
				<select ng-model="subdocument.if_id" ng-change="getApplications()"
			 		ng-options="i.if_id as i.if_label for i in index_fields  | orderBy:'if_label'">
					<option value="">Select Field Type</option>
				</select>
			</div>
		</div>
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">Document Type<span class="star">*</span></label>
				<select  ng-model="subdocument.at_id"
			 		ng-options="at.at_id as at.at_name for at in applications  | orderBy:'at_name'">
					<option value="">Select Document Type</option>
				</select>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group" ng-show="subdocument.at_id!=100003">
				<label class="control-label col-md-4">Date of Decision: <span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="sd_submitted_date" is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" close-text="Close" show-button-bar="false" />
					<span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="row"  ng-show="subdocument.if_id!=39">
			<div class="form-group">
				<label class="control-label col-md-4">Application No.<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="sd_document_no" id="sd_document_no" ng-model="subdocument.sd_document_no" class="form-control col-md-4"/>
				</div>
			</div>
		</div>
		<div class="row" ng-show="subdocument.if_id!=39">
			<div class="form-group">
				<label class="control-label col-md-4">Application Year<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="sd_document_year" id="sd_document_year" ng-model="subdocument.sd_document_year" class="form-control col-md-4"/>
				</div>
			</div>
		</div>
		<div class="row" ng-show="subdocument.if_id==39 && subdocument.at_id!=100002">
			<div class="form-group">
				<label class="control-label col-md-4">Order Remark<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<textarea name="order_date" id="order_date" ng-model="subdocument.ord_remark" class="form-control col-md-4"></textarea>
				</div>
			</div>
		</div>	
		<div class="row">							  			      		
				<div class="form-group">
							<label class="col-md-4 control-label" for="file">File<span class="star">*</span></label>
							<input type="file" ngf-select ng-model="picFile" name="file"  >
				 </div>
		</div>
	
	</div>
	<div class="modal-footer"> 
		<div>
		<input type="submit" value="Submit" ng-disabled="masterForm.$invalid" ng-click="save()"   class="btn btn-success"/>      
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
		</div>	     
	</div>
</form>
