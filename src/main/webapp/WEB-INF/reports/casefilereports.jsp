<%@ include file="../content/header2.jsp"%>

<div id="content" class="content">
	<div class="container-fluid" style="background-color:white;" ng-controller="caseFileReportController" ng-init="getCaseTypes();">
<button id="btnPrint" class="btn btn-success">Print</button>
		<div id="report_table" style="width: 500;height: 600;">
		
		<!-- <div class="container"> -->
  <h2>Case File Reports Graphical Representation</h2>
   </br>
            </br>
   
  <!-- <p>The panel-group class clears the bottom-margin. Try to remove the class and see what happens.</p> -->
  
    <!--       <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
  <div class="btn-group mr-2" role="group" aria-label="First group">
    <button type="button" class="btn btn-primary" ng-click="caseStageReport()" id ="caseStage">Case Stage Report</button>
    <button type="button" class="btn btn-primary" ng-click="cavStageReport()" id ="cavStage">Caveat Stage Report</button>
    <button type="button" class="btn btn-primary" ng-click="appStageReport()" id ="appStage">Application Stage Report</button>
     </div>
     </div>
     </br>
           </br>
            </br> -->
     
    <!-- <div class="row">
          <div class="col-md-2 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>STAGE</th>
            <th>COUNT</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportData">
          <tr ng-repeat="data in caseStageReport" >
            <td>{{data.stage}}</td>
             <td>{{data.count}}</td>
           
        </tr>
        
                 
    </tbody>
</table>
</div>

        <div class="col-md-9 ">
         <canvas id="myChart7" ></canvas>
         </div>
          </div> -->
    <!-- <button type="button" class="btn btn-primary">4</button> -->
 
  
  
   <!--  <div class="panel panel-primary">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div> -->
  

		
       <!--  <canvas id="myChart1" ></canvas>
     
        <canvas id="myChart2" ></canvas> -->
       <!--  </br>
           </br>
            </br> -->
        <div  class="border border-info" id="target">
         <div class="panel panel-primary" >
           <div class="panel-heading">Total Application, Caveat and Case Report	 </div>
             <div class="panel-body">
        <div class="row">
          <div class="col-md-2 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>NAME</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
      <!--   <tr ng-repeat="data in caseFileReportData"> -->
          <tr>
            <td>Application</td>
             <td>{{caseFileReportData.applicationCount}}</td>
           
        </tr>
        <tr>
         <td>Caveat</td>
            <td>{{caseFileReportData.caveatCount}}</td>
        </tr>
        <tr>
         <td>Case</td>
            <td>{{caseFileReportData.fileCount}}</td>
        </tr>
                 
    </tbody>
</table>
</div>

        <div class="col-md-9 ">
         <canvas id="myChart3" ></canvas>
         </div>
          </div>
             </div>
          </div>
          </div>
          
        </br>
           </br>
            </br>
             </br>   
          
          
         
          <div class="panel panel-primary" id ="target1">
           <div class="panel-heading">Total of Case Registration According to Case Types</div>
             <div class="panel-body">
           <div class="row">
           <div class="col-md-12">
         <canvas id="myChart4" ></canvas>
         </div>
            <div class="col-md-3 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>CASE TYPE</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportByType.slice(0, caseFileReportByType.length / 3)">
         
            <td>{{data.name}}</td>
             <td>{{data.count}}</td>
           
       
        </tr>
         
                 
    </tbody>
</table>
</div>

 <div class="col-md-3 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>CASE TYPE</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportByType.slice(caseFileReportByType.length /3, caseFileReportByType.length * 2/3)">
         
            <td>{{data.name}}</td>
             <td>{{data.count}}</td>
           
       
        </tr>
         
                 
    </tbody>
</table>
</div>

      <div class="col-md-3 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>CASE TYPE</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportByType.slice(caseFileReportByType.length * 2/3, caseFileReportByType.length)">
         
            <td>{{data.name}}</td>
             <td>{{data.count}}</td>
           
       
        </tr>
         
                 
    </tbody>
</table>
</div>
        
          </div>
            </div>
          </div>
          
          <div class="panel panel-primary"  id="target3">
           <div class="panel-heading">Total of Case ,Application and Caveat Registration Periodically</div>
             <div class="panel-body">
          
           <div class="row">
          <div class="col-md-2">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>YEAR</th>
            <th>CASE COUNTS</th>
              <th>APP COUNTS</th>
                <th>CAVEAT COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
      <!--   <tr ng-repeat="data in caseFileReportData"> -->
         <tr ng-repeat="data in caseFileReportByYear">
         
            <td>{{data.year}}</td>
             <td>{{data.casecount}}</td>
             <td>{{data.appcount}}</td>
             <td>{{data.caveatcount}}</td>
           
       
        </tr>
                 
    </tbody>
</table>
</div>
        <div class="col-md-8 col-md-offset-2 ">
         <canvas id="myChart5" ></canvas>
         </div>
          </div>
           </div>
          </div>
          
          </br>
           </br>
            </br>
             </br>
          
       
         </div>
         </div>
    </div>
    </div>
    </div>
    </div>

<!-- </div> -->
<!-- end row -->
</body>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/caseFileReportController.js"></script>	
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/angularJs/dirPagination.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/chartModule/Chart.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
<script>
	$(document).ready(function() {
		App.init();
	});
	
	
	document.getElementById("btnPrint").onclick = function() 
	{

		 
		
			  var pdf = new jsPDF('p', 'pt', 'a4');
		       
		           return html2canvas($('#target'), {
			            background: "#ffffff",
			            onrendered: function(canvas) {
			            	
			            	html2canvas($('#target1'), {
			    	            background: "#ffffff",
			    	            onrendered: function(canvas2) {
			    	            	
			    	            	html2canvas($('#target3'), {
					    	            background: "#ffffff",
					    	            onrendered: function(canvas3) {
			            	
			            	
			                var myImage = canvas.toDataURL("image/jpeg,1.0");
			                
			               
			                // Adjust width and height
			                var imgWidth =  (canvas.width * 60) / 240;
			                var imgHeight = (canvas.height * 70) / 240;
			              /*  var imgWidth =doc.internal.pageSize.getWidth();;
			                var imgHeight =doc.internal.pageSize.getHeight();*/
			                // jspdf changes
			                
			                pdf.addImage(myImage, 'JPEG', 30,50,555,400); // 2: 19
			                
			                var myImage2 = canvas2.toDataURL("image/jpeg,1.0");
			                
			                var myImage3 = canvas3.toDataURL("image/jpeg,1.0");
			                
			                pdf.addPage();
			                
			                pdf.addImage(myImage2, 'JPEG', 30,10,555,800); // 2: 19
			                
                             pdf.addPage();
			                
			                pdf.addImage(myImage3, 'JPEG', 30,10,555,450); // 2: 19
			                pdf.save('test.pdf');
			            }
			        });
			            	
			            }
			        });
			            	
			            }
			          });
		       
		  
		  console.log($("#target").width()+"  "+$("#target").height());
		  //$scope.recipient="Sushant";
		 
		
	  
	}
	
</script>



</html>