var EDMSApp = angular.module("EDMSApp", ['treeControl']);


EDMSApp.controller("CaseFileCtrl",	function($scope, $http, $document) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.metadata=[];
	$scope.doc_id= $('#doc_id').val();
	$scope.judgmentdocId= $('#judgmentdocId').val();
	
	//alert(2);
	$scope.document_name= $('#document_name').val();
	//alert($scope.fd_file_bar_code);
	var url = urlBase+'uploads/' + $scope.document_name+".pdf";
	DEFAULT_URL = url;
	
   // alert(DEFAULT_URL);
	getSubDocuments();
	function getSubDocuments(){
		$http.get(urlBase+'casefile/getsubdocuments/'+$scope.doc_id).success(function (data) {
	    	$scope.subDocuments=data.modelList;
	    	  
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	
	$scope.showSubDocument=function(sd_id){
		window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
	};
});
