<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Petition
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
                                <tr ng-repeat="data in petitions">
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
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Rejoinder
					</a>
               </h3>
           </div>
           <div id="collapseTwo" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in rejoinders">
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
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Applications
					</a>
               </h3>
           </div>
           <div id="collapseThree" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in applications">
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
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary affidavits
					</a>
               </h3>
           </div>
           <div id="collapseFour" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in supp_affidavits">
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

<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Counter affidavits
					</a>
               </h3>
           </div>
           <div id="collapseFive" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in coun_affidavits">
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

<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary counter affidavit
					</a>
               </h3>
           </div>
           <div id="collapseSix" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in supp_coun_affidavits">
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

<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary rejoinder
					</a>
               </h3>
           </div>
           <div id="collapseSeven" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in supp_rejoinders">
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

<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Order Sheet
					</a>
               </h3>
           </div>
           <div id="collapseEight" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in order_sheets">
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
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseNine">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Order report
					</a>
               </h3>
           </div>
           <div id="collapseNine" class="panel-collapse collapse in">
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
                                <tr ng-repeat="data in office_reports">
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



