var EDMSApp = angular.module("EDMSApp", ['smart-table']);


EDMSApp.controller("CaseFileCtrl",	function($scope, $http) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.metadata=[];
	$scope.doc_id= $('#doc_id').val();
	//$scope.judgmentdocId= $('#judgmentdocId').val();
	
	//alert(2);
	$scope.document_name= $('#document_name').val();
	//alert($scope.fd_file_bar_code);
	var url = urlBase+'uploads/' + $scope.document_name+".pdf";
	DEFAULT_URL = url;
	
   // alert(DEFAULT_URL);
	$scope.petitions=[];
	$scope.petitionsData=[];
	$scope.rejoinders=[];
	$scope.rejoindersData=[];
	$scope.applications=[];
	$scope.applicationsData=[];
	$scope.supp_affidavits=[];
	$scope.supp_affidavitsData=[];
	$scope.coun_affidavits=[];
	$scope.coun_affidavitsData=[];
	$scope.supp_coun_affidavits=[];
	$scope.supp_coun_affidavitsData=[];
	$scope.supp_rejoinders=[];
	$scope.supp_rejoindersData=[];
	$scope.order_sheets=[];
	$scope.order_sheetsData=[];
	$scope.office_reports=[];
	$scope.office_reportsData=[];
	
	getSubDocuments();
	getCaseFileDetails();
	
	function getSubDocuments(){
		$http.get(urlBase+'casefile/getsubdocuments/'+$scope.doc_id).success(function (data) {
	    	$scope.subDocuments=data.modelList;
	    	angular.forEach($scope.subDocuments, function(value, key) {
	    		  
	    		  $scope.subdocument=value;
	    		  var type=$scope.subdocument.indexField.if_name;
	    		  switch(type){
	    		  case'petition':
	    			  $scope.petitions.push($scope.subdocument);
	    			  break;
	    		  case'rejoinder':
	    			  $scope.rejoinders.push($scope.subdocument);
	    			  break;
	    		  case'application':
	    			  $scope.applications.push($scope.subdocument);
	    			  break;
	    		  case'supp_affidavit':
	    			  $scope.supp_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'coun_affidavit':
	    			  $scope.coun_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'supp_coun_affidavit':
	    			  $scope.supp_coun_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'supp_rejoinder':
	    			  $scope.supp_rejoinders.push($scope.subdocument);
	    			  break;
	    		  case'order_sheet':
	    			  $scope.order_sheets.push($scope.subdocument);
	    			  break;
	    		  }
	    		  getOrderReports();
	    		});
	    	$scope.petitionsData = [].concat($scope.petitions);
	        $scope.rejoindersData = [].concat($scope.rejoinders);
	        $scope.applicationsData = [].concat($scope.applications);
	        $scope.supp_affidavitsData = [].concat($scope.supp_affidavits);
	        $scope.coun_affidavitsData = [].concat($scope.coun_affidavits);
	        $scope.supp_coun_affidavitsData = [].concat($scope.supp_coun_affidavits);
	        $scope.supp_rejoindersData = [].concat($scope.supp_rejoinders);
	        
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getCaseFileDetails(){
		$http.get(urlBase+'casefile/getcasefiledetails/'+$scope.doc_id).success(function (data) {
	    	$scope.casefile=data.modelData;
	    	  
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getOrderReports(){
		$http.get(urlBase+'casefile/getorderreports/'+$scope.doc_id).success(function (data) {
	    	$scope.orderReports=data.modelList;
	    	generateReportData();
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function generateReportData(){
		$scope.orderData=[];
		angular.forEach($scope.order_sheets, function(value, key) {
			$scope.ordermodel={'sd_id':value.sd_id,'document_type':value.documentType.at_description,'sd_created_date':value.sd_cr_date,'sd_submitted_date':value.sd_submitted_date,'sd_party':value.sd_party,'sd_description':value.sd_description,'ord_remark':''};
			$scope.orderData.push($scope.ordermodel);
		});
		angular.forEach($scope.orderReports, function(value, key) {
			var sd_id=null;
			if(value.subDocument!=null)
				sd_id=value.subDocument.sd_id;
			
				$scope.ordermodel={'sd_id':sd_id,'document_type':'OFFICE REPORT','sd_created_date':value.ord_created,'sd_submitted_date':value.ord_created,'sd_party':'','sd_description':'','ord_remark':value.ord_remark};
				$scope.orderData.push($scope.ordermodel);
			});
		//alert(JSON.stringify($scope.orderData));
		$scope.orderDataList = [].concat($scope.orderData);
	}
	$scope.showSubDocument=function(sd_id){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'casefile/subdocument/'+sd_id,'_self');
	};
});