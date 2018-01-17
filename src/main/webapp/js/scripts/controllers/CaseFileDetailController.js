var EDMSApp = angular.module("EDMSApp", ['smart-table']);


EDMSApp.controller("CaseFileCtrl",	function($scope, $http) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.casefile={};
	$scope.doc_id= $('#doc_id').val();
	
	getSubDocuments();
	getCaseFileDetails();
	getOrderReports();
	function getSubDocuments(){
		$http.get(urlBase+'casefile/getsubdocuments/'+$scope.doc_id).success(function (data) {
	    	$scope.subDocuments=data.modelList;
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
		window.open(urlBase+'casefile/subdocument/'+sd_id,'_self');
	};
	$scope.viewAllOrders=function(){
		window.open(urlBase+'casefile/vieworders/'+$scope.doc_id,'_self');
	};
	$scope.showHighCourtCase=function(io_id){
		window.open(urlBase+'casefile/impugnedorder/'+io_id,'_self');
	};
	$scope.deletesubdocument=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletesubdocument/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deleteofficereport=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deleteofficereport/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletepetitioner=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletepetitioner/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deleterespondent=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deleterespondent/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletepcounsel=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletepcounsel/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletercounsel=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletercounsel/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();
		   		});	
		  }
	  }
});