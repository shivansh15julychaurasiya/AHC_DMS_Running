var EDMSApp = angular.module('EDMSApp', []);
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



EDMSApp.controller('ECourtHomeCtrl',['$scope','$http',function ($scope, $http) {
	var baseUrl="/dms/";
	loadMasterData();
	//$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
	//$scope.data = [300, 500, 100, 40, 120];
	$scope.dailycount=0;
	$scope.freshcount=0;
	$scope.backlogcount=0;
	$scope.supplementarycount=0;
	$scope.additionalcount=0;
	$scope.applicationcount=0;
	$scope.correctioncount=0;
	
	function loadMasterData() {
		var response = $http.get(baseUrl+'ecourt/getreport');
		response.success(function(data, status, headers, config) {		
			$scope.masterdata= data.modelList;
			angular.forEach($scope.masterdata, function(object, key) {
				  
				  if(object.cl_list_type_mid==1){
					  $scope.applicationcount=object.count
				  }
				  if(object.cl_list_type_mid==2){
					  $scope.correctioncount=object.count
				  }
				  if(object.cl_list_type_mid==3){
					  $scope.dailycount=object.count
				  }
				  if(object.cl_list_type_mid==4){
					  $scope.backlogcount=object.count
				  }
				  if(object.cl_list_type_mid==5){
					  $scope.freshcount=object.count
				  }
				  if(object.cl_list_type_mid==6){
					  $scope.supplementarycount=object.count
				  }
				  if(object.cl_list_type_mid==7){
					  $scope.additionalcount=object.count
				  }
				});
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
}]);