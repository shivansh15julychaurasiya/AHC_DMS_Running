
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
				<select  ng-model="if_id"
			 		ng-options="indexField.if_id as indexField.if_label for indexField in indexFields  | orderBy:'if_label'">
					<option value="">Select Document Type</option>
				</select>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Date of Decision: <span class="text-danger"> * </span></label>
			<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="sd_submitted_date" required is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
			<span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
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
