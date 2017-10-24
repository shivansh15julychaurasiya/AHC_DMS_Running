<div class="modal-body">
		<div class="panel-body">
			<div class="table-responsive">
                <table id="data-table" class="table table-striped table-bordered">
	                <thead>
	                      <tr>
	                            <th>Diary No</th>
	                             <th>Stage</th>
	                             <th>Date</th>                                                                        
	                      </tr>
	                </thead>
	                <tbody>
	                      <tr ng-repeat="row in caseHistory" class="odd gradeX">
                             <td>{{casefile.rcd_diary_no}}</td>
                             <td>{{row.lkStage.lk_longname}}</td>  
                             <td>{{row.rcs_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
	                      </tr>
	                </tbody>
            	</table>
        	</div>
		</div>
</div>
