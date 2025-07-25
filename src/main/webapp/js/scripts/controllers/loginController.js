var edmsApp = angular.module('EDMSApp', []);

edmsApp.controller('loginController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
	
	
	var urlBase="/dms/";
	$scope.flattypes=[];
	$scope.flatcats=[];
	$scope.loginform={};
	$scope.userRoles=[];
	$scope.errorlist = '';
	//$scope.errorlist={"name":["Already Exist"]};
	$('.register').hide();
	//$('.login').hide();
	$scope.register={};
	$scope.register.type='aor';
	$scope.isadvocate=false;
	$scope.designations = [];
	$scope.sections =[];
	$scope.registerData ={};
	
	
	/*deleteHistory();
	function deleteHistory()
	{
		alert("Please clear browser history by press Ctrl + H  ");
	}
	
	*/
	/*getUserDesignation();
	getUserSection();*/
	
	
	
	$scope.darkMode =function (){
		
		var dark = localStorage.getItem("mode");
	    if(dark==''){
	        localStorage.setItem("mode", 'dark-mode-live');
	        $("body").toggleClass("dark-mode-live");
	        $('.inner-switch-dark').prop('title', 'Disable Contrast Mode');
	    }else{
	        localStorage.setItem("mode", '');
	        $("body").toggleClass("dark-mode-live");
	        $('.inner-switch-dark').prop( "title","Enable Contrast Mode");
	    }
	}
	
	
	
	function getUserDesignation(){
		
		$http.get(urlBase+'userregistration/getUserDesignations').success(function (data) {
			
			$scope.designations  =data.modelData;
			
			
			console.log("user designationnnnnn",$scope.designations)
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting UserDesignations");
	      });
		
		
		
		
	}
	
	function getUserSection(){
		
		$http.get(urlBase+'userregistration/getUserSections').success(function (data) {
			
			$scope.sections =data.modelData;
			console.log("user sectionnnnnn",$scope.sections);
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting UserSection");
	      });
		
		
	}
	
	
	/////////////virtual keyboard
	
	const Keyboard = {
			  elements: {
			    main: null,
			    keysContainer: null,
			    keys: []
			  },

			  eventHandlers: {
			    oninput: null,
			    onclose: null
			  },

			  properties: {
			    value: "",
			    capsLock: false
			  },

			  init() {
			    this.elements.main = document.createElement("div");
			    this.elements.keysContainer = document.createElement("div");

			    this.elements.main.classList.add("keyboard", "keyboard-hidden");
			    this.elements.keysContainer.classList.add("keyboard-keys");
			    this.elements.keysContainer.appendChild(this._createKeys());

			    this.elements.keys = this.elements.keysContainer.querySelectorAll(
			      ".keyboard-key"
			    );

			    this.elements.main.appendChild(this.elements.keysContainer);
			    document.body.appendChild(this.elements.main);
			    
			    
			    document.querySelectorAll(".keyboard-open").forEach((element) => {
				      element.addEventListener("focus", () => {
				        this.open1(element.value, (currentValue) => {
				          element.value = currentValue;
				        });
				      });
				    });

			    document.querySelectorAll(".keyboard-input").forEach((element) => {
			      element.addEventListener("focus", () => {
			        this.open(element.value, (currentValue) => {
			          element.value = currentValue;
			        });
			      });
			    });
			  },

			  _createKeys() {
			    const fragment = document.createDocumentFragment();
			    const keyLayout = [
			      "1",
			      "2",
			      "3",
			      "4",
			      "5",
			      "6",
			      "7",
			      "8",
			      "9",
			      "0",
			      "backspace",
			      "q",
			      "w",
			      "e",
			      "r",
			      "t",
			      "y",
			      "u",
			      "i",
			      "o",
			      "p",
			      "caps",
			      "a",
			      "s",
			      "d",
			      "f",
			      "g",
			      "h",
			      "j",
			      "k",
			      "l",
			      "enter",
			      "done",
			      "z",
			      "x",
			      "c",
			      "v",
			      "b",
			      "n",
			      "m",
			      ",",
			      ".",
			      "?",
			      "space"
			    ];

			    const createIconHTML = (icon_name) => {
			      return `<i class="material-icons">${icon_name}</i>`;
			    };

			    keyLayout.forEach((key) => {
			      const keyElement = document.createElement("button");
			      const insertLineBreak =
			        ["backspace", "p", "enter", "?"].indexOf(key) !== -1;

			      keyElement.classList.add("keyboard-key");

			      switch (key) {
			        case "backspace":
			          keyElement.classList.add("keyboard-wide");
			          keyElement.innerHTML = createIconHTML("backspace");
			          keyElement.addEventListener("click", () => {
			            this.properties.value = this.properties.value.substring(
			              0,
			              this.properties.value.length - 1
			            );
			            this._triggerEvent("oninput");
			          });
			          break;

			        case "caps":
			          keyElement.classList.add("keyboard-wide", "keyboard-active");
			          keyElement.innerHTML = createIconHTML("keyboard_capslock");
			          keyElement.addEventListener("click", () => {
			            this._toggleCapsLock();
			            keyElement.classList.toggle(
			              "keyboard-check",
			              this.properties.capsLock
			            );
			          });
			          break;

			        case "enter":
			          keyElement.classList.add("keyboard-wide");
			          keyElement.innerHTML = createIconHTML("keyboard_return");
			          keyElement.addEventListener("click", () => {
			            this.properties.value += "\n";
			          });
			          break;

			        case "space":
			          keyElement.classList.add("keyboard-extrawide");
			          keyElement.innerHTML = createIconHTML("space_bar");
			          keyElement.addEventListener("click", () => {
			            this.properties.value += " ";
			            this._triggerEvent("oninput");
			          });
			          break;

			        case "done":
			          keyElement.classList.add("keyboard-wide", "keyboard-dark");
			          keyElement.innerHTML = createIconHTML("check_circle");
			          keyElement.addEventListener("click", () => {
			            this.close();
			            this._triggerEvent("onclose");
			          });
			          break;
			        default:
			          keyElement.textContent = key.toLowerCase();
			          keyElement.addEventListener("click", () => {
			            this.properties.value += this.properties.capsLock
			              ? key.toUpperCase()
			              : key.toLowerCase();
			            this._triggerEvent("oninput");
			          });
			          break;
			      }

			      fragment.appendChild(keyElement);

			      if (insertLineBreak) {
			        fragment.appendChild(document.createElement("br"));
			      }
			    });

			    return fragment;
			  },

			  _triggerEvent(name) {
			    if (typeof this.eventHandlers[name] === "function") {
			      this.eventHandlers[name](this.properties.value);
			    }
			  },

			  _toggleCapsLock() {
			    this.properties.capsLock = !this.properties.capsLock;

			    for (const key of this.elements.keys) {
			      if (key.childElementCount === 0) {
			        key.textContent = this.properties.capsLock
			          ? key.textContent.toUpperCase()
			          : key.textContent.toLowerCase();
			      }
			    }
			  },

			  open(initialValue, oninput, onclose) {
			    this.properties.value = initialValue || "";
			    this.eventHandlers.oninput = oninput;
			    this.eventHandlers.onclose = onclose;
			   /* this.elements.main.classList.remove("keyboard-hidden");*/
			  },
			  
			  open1(initialValue, oninput, onclose) {
				    
				    this.elements.main.classList.remove("keyboard-hidden");
				  },

			  close() {
			    this.properties.value = "";
			    this.eventHandlers.oninput = oninput;
			    this.eventHandlers.onclose = onclose;
			    this.elements.main.classList.add("keyboard-hidden");
			  }
			};

			Keyboard.init();

		
	
	///////////// Keyboard
	
	
	
	$scope.registerForm=function(){
		$('.register').show();
		$('.login').hide();
		this.refresh();
	}
	$scope.loginForm=function(){
		$('.login').show();
		$('.register').hide();
		
	}
	$scope.refresh=function(){
		$scope.registertemp=$scope.register;
		$scope.register={};
		$scope.isadvocate=false;
		$scope.register.type=$scope.registertemp.type;
	}
	$scope.validateAOR=function(){
		if(AORFormValidate()=="TRUE"){
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		$http.post(urlBase+'user/validateAdvocate',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$scope.isadvocate=true;
        		$scope.register.username=data.modelData.rollNo;
        		$scope.register.name=data.modelData.name;
        		$scope.register.email=data.modelData.email;
        		$scope.register.mobile=data.modelData.mobile;
        		
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        	}else{
        		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		 setTimeout(function() {
        			 $(".msg_div").html("");
        		    }, 5000);
        		        		
        	}    
        	           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("Error in getting Login details");
        });
		}
	}
	$scope.registration=function(){
		
		console.log("dataaaaaaaaaa",$scope.register);
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		if(RegisterFormValidate()=="TRUE"){
			
		$scope.errorlist='';
		$http.post(urlBase+'userregistration/create',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		setTimeout(function() {
       			 $(".msg_div").html("");
       		    }, 5000);
        		$scope.registertemp=$scope.register;
        		$scope.register={};
        		$scope.register.type=$scope.registertemp.type;	
    			$('.login').show();
    			$('.register').hide();
    			$("#userid").parent().removeClass('has-error');
    			$("#name").parent().removeClass('has-error');
    			$("#email").parent().removeClass('has-error');
    			$("#mobile").parent().removeClass('has-error');
    			$("#confirmPassword").parent().removeClass('has-error');
    			$("#username").parent().removeClass('has-error');
    			$("#password").parent().removeClass('has-error');
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        		//$scope.register={'type':$scope.register.type,'username':$scope.register.username,'name':$scope.register.name,'mobile':$scope.register.mobile,'email':$scope.register.email,'password':$scope.register.password,'confirmPassword':$scope.register.confirmPassword};
        	}else{
        		$scope.errorlist=data.dataMapLists;
        	}           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("E" +
        			" rror in getting Login details");
        });
		}
	}
	function AORFormValidate(){
		$scope.validate = "TRUE";
		
			if($scope.register.hasOwnProperty("rollNo") == false){
				$("#rollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#rollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollNo") == false){
				$("#enrollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollYear") == false){
				$("#enrollYear").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollYear").parent().removeClass('has-error');
			}
		return $scope.validate;
		
	}
	function RegisterFormValidate(){
		
		console.log("validateeee");
		$scope.validate = "TRUE";
			if($scope.register.hasOwnProperty("hcu_office_id") == false){
				$("#userid").parent().addClass('has-error');
				$scope.validate = "FALSE";
				console.log("validateeee1111");
			}else{
				$("#userid").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_username") == false){
				$("#name").parent().addClass('has-error');
				$scope.validate = "FALSE"; 
			}else{
				$("#name").parent().removeClass('has-error');
			}
			
			if($scope.register.hasOwnProperty("hcu_email") == false){
				$("#email").parent().addClass('has-error');
				$scope.validate = "FALSE";
				
			}else{
				if(isValidEmailAddress($scope.register.hcu_email)){
					$("#email").parent().removeClass('has-error');					
				}else
				{
					$scope.validate = "FALSE";
					$("#email").parent().addClass('has-error');
				}
					
			}
			if($scope.register.hasOwnProperty("hcu_contact_number") == false){
				$("#mobile").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#mobile").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_password") == false){
				$("#registerpassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#registerpassword").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_confirm_password") == false){
				$("#confirmPassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#confirmPassword").parent().removeClass('has-error');
			}
			var msg="Please update latest Email/Mobile in advocate role.";
			if($scope.register.type=="aor" &&( $scope.register.email=='' || $scope.register.mobile=="")){
				$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+msg+"</div>");
				
			}else{
				$(".msg_div").html("");
			}
		return $scope.validate;
		
	}
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	};

	function loginFormValidate(action){
		
		$scope.validate = "TRUE";
		$(".form-group").removeClass('has-error');  
		$("#msg_div").html(" ");
		
		if(action=="login"){
		
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			if($scope.loginform.hasOwnProperty("password")==false){
				$("#password").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#password").parent().removeClass('has-error');
			}
		}
		if(action=="fgpwd"){
			
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			
		}
		
		return $scope.validate;
	}
	
	$scope.login= function() {
		 
		if(loginFormValidate("login")=="TRUE"){	
			console.log($scope.loginform); 
	        $http.post(urlBase+'dms/userlogin',$scope.loginform).
	            success(function (data) {
	            	console.log("Success got Login details");
	            	console.log(data);  	            	
	            	if(data.response=="TRUE"){	
            			window.location.href=urlBase+data.data;
            			
	            	}
	            	else{
	            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            		setTimeout(function() {
	           			 $(".msg_div").html("");
	           		    }, 5000);
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}	     
    };
    
    $scope.fagpass= function() {
    	
		if(loginFormValidate("fgpwd")=="TRUE"){	
			console.log($scope.loginform); 
	
	        $http.post(urlBase+'dms/userFagPass',$scope.loginform). 
	            success(function (data) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		
            			window.location.href=urlBase+"views/forgetPassword";
	            	}else if(data.response=="FALSE"){
	            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}
    };

    $scope.accountActivation= function() {
    	
		if(loginFormValidate("fgpwd")=="TRUE"){	
			console.log($scope.loginform); 
	
	        $http.post(urlBase+'dms/userFagPass',$scope.loginform). 
	            success(function (data) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		
            			window.location.href=urlBase+"views/accountActivation";
	            	}else if(data.response=="FALSE"){
	            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}
    };    
    
}]);

var compareTo = function() {
	
	
    return {
      require: "ngModel",
      scope: {
        otherModelValue: "=compareTo"
      },
      link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
          return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
          ngModel.$validate();
        });
      }
    };
  };
  
  edmsApp.directive("compareTo", compareTo);

