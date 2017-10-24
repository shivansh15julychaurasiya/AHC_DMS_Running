var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask']);
EDMSApp.directive('loading', ['$http', function ($http) {
    return {
        restrict: 'A',
        link: function (scope, elm, attrs) {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function (v) {
                if (v) {
                    elm.show();
                } else {
                    elm.hide();
                }
            });
        }
    };
}]);



EDMSApp.controller('CaseFileController',['$scope','$http','Upload',function ($scope, $http,Upload) {
	  var urlBase="/dms/";
	  $scope.picFile='';
	  $scope.caseTypes=[];
	  $scope.search={};
	  $scope.subdocument={};
	  getCaseTypes();
	  getIndexFields();


	  function getCaseTypes(){
		  $http.get(urlBase+'master/getcasetypes').success(function (data) {
		    		$scope.caseTypes=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  function getIndexFields(){
		  $http.get(urlBase+'master/getindexfields').success(function (data) {
		    		$scope.indexFields=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting indexfields");
		      });
	  }
	  $scope.setModel=function(casefile){
		  $scope.casefile=casefile;
	  }
	  $scope.searchCaseFiles=function() 
	  {
		  $http.post(urlBase+'casefile/getCaseFileList',$scope.casefile).success(function (data) {
		    	if(data.response=="TRUE")
		    		$scope.caseFileList=data.modelList;		    	
		    	else
		    		$scope.caseFileList=[];
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	  }	  
	  $scope.save=function() 
	  {
		  $scope.subdocument.sd_if_mid=$scope.if_id;
		  $scope.subdocument.sd_fd_mid=$scope.casefile.fd_id;
		  
			  var file=$scope.picFile;
			  
			    file.upload = Upload.upload({
			      url: urlBase + 'casefile/uploadApplication',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	    		   file:file,
	    		   fields:$scope.subdocument,
			    });

			    file.upload.then(function (response) {
			        if(response.response=="TRUE"){
			        	$scope.errorlist =null;
			        	alert("Successfully uploaded document");
			        	$("#uploadDocument").modal("hide");
			        	//window.location.reload();
			        }else{
			        	$scope.errorlist = response.data.dataMapList;
			        }
			      }, function (response) {
			        
			      }, function (evt) {
			        // Math.min is to fix IE which reports 200% sometimes
			        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			      });

			      file.upload.xhr(function (xhr) {
			        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			      });
			} 
	  $scope.downloadFiles=function(id){
			window.open(urlBase+"casefile/downloadfiles/"+id,"_self");
		}
	  $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_self");
	  }
}]);