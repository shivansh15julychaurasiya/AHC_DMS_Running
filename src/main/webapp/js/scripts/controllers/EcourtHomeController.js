var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);
EDMSApp.directive('loading', ['$http', function($http) {
	return {
		restrict: 'A',
		link: function(scope, elm, attrs) {
			scope.isLoading = function() {
				return $http.pendingRequests.length > 0;
			};
			scope.$watch(scope.isLoading, function(v) {
				if (v) {
					elm.show();
				} el
			 {
					elm.hide();
				}
			});
		}
	};
}]);



EDMSApp.controller('ECourtHomeCtrl', ['$scope', '$http', function($scope, $http) {
	var baseUrl = "/dms/";
	$scope.causelist_date = convertDate(new Date());

	loadMasterData();

	$scope.dailycount = 0;
	$scope.freshcount = 0;
	$scope.putFreshcount = 0;
	$scope.backlogcount = 0;
	$scope.supplementarycount = 0;
	$scope.additionalcount = 0;
	$scope.applicationcount = 0;
	$scope.correctioncount = 0;
	$scope.productioncount = 0;
	$scope.tiacount = 0;
	$scope.showProductionList = false;



	$scope.listData = [];

	$scope.open1 = function($event, type) {
		$event.preventDefault();
		$event.stopPropagation();

		if (type == "fromDate1")
			$scope.fromDate1 = true;
		if (type == "toDate1")
			$scope.toDate = true;
	};



	// Vijay chaurasiya

	


	function loadMasterData2() {
		var countadd = 0;
		$scope.listData = [];
		var response = $http.get(baseUrl + 'ecourt/getreportforadditionallists?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			$scope.listData = data.modelList;
			console.log("Dataaaaaaaa", $scope.listData);
			angular.forEach($scope.listData, function(object, key) {
				countadd = countadd + object.count;


			});
			$scope.additionalcount = countadd + $scope.additionalcount;

			if ($scope.listData.length > 0) {
				//$scope.showTransfer = true;
			}


		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};

	function getFreshMentionCount() {
		var countfreshmention = 0;
		$scope.listData = [];
		var response = $http.get(baseUrl + 'ecourt/getreportforfreshmentionlist?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			$scope.listData = data.modelList;
			console.log("Dataaaaaaaa", $scope.listData);
			angular.forEach($scope.listData, function(object, key) {
				countfreshmention = countfreshmention + object.count;


			});
			$scope.freshcount = countfreshmention + $scope.freshcount;

			if ($scope.listData.length > 0) {
				//$scope.showTransfer = true;
			}


		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};

	loadMasterData5();
	function loadMasterData5() {
		var countadd = 0;
		var response = $http.get(baseUrl + 'ecourt/getreportforcorrectionlists?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			$scope.listData = data.modelList;
			console.log("Dataaaaaaaa", $scope.listData.length);
			angular.forEach($scope.listData, function(object, key) {
				countadd = countadd + object.count;


			});
			$scope.correctioncount = countadd + $scope.correctioncount;

			if ($scope.listData.length != 0) {
				$scope.showList = true;
			}


		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};


	getFreshMentionCount();
	function loadMasterData4() {
		var countadd = 0;
		$scope.listData = [];
		var response = $http.get(baseUrl + 'ecourt/getreportforsupplementarylist?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			$scope.listData = data.modelList;
			console.log("Dataaaaaaaa", $scope.listData);
			angular.forEach($scope.listData, function(object, key) {
				countadd = countadd + object.count;


			});
			$scope.supplementarycount = countadd + $scope.supplementarycount;

			if ($scope.listData.length > 0) {
				//$scope.showTransfer = true;
			}


		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};

	loadMasterData4();





	function loadMasterData3() {
		$scope.showTransfer = false;


		$scope.totalCases = null;
		var response = $http.get(baseUrl + 'ecourt/getTranferCasesCount?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			if (data.modelList.length > 0 && data.modelList[0] > 0) {
				$scope.totalCases = data.modelList[0];

				$scope.showTransfer = true;
			}





		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};

	loadMasterData3();
	loadMasterData2();

	$scope.isLoading = true;

	function loadMasterData() {
	    var response = $http.get(baseUrl + 'ecourt/getreport?causelist_date=' + $scope.causelist_date);

	    response.success(function (data) {

	        $scope.masterdata = data.modelList;
	        $scope.backupdata = data.modelList;
	        $scope.backupdata1 = $scope.backupdata;

	        // Allowed list IDs
	        $scope.allowedListIds = [1, 2, 3, 4, 5, 35, 6, 7,23,21];

	       
	        $scope.additionListIds = [
	            7, 11, 12, 13, 14, 15, 25, 23, 24,
	            8, 9, 10, 16, 17, 18, 19, 20, 22,21
	        ];

	        // Default Data
	        $scope.defaultData = $scope.masterdata.filter(function (item) {
	            return $scope.allowedListIds.includes(item.cl_list_type_mid);
	        });

	        // Other Data
	        $scope.otherData = $scope.masterdata.filter(item =>
	            !$scope.allowedListIds.includes(item.cl_list_type_mid)
	        );

	        // Removed additional list
	        $scope.removedAdditional = $scope.otherData.filter(item =>
	            !$scope.additionListIds.includes(item.cl_list_type_mid)
	        );

	        // Stop loader
	        $scope.isLoading = false;
	    });

	    response.error(function () {
	        $scope.isLoading = false; // also stop loader on error
	    });
	}


	function loadMasterData1() {
		var response = $http.get(baseUrl + 'ecourt/downloadCauseList?causelist_date=' + $scope.causelist_date);
		response.success(function(data, status, headers, config) {
			$scope.masterdata = data.modelList;
			angular.forEach($scope.masterdata, function(object, key) {

				if (object.cl_list_type_mid == 1) {
					$scope.applicationcount = object.count
				}
				if (object.cl_list_type_mid == 2) {
					$scope.correctioncount = object.count
				}
				if (object.cl_list_type_mid == 3) {
					$scope.dailycount = object.count
				}
				if (object.cl_list_type_mid == 4) {
					$scope.backlogcount = object.count
				}
				if (object.cl_list_type_mid == 5) {
					$scope.freshcount = object.count
				}
				if (object.cl_list_type_mid == 6) {
					$scope.supplementarycount = object.count
				}
				if (object.cl_list_type_mid == 7) {
					$scope.additionalcount = object.count
				}
			});

		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});

	};


	$scope.doownloadCauseList = function() {

		console.log("function invokedddddgggg");
		loadMasterData1();
	}



	$scope.getCauseList = function(cause_list_date) {
		$scope.causelist_date = convertDate(cause_list_date);
		loadMasterData();

		console.log("hello" + cause_list_date);

	}

	$scope.show = function() {


		$scope.divShow = true;

	};
	$scope.open1 = function($event, type) {
		$event.preventDefault();
		$event.stopPropagation();

		if (type == "fromDate1")
			$scope.fromDate1 = true;
		if (type == "toDate1")
			$scope.toDate = true;
	};


	$scope.toggleMax = function() {
		//$scope.minDate = $scope.minDate ? null : new Date();
		$scope.maxDate = new Date();
	};
	$scope.toggleMax();

	$scope.open = function($event, type) {
		$event.preventDefault();
		$event.stopPropagation();

		if (type == "fromDate")
			$scope.fromDate = true;
		if (type == "toDate")
			$scope.toDate = true;
	};

	$scope.dateOptions = {
		formatYear: 'yy',
		startingDay: 1

	};

	$scope.formats = ['dd-MMMM-yyyy', 'dd-mm-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
	$scope.format = $scope.formats[3];

	function convertDate(inputFormat) {
		function pad(s) { return (s < 10) ? '0' + s : s; }
		var d = new Date(inputFormat);
		return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
	}
}]);