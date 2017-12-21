<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.order{
background: #f8b2b2
}
.ofr{
background: #93cfe5;
}
.str{
background: #f0f3f5;
}
</style>
 <button class="btn btn-success btn-sm" ng-click="viewAllOrders()">View All Orders</button>
<c:if test="${isApplication==1}">
<div class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
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
                                   <th>Name</th>
                                   <th>Counsel</th>
                                </tr>
                                <tr>
                                 <td> ${application_type} <br/> ${application_no}/ ${application_year} <br/> ${submitted_date} </td>
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
<div  ng-show="orderData.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseORDER">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Order / Office report
					</a>
               </h3>
           </div>
           <div id="collapseORDER" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="orderData" st-safe-src="orderDataList" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort-default="true" st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Description</th>
                                   <th  st-sort="sd_counsel">Name</th>
                                </tr>
                                <tr ng-repeat="data in orderData" ng-class="data.document_type == 'ORD' ? 'order' : (data.document_type == 'Off. Rep.' ? 'ofr' : 'str')">
                                 <td style="padding:10px 5px;width:35%">
                                 <span  ng-if="data.sd_id!=null" ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;"><b>{{data.document_type}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></span>
                                 <span  ng-if="data.sd_id==null"><b>{{data.document_type}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></span>
                                 </td>
                                 <td  style="text-align:justify"> {{data.ord_remark}}</td>
                                 <td>{{data.sd_description}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
<div  ng-show="petitions.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Petition
					</a>
               </h3>
           </div>
           <div id="collapseOne" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="petitions" st-safe-src="petitionsData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in petitions">
                                 <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}} <br/>  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         		</tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
<div ng-show="coun_affidavits.length > 0"  ng-show="coun_affidavits.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Counter
					</a>
               </h3>
           </div>
           <div id="collapseFive" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="coun_affidavits" st-safe-src="coun_affidavitsData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in coun_affidavits">
                                 <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
<div  ng-show="rejoinders.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Rejoinder
					</a>
               </h3>
           </div>
           <div id="collapseTwo" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="rejoinders" st-safe-src="rejoindersData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in rejoinders">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}}  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
<div  ng-show="supp_affidavits.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary
					</a>
               </h3>
           </div>
           <div id="collapseFour" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="supp_affidavits" st-safe-src="supp_affidavitsData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_affidavits">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}}  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>

<div  ng-show="supp_coun_affidavits.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary Counter
					</a>
               </h3>
           </div>
           <div id="collapseSix" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="supp_coun_affidavits" st-safe-src="supp_coun_affidavitsData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_coun_affidavits">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}}  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>

<div  ng-show="supp_rejoinders.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Supplimentary Rejoinder
					</a>
               </h3>
           </div>
           <div id="collapseSeven" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="supp_rejoinders" st-safe-src="supp_rejoindersData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in supp_rejoinders">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}}  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         </tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>


<div  ng-show="applications.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Misc. Applications
					</a>
               </h3>
           </div>
           <div id="collapseThree" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="applications" st-safe-src="applicationsData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in applications">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.documentType.at_name}} <br/> {{data.sd_document_no}} /{{data.sd_document_year}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>                                 
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         		</tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
<div  ng-show="others.length > 0" class="panel panel-inverse overflow-hidden">
           <div class="panel-heading">
               <h3 class="panel-title">
                   <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">
				    <i class="fa fa-plus-circle pull-right"></i> 
					Others
					</a>
               </h3>
           </div>
           <div id="collapseEight" class="panel-collapse collapse in">
               <div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   <div class="table-responsive">
                       <table id="data-table" st-table="others" st-safe-src="othersData" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                                   <th st-sort="sd_submitted_date">Type</th>
                                   <th st-sort="sd_description">Name</th>
                                   <th  st-sort="sd_counsel">Counsel</th>
                                </tr>
                                <tr ng-repeat="data in others">
                                 <td  ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.documentType.at_name}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>                                 
                                 <td style="text-align:justify">{{data.sd_description}}</td>
                                 <td>{{data.sd_counsel}}</td>
                         		</tr>
                    </thead>
                    
                </table>
            </div>
        </div>
    </div>
</div>
</div>
