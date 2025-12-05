
<form class="form-horizontal reduce-gap" name="masterForm" role="form" novalidate>      
<div class="modal-body">
<div ng-show="errorlist" class="alert alert-block alert-danger">
	<ul>
    <span ng-repeat="errors in errorlist"  >
	        <span ng-repeat="n in errors track by $index">
	        	<li>{{(n)}}</li>
	        </span>
	    </span>
    </ul>
</div>

<div class="row">
	<div class="col-md-6">
	<div class="form-group">
			<label class="col-md-4 control-label" for="um_fullname">Bench Number<span class="star">*</span></label>
		    <div class="col-md-7">
						<input class="form-control" required type="text" id="becnhId"
							name="becnhId" ng-model="masterentity.cm_bench_id"
							placeholder="Bench Number"  ng-readonly="true" />
					</div>		   
	</div>
	</div>	
	
	
	
	
	
	<div class="col-md-6">
		<div class="form-group">
				<label class="col-md-4  control-label" for="um_vendor_id">Status <span class="star">*</span></label>
			    <div class="col-md-7">
		      		<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="masterentity.clDate" is-open="fromDate1"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" close-text="Close" show-button-bar="false" />
                    <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>		   
		</div>
	</div>

		
</div>
</div>

<div class="modal-footer"> 
	<div ng-if="!masterentity.um_id">
			<input type="submit" value="Generate" id="create-masterForm" data-loading-text="Creating..." autocomplete="off" ng-click="nextCause(masterentity)" class="btn btn-success"/>      			
			<input type="submit" value="Transfer" id="create-masterForm" data-loading-text="Creating..." autocomplete="off" ng-click="nextTrans(masterentity)" class="btn btn-secondary"/> 
			<input type="submit" value="Correction" id="create-masterForm" data-loading-text="Creating..." autocomplete="off" ng-click="nextCorrection(masterentity)" class="btn btn-primary"/>      			
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
	
	
	     
</div>

</form>

 