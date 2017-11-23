var EDMSApp = angular.module("EDMSApp", ['treeControl']);


EDMSApp.controller("CaseFileCtrl",	function($scope, $http, $document) {
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
	$scope.rejoinders=[];
	$scope.applications=[];
	$scope.supp_affidavits=[];
	$scope.coun_affidavits=[];
	$scope.supp_coun_affidavits=[];
	$scope.supp_rejoinders=[];
	$scope.order_sheets=[];
	$scope.office_reports=[];
	
	getSubDocuments();
	getCaseFileDetails();
	getOrderReports();
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
	    		});
	    	console.log($scope.petition);
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
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	
	$scope.showSubDocument=function(sd_id){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'casefile/subdocument/'+sd_id,'_self');
	};
});
