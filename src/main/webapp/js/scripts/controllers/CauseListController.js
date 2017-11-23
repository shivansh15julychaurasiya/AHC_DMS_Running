/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','ngMask']);

//edmsApp.directive('fileModel', [ '$parse', function($parse) {
//    return {
//        restrict : 'A',
//        link : function(scope, element, attrs) {
//            var model = $parse(attrs.fileModel);
//            var modelSetter = model.assign;
//
//            element.bind('change', function() {
//                scope.$apply(function() {
//                    modelSetter(scope, element[0].files[0]);
//                });
//            });
//        }
//    };
//} ]);


/**
 * Controller in index.jsp
 */

edmsApp.controller("causeListController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
		$scope.model={};
	  $scope.invalidFiles = [];
	  $scope.type_id= $('#type_id').val();
	  $scope.$watch('files', function (files) {
	    $scope.formUpload = false;
	    if (files != null) {
	      if (!angular.isArray(files)) {
	        $timeout(function () {
	          $scope.files = files = [files];
	        });
	        return;
	      }
	      for (var i = 0; i < files.length; i++) {
	        $scope.errorMsg = null;
	        (function (f) {
	          $scope.upload(f, true);
	        })(files[i]);
	      }
	    }
	  });
	$scope.folder={};
	$scope.errorlist=null;
	$scope.picFile='';
    $scope.causeListTypes =[];
    $scope.masterdata=[];
    $scope.displayedCollection = []
    $scope.courtList=[];
    $scope.causelist={};
    $scope.search=false;
    getCourtList();
    getCauseListTypes();
    
    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
	$scope.format = $scope.formats[0];
    $scope.today = function() {
    	$scope.model.cl_dol = new Date();
	};
	if($scope.type_id!='' && $scope.type_id!=null){
		$scope.model.cl_list_type_mid=$scope.type_id;
		$scope.model.cl_dol=new Date();
		getData($scope.model);
	}
	$scope.today();
	
	$scope.clear = function () {
		$scope.model.cl_dol = null;
	};	

	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;
		  };
    	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};

    

	function getCauseListTypes() {
		var response = $http.get(urlBase+'causelist/getCauseListTypes');
		response.success(function(data, status, headers, config) {
			$scope.causeListTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getCourtList() {
		var response = $http.get(urlBase+'causelist/getCourtList');
		response.success(function(data, status, headers, config) {
			$scope.courtList = data.modelList;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	}
	$scope.getList=function(){
		getData();
	}
	function getData(){	
		$scope.search=true;
		$http.post(urlBase+'causelist/getCauseList',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
			//$scope.SearchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
	$scope.downloadList=function(){	
		var d = new Date($scope.model.cl_dol);
	  	var m=("0" + (d.getMonth() + 1)).slice(-2)
	    var year=d.getFullYear();
	    var date = d.getDate()+"-"+m+"-"+year;	    
		window.open(urlBase+"causelist/downloadfiles/?courtno="+$scope.model.cl_court_no+"&date="+date,"_blank");			
	};
    $scope.$watch('files', function (files) {
        $scope.formUpload = false;
        if (files != null) {
          if (!angular.isArray(files)) {
            $timeout(function () {
              $scope.files = files = [files];
            });
            return;
          }
          for (var i = 0; i < files.length; i++) {
            $scope.errorMsg = null;
            (function (f) {
              $scope.upload(f, true);
            })(files[i]);
          }
        }
      });
	$scope.refreshModel=function(){
		$scope.document={};
		$scope.repositories=[];
		$scope.treedata=[];
		$scope.picFile='';
		$scope.errorlist=null;
	};
	$scope.onFileSelect = function ($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
    };
    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
    	$scope.$apply(function () {            
            $scope.files.push(args.file);
        });
    });
    
	$scope.save=function() 
	{
		  var file=$scope.picFile;
		  if($scope.picFile==''){
			  alert("Please select file");
			  return false;
		  }
		  	//var d = new Date($scope.causelist.cl_date);
		  	//var m=("0" + (d.getMonth() + 1)).slice(-2)
		    //var year=d.getFullYear();
		    //var n = d.getDate()+"-"+m+"-"+year;
		    //$scope.causelist.cl_date=n;
		    file.upload = Upload.upload({
		      url: urlBase + 'causelist/create',
		      headers: {
		    	  'optional-header': 'header-value'
		        },
		        fields:$scope.causelist,
    		   file:file,
		    });

		    file.upload.then(function (response) {
		        if(response.data.response=="TRUE"){
		        	$scope.errorlist =null;
		        	alert("Successfully uploaded complete causelist");
		        	$("#causeListCreate").modal("hide");
		        	//window.location.reload();
		        }else{
		        	alert(response.data.data);
		        	$("#causeListCreate").modal("hide");
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
	$scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	$scope.viewApplication=function(application_no,application_year){
		  window.open(urlBase+"casefile/applicationview/?app_no="+application_no+"&app_year="+application_year,"_blank");
	  }
	
	$scope.updatePriority=function(id){
		var response = $http.get(urlBase+'causelist/updatepriority/'+id);
		response.success(function(data, status, headers, config) {
			if(data.response=="TRUE"){
				alert("Priority updated please refresh the page for updated list");
			}
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	  }
	
		
}]);
