<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${isApplication==1}">
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Application
					</a>
               </h3>
           </div>
           <div id="collapseThree" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">

						<table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                
                                 <td> ${application_type}</td>
                                 <td><c:if test="${party=='P'}">Petitioner</c:if>
                                 <c:if test="${party=='R'}">Respondent</c:if>
                                 <c:if test="${party=='O'}">Other</c:if>
                                  </td>
                                 <td>${name}</td>
                                 <td>${counsel}</td>
                         		</tr>
                    </thead>
                    
                </table>
                </div>
          </div>
    </div>
</div>
</c:if>
<div class="panel-group" id="accordion">
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseORDER">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Office report
					</a>
               </h3>
           </div>
           <div id="collapseORDER" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Notice</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                </tr>
                                <tr ng-repeat="data in orderData | orderBy :'sd_created_date'">
                                 <td>
                                 <span  ng-if="data.sd_id!=null" ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.document_type}} ( {{data.sd_created_date | date:'dd/MM/yyyy'}})</span>
                                 <span  ng-if="data.sd_id==null">{{data.document_type}} ( {{data.sd_created_date | date:'dd/MM/yyyy'}})</span>
                                 </td>
                                 <td> {{data.ord_remark}}</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
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
                   <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Petition
					</a>
               </h3>
           </div>
           <div id="collapseOne" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                </tr>
                                <tr ng-repeat="data in petitions">
                                 <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in coun_affidavits">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in rejoinders">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_coun_affidavits">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_rejoinders">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_affidavits">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.indexField.if_label}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
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
               <div class="panel-body" style="padding:2px;">
                   <div class="table-responsive">
                       <table id="data-table" class="table table-striped table-bordered">
                           <thead>
                               <tr>
                                   <th>Type</th>
                                   <th>Party</th>
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr ng-repeat="data in applications">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;">{{data.documentType.at_name}} ( {{data.sd_submitted_date | date:'dd/MM/yyyy'}})</td>
                                 <td><span ng-if="data.sd_party=='P'">Petitioner</span>
                                 	<span ng-if="data.sd_party=='R'">Respondent</span>
                                 	<span ng-if="data.sd_party=='O'">Other</span>
                                 </td>
                                 <td>{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         		</tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
</div>
