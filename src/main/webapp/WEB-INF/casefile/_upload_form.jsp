
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
				<label class="col-md-4 control-label">Document Type<span class="star">*</span></label>
				<select  ng-model="at_id"
			 		ng-options="at.at_id as at.at_name for at in applications  | orderBy:'at_name'">
					<option value="">Select Document Type</option>
				</select>
			</div>
		</div>
		
		<div class="row" ng-show="at_id==100001 || at_id==100002">
			<div class="form-group">
				<label class="control-label col-md-4">Date of Decision: <span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="sd_submitted_date" is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" close-text="Close" show-button-bar="false" />
					<span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="row" >
			<div class="form-group">
				<label class="control-label col-md-4">Order Remark<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<textarea name="order_date" id="order_date" ng-model="ord_remark" class="form-control col-md-4"></textarea>
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
