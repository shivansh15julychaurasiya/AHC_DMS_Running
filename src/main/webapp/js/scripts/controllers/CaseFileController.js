var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);
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

	  $scope.open1 = function($event,type) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    
		    if(type=="fromDate1")
		    	$scope.fromDate1= true;
		    if(type=="toDate1")
		    	$scope.toDate= true;
		};
	
		$scope.toggleMax = function() {
		    //$scope.minDate = $scope.minDate ? null : new Date();
			$scope.maxDate = new Date();
		};
		$scope.toggleMax();
		
		$scope.open = function($event,type) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    
		    if(type=="fromDate")
		    	$scope.fromDate= true;
		    if(type=="toDate")
		    	$scope.toDate= true;
		};
		
		$scope.dateOptions = {
		    formatYear: 'yy',
		    startingDay: 1
		    
		};
		
		$scope.formats = ['dd-MMMM-yyyy','dd-mm-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
		$scope.format = $scope.formats[3];
		
		function convertDate(inputFormat) 
		{debugger
			  function pad(s) { return (s < 10) ? '0' + s : s; }
			  var d = new Date(inputFormat);
			  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
		}

	  
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
		  
		  if($scope.sd_submitted_date!=null){
			  $scope.sd_submitted_date=convertDate($scope.sd_submitted_date);
			}
		  $scope.subdocument.sd_submitted_date=$scope.sd_submitted_date;
		  
		  
			  var file=$scope.picFile;
			  
			    file.upload = Upload.upload({
			      url: urlBase + 'casefile/uploadJudgement',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	    		   file:file,
	    		   fields:$scope.subdocument,
			    });

			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
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