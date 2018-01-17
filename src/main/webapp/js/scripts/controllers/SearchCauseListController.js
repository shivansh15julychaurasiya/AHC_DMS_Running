/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap']);

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

edmsApp.controller("causeListController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
	$scope.search=false;	
	$scope.model={};
    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
	$scope.format = $scope.formats[0];
    $scope.today = function() {
    	$scope.model.cl_dol = new Date();
	};
	
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
	getCauseListTypes();
	function getCauseListTypes() {
		var response = $http.get(urlBase+'causelist/getCauseListTypes');
		response.success(function(data, status, headers, config) {
			$scope.causeListTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	$scope.getList=function(){
		$scope.search=true;
		$http.post(urlBase+'causelist/getCauseList',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
			//$scope.SeasrchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
	$scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	$scope.viewApplication=function(application_no,application_year){
		  window.open(urlBase+"casefile/applicationview/?app_no="+application_no+"&app_year="+application_year,"_blank");
	  }
	
}]);
