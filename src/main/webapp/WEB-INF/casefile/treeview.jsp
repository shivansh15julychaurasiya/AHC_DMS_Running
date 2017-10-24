<div class="panel panel-inverse overflow-hidden">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Sub Documents
									</a>
                        </h3>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Type</th>
                                            <th>Party</th>
                                            <th>Name</th>
                                            <th>Date</th>
                                            <th>Link</th>
                                         </tr>
                                         <tr ng-repeat="data in subDocuments">
	                                         <td>{{data.indexField.if_label}}</td>
	                                         <td><span ng-if="data.sd_party=='P'">Petitioner</span>
	                                         	<span ng-if="data.sd_party=='R'">Respondent</span>
	                                         	<span ng-if="data.sd_party=='O'">Other</span>
	                                         </td>
	                                         <td>{{data.sd_description}}</td>
	                                         <td>{{data.sd_submitted_date | date:'dd/MM/yyyy'}}</td>
	                                         <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">View Document</td>
                                         </tr>
                                    </thead>
                                    
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
