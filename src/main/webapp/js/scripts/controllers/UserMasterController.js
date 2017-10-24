var edmsApp = angular.module("EDMSApp",['smart-table']);

edmsApp.controller("UserMasterCtrl",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	$scope.masterentity = {};	
	$scope.masterdata=  [];
	$scope.roleData=[];
	$scope.departmentData=[];
	$scope.designationData=[];
	$scope.userrole={};
	$scope.userroles=[];
	$scope.errorlist=null;
	$scope.userpemissions=[];
	$scope.repositories=[];
	$scope.folders=[];
	
	getMasterdata();
	getRoleDD();
	function getMasterdata() 
	{
		$http.get(urlBase+'user/getallusers').success(function (data) {
            	$scope.masterdata = data; 
            	$scope.displayedCollection = [].concat($scope.masterdata);
            }).
            error(function(data, status, headers, config) {
            	console.log("Error in getting User data");
            });
    };
    function getRoleDD() {
		$http.get(urlBase+'user/getmasters').
            success(function (data) {
            	$scope.roleData=data.roleData;
            	$scope.departmentData=data.departmentData;
            	$scope.designationData=data.designationData;
            	$scope.courtsData=data.courtsData;
            	$scope.masterdata.um_pass_validity_date = new Date();
            }).
            error(function(data, status, headers, config) {
            	console.log("Error in getting property details");
            });
		
		}
    
    $scope.changeStatus=function(permission){
    	$scope.errorlist=[];
    	$scope.entity=angular.copy(permission);
    	
    	if($scope.entity.status==0)
    		$scope.entity.status=1;
    	else
    		$scope.entity.status=0;
    	
    	$http.post(urlBase+'user/updateuserpermission',$scope.entity).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$scope.repositories=data.repositories;
            	$scope.folders=data.folders;
        	}else{
        		$scope.errorlist=data.dataMapList;
        	}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting property details");
        });
    };
    
    $scope.user_create = function(masterentity) {
    	console.log(masterentity);
    	
    	$scope.masterentity=masterentity;
    	
//    	if($scope.masterentity){
//    		angular.forEach($scope.masterdata,function(value,index){	    			
//    			if(value.username==$scope.masterentity.username){
//    				if($scope.masterentity.um_id){
//    					if($scope.masterentity.um_id!=value.um_id){
//    						$scope.error=true;
//    					}
//    				}else{
//    					$scope.error=true;
//    				}
//    			}
//            });
//    	}
    	
    		var response = $http.post(urlBase+'user/create',$scope.masterentity);		 
				response.success(function(data, status, headers, config) {					
					if(data.response=="FALSE"){					
						$scope.errorlist=data.dataMapList;
					}else{		
						$('#user_Modal').modal('hide');		
						$scope.errorlist=[];
						
						$('.form-group').removeClass('has-error');
						
								alert("User created Successfully!");
								getMasterdata();						
					}
					
			});
			response.error(function(data, status, headers, config) {
				alert( "Error");
			});
    	
	};
	
	
	$scope.user_update=function(masterentity)
	{
		console.log(masterentity);
		$scope.masterentity=masterentity;
		
		
		var response=$http.post(urlBase+'user/update',$scope.masterentity);
		response.success(function(data,status,headers,config){
			if(data.response=="FALSE")
				{
				$scope.errorlist=data.dataMapList;					
					
				}
			else
				{
				$('#user_Modal').modal('hide');		
				$scope.errorlist=null;
				$('.form-group').removeClass('has-error');
				alert("User updated Successfully!");
				getMasterdata();					
			    }
			
			
		});
			response.error(function(data, status, headers, config) {
				alert( "Error");
			}); 
	};
	$scope.setMasterdata = function(masterentity) {
		$scope.masterentity = angular.copy(masterentity);
		
		if($scope.masterentity.userroles.length>0)
			$scope.masterentity.um_role_id= $scope.masterentity.userroles[0].ur_role_id;
	};
    

	$scope.resetModel=function()
	{
		$scope.masterentity.um_rec_status=1;
		$scope.masterentity={};		
		$('.form-group').removeClass('has-error');	
		$scope.index=-1;
		
	};
    
	$scope.Refresherrorlist=function()
	{
		$scope.errorlist = null;
	};

	$scope.changePassword = function(data) {
		//alert(1);
		console.log(data);
		console.log("********");

				
			$scope.password;
			$scope.confirmpassword;
			$scope.entity={'password':$scope.password,'confirmpassword':$scope.confirmpassword}
		

		var response = $http.post(urlBase+'user/changepassword',
				$scope.entity);
		response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
			
				$('#pass_Modal').modal('hide');		
				$scope.errorlist=null;
				$('.form-group').removeClass('has-error');
				bootbox.alert("Password changed Successfully!");
				
				//$scope.errorlist = [];
				//$scope.ansDetails = {};

			} else {

				$scope.errorlist = data.dataMapList;
				/*$.each($scope.errorlist, function(k, v) {
					$("#" + k).parent().parent().addClass('has-error');
				});*/
				//bootbox.alert("Password and Confirm Password not matched");
				$scope.ansDetails = [];
			
			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});

  
}
}]);