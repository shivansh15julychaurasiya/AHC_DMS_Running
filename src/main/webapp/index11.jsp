<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html id="YourElementId" ng-app="EDMSApp">
<!--<![endif]-->
<head>

	<meta charset="utf-8" />
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
	<title>Login Page</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
<!-- 	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
	<link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/animate.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/otpstyle.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	 <script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js"></script> 
	 
	  <script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js"></script> 
	<style>
	.link:focus, .link:hover {
    	color: #23527c;
    	text-decoration: underline;
    	cursor: pointer;
	}
	
	.dark-mode-live{
	filter: invert(1) hue-rotate(180deg);
}


input[type=text] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 3px solid #ccc;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  outline: none;
}

input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 3px solid #ccc;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  outline: none;
}

input[type=text]:focus {
  border: 3px solid #555;
}

	
	</style>	 
	<!-- ================== END BASE JS ================== -->
</head>
<body class="pace-top" style ="background: white;">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container"  class="fade" ng-controller="OtpController">
	
	
	
	<%--  <img src="${pageContext.request.contextPath}/assets/img/login-bg/bg-7.jpg" data-id="login-cover-image" alt="" /> --%>
             
	    <!-- begin login -->
	    <div class="container">
	    
	    
	    <div style='position:absolute;z-index:0;left:0;top:0;width:100%;height:100%'>
         <img src='${pageContext.request.contextPath}/assets/img/login-bg/aldd.jpg' style='width:100%;height:100%' alt='[]' />
     </div>

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form">
			<fieldset>
			
			<h1 align="center"><u><i style="color:blue;">e</i>-Court (DMS)</u></h1>
			<h1 align="center"><strong>Allahabad High Court</strong></h1> <br/><br/>
			
			  <div class="form-group">
                                <label class="col-md-3 control-label">Login as</label>
                                <div class="col-md-9">
                                    <label class="radio-inline">
                                        <input type="radio" ng-model="loginType" ng-change="formChange()" value=false ng-checked="loginType==false" /> E-court
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" ng-model="loginType" ng-change="formChange()" value=true  ng-checked="loginType==true"/> Office
                                    </label>
                                </div>
                            </div>
                            <br/>
				
				<hr class="colorgraph">
				<div ng-show="loginType==false">
					<div class="form-group">
					
					<label for="password" ></label>
					<div class="passcode-wrapper " >
						<!-- <input type="text"  ng-model="loginform.username"> -->
						
						<select style="width: 100%;" class="form-control" 
							ng-model="loginform.username" ng-change="changeJudge()"
							ng-options="judge[1] as judge[0] +', .J' for judge in judges   | orderBy : 'judge[0]'"
							 select2="">
							<option value="">Select Name</option>
						</select>
						</div>
				</div>
				<br/> <br/>
				
			
				
				
				<div style="background: #cccc66;" class="form-group"  ng-show="bioFlag">
					<label for="password" ><h4><b>Please Put Finger On Scanner</b></h4></label>
					<div class="passcode-wrapper " style ="margin-left : 100px">
						<input type="text" pattern="\d*" maxlength="4" ng-model="loginform.password" width="100%"  style="
    width: 150px;
    height: 50px;
    font-size: 45px;
    color: blue;
">
               <%-- <img src='${pageContext.request.contextPath}/assets/img/fingarPrint.png' style='width:30%;height:30%' alt='[]' /> --%>
						</div>
					<br/>
			<!-- 		<div id="some_div">
</div> -->
				</div>
				
				<!-- <span class="button-checkbox" ng-hide="otpFlag">
					<button type="button" ng-click="sendOTP()" class="btn btn-primary" data-color="info">Send OTP</button>
                    <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<a href="" class="btn btn-link pull-right">Forgot Passcode?</a>
				</span>
				
				<span class="button-checkbox" id ="hideBtn" hidden>
					<button type="button" id="resend" ng-click="reSendOTP()" class="btn btn-primary" data-color="info">ReSend OTP</button>
                    <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<a href="" class="btn btn-link pull-right">Forgot Passcode?</a>
				</span> -->
				
				<span class="button-checkbox" >
					<button type="button" ng-click="loginBtn()" class="btn btn-primary" data-color="info">Login</button>
                    <!-- <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<a href="" class="btn btn-link pull-right">Forgot Passcode?</a> -->
				</span>
				
				</div>
				
				
				
				<div  ng-show="loginType=='true'">

<!-- <h2 class="active"> <em style="color: black"><b style="color: blue">e</b>-Court Allahabad High Court</em> </h2><br/><br/> -->
<!-- <h2 class="active"> <em style="color: black">Allahabad High Court</em> </h2> -->
<!-- <button align="right" class="fa fa-keyboard-o keyboard-open" style="font-size:28px;color:blue"></button><br/> -->

  <form>
   <span class="msg_div"></span>
  <!--  <span style="color: purple"><b>username</b></span> -->
   <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
   <input type="text" class="text keyboard-input" id="username" name="username" ng-model="username1" required placeholder="Employee Id" />
   
 
   <!-- <input type="text" class="text glyphicon glyphicon-pencil" id="username" name="username" ng-model="loginform.username" required placeholder="User Name" />
    -->  
     </div>
    <br>
   
    <div class="form-group m-b-15" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
   <input type="password" class="text keyboard-input"  id="password" name="password"  ng-model="loginform.password" required placeholder="Password" />
   <!-- <input type="password" class="text keyboard-input"  id="password" name="password"  ng-model="loginform.password" required placeholder="Password" style="
    width: 100%;
    padding: 12px 20px;
"/> -->
    
     </div>
    <br>
    <!-- <input type="checkbox" id="checkbox-1-1" class="custom-checkbox" />
    <label for="checkbox-1-1">Keep me Signed in</label> -->
    
    
     <div class="login-buttons">
    <button  type="button" class="btn btn-primary" data-color="info" ng-click="login()">Login In </button>
    </div>
   <!--  <hr> -->
    <!--  <a href="#">Forgot Password?</a> -->
     
  </form>
</div>
				
				<hr class="colorgraph">
				
	 
       
        </div>
        
        <!-- end theme-panel -->
	</div>
	<!-- end page container -->
	
	
	<!-- fingreprint Scanner start -->
	
	 <div class="panel" hidden>
        <table width="100%" style="padding-top: 0px;">
            <tr>
                <td colspan="3" align="center" style="color: #428BCA; font-size: 20px; font-weight: bold; background-color: #428bca2e;">
                    MANTRA-MorFinAuth-TEST
                </td>
            </tr>
        </table>
        <table width="40%" style="padding-top: 0px; ">
            <tr>
                <td style="padding-left: 100px; padding-top: 10px;">
                    <b class="b4">Quality(1-100)</b>
                    <input type="text" id="txtQuality" value="60" maxlength="3" class="form-control b3" />
                </td>
                <td style="padding-top :10px;">
                    <b class="b4o">Timeout(In Second)</b>
                    <input type="text" id="txtTimeout" value="50" maxlength="5" class="form-control b3o" />
                </td>
            </tr>
            <tr>
                <td style="padding-left: 100px; padding-top: 10px;">
                    <b class="b5" style="margin-left: -145px; ">Select Image Type</b>
                    <div class="select-dropdown" style="width: 147px">
                        <select id="ddlImageFormat">
                            <option value="0">Select</option>
                            <option value="1" selected="selected">BMP</option>
                            <option value="2">JPEG2000</option>
                            <option value="3">Wsq</option>
                            <option value="4">RAW</option>
                            <option value="5">FIR_V2005</option>
                            <option value="6">FIR_V2011</option>
                            <option value="7">FIR_WSQ_V2005</option>
                            <option value="8">FIR_WSQ_V2011</option>
                            <option value="9">FIR_JPEG2000_V2005</option>
                            <option value="10">FIR_JPEG2000_V2011</option>
                        </select>
                    </div>
                </td>
                <td style="padding-top: 10px;">
                    <b class="b4o" style="margin-left: -133px;">Select Template Type</b>
                    <div class="select-dropdown" style="margin-left: -133px; width: 128px; margin-top: 17px;">
                        <select id="ddlTemplateFormat" style="width: 128px;">
                            <option value="0">Select</option>
                            <option value="1" selected="selected">FMR_V2005</option>
                            <option value="2">FMR_V2011</option>
                            <option value="3">ANSI_V378</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 100px;padding-top: 10px; ">
                    <b class="b4o" style="margin-left: -138px;">Select Connected Device</b>
                    <div class="select-dropdown" style="margin-top: 18px">
                        <select name="cmbConnectedDvc" id="cmbConnectedDvc" style="width:141px;"></select>
                    </div>
                </td>

                <td style="padding-left: 5px; padding-top: 8px;">
                    <b class="b4o" style="">
                        Enter Client Key :
                    </b>
                    <div style="padding-top: 4px;">
                        <textarea id="txtClientKey" class="form-control b7" style="height: 20px; width: 180%; margin-left: -137px; resize: none"></textarea>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 100px; padding-top: 10px;">
                    <b class=" b4">
                        Enter Template For Matching
                    </b>
                    <div>
                        <textarea id="txtProbTemplate" class="form-control b7" style="height: 55px; width: 290%; margin-left: -70px; resize: none"></textarea>
                    </div>
                </td>
            </tr>

            <tr></tr>
        </table>
        <table width="100%" style="padding-top: 0px;">
            <tr>
                <td width="300px;">
                    <table align="left" border="0" width="50%" style="margin-top: 3px;">
                        <tr>
                            <td>
                                <input type="submit" id="btnGetConnectedDevice" value="Get Connected Device" style="margin-top: 10px; margin-right: 10px; margin-left: 30px;" class="btn btn-primary btn-200" onclick="return GetConnectedDevice()" />
                            </td>
                            <td>
                                <input type="submit" id="btnCheckDevice" value="Check Device" style="margin-top: 10px;" class="btn btn-primary btn-200" onclick="return CheckDevice()" />
                            </td>
                            <td>
                                <input type="submit" id="btnGetSupportedDevice" value="Get Supported Device" style="margin-top: 10px; margin-right: 10px; margin-left: -14%;" class="btn btn-primary btn-200" onclick="return GetSupportedDevice()" />
                            </td>

                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" id="btnInfo" value="Get Info" style="margin-top: 10px; margin-left: 30px; " class="btn btn-primary btn-200" onclick="return GetInfo()" />
                            </td>
                            <td>
                                <input type="submit" id="btnCapture" value="Capture" style="margin-top: 10px; margin-right: 20px; margin-right: 40px; " class="btn btn-primary btn-200" onclick="return Capture()" />
                            </td>
                            <td>
                                <input type="submit" id="btnGet" value="Get Image" style="margin-top: 10px; margin-left: -14%; " class="btn btn-primary btn-200" onclick="return GetImageByFormat()" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" id="btnGetTemplate" value="Get Template" style="margin-top: 10px; margin-left: 30px; " class="btn btn-primary btn-200" onclick="return GetTemplateByFormat()" />
                            </td>
                            <td>
                                <input type="submit" id="btnMatch" value="Match" style="margin-top: 10px; margin-right: 20px;" class="btn btn-primary btn-200" onclick="return Verify()" />
                            </td>
                            <td>
                                <input type="submit" id="btnCaptureAndMatch" value="Capture and Match" style="margin-top: 10px; margin-left: -14%; " class="btn btn-primary btn-200" onclick="return Match()" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" id="btnUninit" value="Uninit" style="margin-top: 10px; margin-left: 30px; " class="btn btn-primary btn-200" onclick="return Uninit()" />
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table align="left" border="0" style="width: 100%; padding-right: 20px; margin-top: -373px; margin-left: -97%">
                        <tr>
                            <td width="145px" height="190px" align="center" class="img">
                                <img id="imgFinger" width="145px" height="166px" alt="Finger Image" />
                            </td>
                        </tr>
                        <tr></tr>
                    </table>
                </td>
                <td>
                    <table align="left" border="0" style="width: 116%; padding-right: 48px; line-height: 27px; margin-top: -87%; margin-left: -30%; border-radius: 13px; ">
                        <tr>
                            <td align="left" class="td1">Serial No:</td>
                            <td align="left" class="td2" id="tdSerial"></td>
                            <td align="left" class="td1">Make:</td>
                            <td align="left" class="td2" id="tdMake"></td>
                        </tr>
                        <tr>
                            <td align="left" class="td1">Model:</td>
                            <td align="left" class="td2" id="tdModel"></td>
                            <td align="left" class="td1">Width:</td>
                            <td align="left" class="td2" id="tdWidth"></td>
                        </tr>
                        <tr>
                            <td align="left" class="td1">Height:</td>
                            <td align="left" class="td2" id="tdHeight"></td>
                        </tr>
                        <tr></tr>
                        <tr></tr>
                    </table>
                </td>
            </tr>
        </table>
        <table width="100%" style="margin-top: 0px;">
            <tr>
            <tr>
            <tr>
                <td width="220px" style="padding-left: 21px;">
                    Status:
                </td>
                <td style="padding-right:10px;">
                    <input type="text" value="" id="txtStatus" class="form-control" disabled="disabled" />
                </td>
            </tr>
            <tr>
                <td width="220px" style="padding-left: 21px;">
                    Quality:
                </td>
                <td style="padding-right:10px;">
                    <input type="text" value="" id="txtImageInfo" class="form-control" style="width: 100%;" disabled="disabled" />
                </td>
            </tr>
            <tr id="tr1">
                <td style="padding-left: 21px;">
                    Base64Encoded Template Data
                </td>
                <td>
                    <textarea id="txtIsoTemplate" style="width: 99%; height: 50px; resize: none" class="form-control" disabled="disabled"></textarea>
                    <textarea id="txtIsoTemplates" style="width: 99%; height: 50px; resize: none" class="form-control" disabled="disabled"></textarea>
                    <textarea id="txtSecondFinger" style="width: 99%; height: 50px; resize: none" class="form-control" disabled="disabled"></textarea>
                     <textarea id="dashboardurl" style="width: 99%; height: 50px; resize: none" class="form-control" disabled="disabled"></textarea>
                </td>
            </tr>
            <tr id="tr1">
                <td style="padding-left: 21px;">
                    Base64Encoded Image Data
                </td>
                <td>
                    <textarea id="txtIsoImage" style="width: 99%; height: 50px; resize: none" class="form-control" disabled="disabled"></textarea>
                </td>
            </tr>
        </table>
    </div>
    
    <!-- fingerprint Scanner end -->
	
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/select2.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/select2.full.min.js"></script>
	

	<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui/0.4.0/angular-ui.min.js"></script> -->
	<script  src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/OtpController.js?v=5"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fingreprintScanner/morfinauth.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
		});
		
		
		
		/* var timeLeft = 30;
	    var elem = document.getElementById('some_div');
	    
	    var timerId = setInterval(countdown, 1000);
	    
	    $("#resend").click(function(){
			  timeLeft = 30;
			  $("#some_div").hide();
			//  countdown();
			  
		  });
	    
	    function countdown() {
	      if (timeLeft == -1) {
	        clearTimeout(timerId);
	        $("#hideBtn").show();
	      } else {
	        elem.innerHTML = '<b>resend OTP after '+timeLeft + ' seconds</b>';
	        timeLeft--;
	      }
	    } */
	</script>

<!-- Fingreprint Scanner -->
<script>


function encriptdata(id) {
	
	Match();
             
        
}




UninitDevice();

var nooffinger = '1';
function GetInfo() {
    document.getElementById('tdSerial').innerHTML = "";
    document.getElementById('tdMake').innerHTML = "";
    document.getElementById('tdModel').innerHTML = "";
    document.getElementById('tdWidth').innerHTML = "";
    document.getElementById('tdHeight').innerHTML = "";
    document.getElementById('imgFinger').src = "";

    document.getElementById('txtStatus').value = "";
    document.getElementById('txtImageInfo').value = "";
    document.getElementById('txtIsoTemplate').value = "";
    document.getElementById('txtIsoImage').value = "";
    document.getElementById('txtProbTemplate').value = "";
    var connectedDvcIndex = $("#cmbConnectedDvc").val();
    var connectedDvc = $("#cmbConnectedDvc option:selected").text();
    if (connectedDvcIndex == "-1" || connectedDvcIndex == null) {
        // alert("Please Select Connected Device");
        return true;
    }

    DisabledButton(true);
    var res = GetMorFinAuthInfo(connectedDvc, $("#txtClientKey").val());

    if (res.httpStaus) {

        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;

        if (res.data.ErrorCode == "0") {
            isInit = 1;
            document.getElementById('tdSerial').innerHTML = res.data.DeviceInfo.SerialNo;
            document.getElementById('tdMake').innerHTML = res.data.DeviceInfo.Make;
            document.getElementById('tdModel').innerHTML = res.data.DeviceInfo.Model;
            document.getElementById('tdWidth').innerHTML = res.data.DeviceInfo.Width;
            document.getElementById('tdHeight').innerHTML = res.data.DeviceInfo.Height;
        }
    }
    else {
        //  alert(res.err);
    }

    DisabledButton(false);
    return false;
}

function CheckDevice() {
    document.getElementById('txtStatus').value = "";
    document.getElementById('txtImageInfo').value = "";
    document.getElementById('txtIsoTemplate').value = "";
    document.getElementById('txtProbTemplate').value = "";

    var connectedDvcIndex = $("#cmbConnectedDvc").val();
    var connectedDvc = $("#cmbConnectedDvc option:selected").text();
    if (connectedDvcIndex == null) {
        //  alert("Please Select Connected Device");
        return true;
    }

    DisabledButton(true);
    var res = IsDeviceConnected(connectedDvc);
    if (res.httpStaus) {
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
    }
    else {
        // alert(res.err);
    }

    DisabledButton(false);
    return false;
}

function Init() {

    var clientKey = $("#txtClientKey").val();
    var connectedDvcIndex = $("#cmbConnectedDvc").val();
    var connectedDvc = $("#cmbConnectedDvc option:selected").text();
    if (connectedDvcIndex == "-1" || connectedDvcIndex == null) {
        //   alert("Please Select Connected Device");
        return true;
    }

    var res = InitDevice(connectedDvc, clientKey);

    if (res.httpStaus) {
        isInit = 1;
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
    }
    else {
        //  alert(res.err);
    }
    return false;
}
var isInit = 0;

function Uninit() {
    DisabledButton(true);
    var res = UninitDevice();
    if (res.httpStaus) {
        isInit = 0;
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
        document.getElementById('tdSerial').innerHTML = "";
        document.getElementById('tdMake').innerHTML = "";
        document.getElementById('tdModel').innerHTML = "";
        document.getElementById('tdWidth').innerHTML = "";
        document.getElementById('tdHeight').innerHTML = "";
        document.getElementById('imgFinger').src = "";

        document.getElementById('txtClientKey').value = "";
        document.getElementById('txtImageInfo').value = "";
        document.getElementById('txtIsoTemplate').value = "";
        document.getElementById('txtProbTemplate').value = "";
        document.getElementById('txtIsoImage').value = "";
        document.getElementById('ddlTemplateFormat').value = "0";
        document.getElementById('ddlImageFormat').value = "0";
    }
    else {
        //   alert(res.err);
    }
    DisabledButton(false);
    return false;
}

function StopCapture() {
    var res;
    res = StopCaptured();

    if (res.httpStaus) {
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
    }
    else {
        alert(res.err);
    }
    return false;
}

function GetSupportedDevice() {
    DisabledButton(true);
    var res = GetSupportedDeviceList();

    if (res.httpStaus) {
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
    }
    else {
        alert(res.err);
    }
    DisabledButton(false);
    return false;
}

function GetConnectedDevice() {
    $('#cmbConnectedDvc').empty();
    DisabledButton(true);
    var res = GetConnectedDeviceList();
    if (res.httpStaus) {
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
        if (res.data != null && res.data.ErrorCode == 0) {
            var connectedDevices = res.data.ErrorDescription;
            if (connectedDevices != null && connectedDevices != "") {

                connectedDevices = connectedDevices.split(":");
                if (connectedDevices != null && connectedDevices.length > 1) {
                    connectedDevices = connectedDevices[1].split(",");
                    if (connectedDevices != null && connectedDevices.length > 0) {
                        for (var i = 0; i < connectedDevices.length; i++) {
                            $('#cmbConnectedDvc').append("<option value=" + i + ">" + connectedDevices[i].trim() + "</option>");
                        }
                    }
                }
            }
            else {
                //  alert("Connected device not found");
                clearDeviceInfo();
            }
        }
    }
    else {
        alert(res.err);
    }
    DisabledButton(false);
    return false;
}

var delayInMilliseconds = 100; //0.1 second

function Capture() {
    GetConnectedDevice();
    CheckDevice();
    GetInfo();
    try {
        DisabledButton(true);
        document.getElementById('imgFinger').src = "";
        document.getElementById('txtImageInfo').value = "";
        document.getElementById('txtIsoTemplate').value = "";
        document.getElementById('txtProbTemplate').value = "";
        document.getElementById('txtIsoImage').value = "";
        if (!validate()) {
        }
        else {
            if (isInit == 1) {
                document.getElementById('txtStatus').value = "Capture is in progress......";
            }
            setTimeout(function () {
                quality = $("#txtQuality").val();
                timeout = $("#txtTimeout").val();

                if (timeout == "") {
                    alert("Timeout must not be null");
                    DisabledButton(false);
                    return false;
                }
                if (timeout >= 0 && timeout % 1 == 0) {

                }
                else {
                    alert("Invalid timeout or timeout should be greater or equal to 10");
                    DisabledButton(false);
                    return false;
                }

                var res = CaptureFinger(quality, timeout);
                if (res.httpStaus) {
                    if (res.data.ErrorCode == "0") {
                        document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                        var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq;
                        document.getElementById('txtImageInfo').value = imageinfo;
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + "Capture Success";
                        $('#divloader').show();
                        GetTemplateByFormat();

                    }
                    else {
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                        var response = "basic";
                        var msg = res.data.ErrorDescription;
                        msgalert(response, msg)
                    }
                }
                else {
                    document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                    var response = "basic";
                    var msg = res.err;
                    msgalert(response, msg)
                }
            }, delayInMilliseconds);
        }
    }
    catch (e) {
        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + "";

        alert(e);
    }
    DisabledButton(false);
    return false;
}

function Verify() {
    try {
        var isotemplate1 = document.getElementById('txtProbTemplate').value;
        var isotemplate2 = document.getElementById('txtIsoTemplate').value;
        var tmpFormat = $("#ddlTemplateFormat").val();
        if (tmpFormat == "0") {
            alert("Please Select Template Type");
            return true;
        }
        var len = isotemplate2.trim().length;
        len = len + len;
        if (isotemplate1.trim() == "" || isotemplate2.trim() == "") {
            if (isotemplate1.trim() == "") {
                alert("Please Enter Template for matching");
                return true;
            }
            if (isotemplate2.trim() == "") {
                alert("Please Enter Gallery Template");
                return true;
            }
        }
        else if (isotemplate1.trim().length >= len) {
            alert("Please Enter Valid Template for matching");
            return false;
        }
        else {
            tmpFormat = tmpFormat - 1;

            var res = VerifyFinger(isotemplate1, isotemplate2, tmpFormat);
            if (res.httpStaus) {
                if (res.data.Status) {
                    document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                    alert("Finger matched");
                }
                else {
                    if (res.data.ErrorCode != "0") {
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                        alert(res.data.ErrorDescription);
                    }
                    else {
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                        alert("Finger not matched");
                    }
                }
            }
            else {
                alert(res.err);
            }
        }
    }
    catch (e) {
        alert(e);
    }
    return false;
}

function Match() {
    
    try {
        document.getElementById('txtImageInfo').value = "";
        var isotemplate = document.getElementById('txtIsoTemplates').value.trim();

        var tmpFormat = $("#ddlTemplateFormat").val();
        if (tmpFormat == "0") {
            alert("Please Select Template Type");
            return true;
        }
        if (isotemplate == "" || isotemplate == null) {
            alert("Gallery Template Is Not Available");
            return true;
        }
        else {
            tmpFormat = tmpFormat - 1;
            document.getElementById('imgFinger').src = "";
            if (isInit == 1) {
                document.getElementById('txtStatus').value = "Capture is in progress......";
            }
            setTimeout(function () {
                var res = MatchFinger(quality, timeout, isotemplate, tmpFormat);
                if (res.httpStaus) {
                    if (res.data.ErrorDescription==="Success") {
                        document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                        var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq;
                        document.getElementById('txtImageInfo').value = imageinfo;
                        setTimeout(function () {

                            var sessionUrl = document.getElementById('dashboardurl').value.trim();
                            if (sessionUrl != "" || sessionUrl != null) {
                                var response = "success";
                                var msg = "Finger matched";
                                // alert(sessionUrl);
                                 angular.element(document.getElementById('YourElementId')).scope().loginBtn('test');
                               // $scope.loginBtn();
                                //window.location.href = sessionUrl;
                                $('#divloader').hide();
                                $('#divfingerprint').show();
                                msgalert(response, msg)
                                return true;
                            }
                            else {

                                alert("Time Out, Please try again Later");
                            }

                        }, delayInMilliseconds);
                    }
                    else {
                        if (res.data.ErrorCode != "0") {
                            document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                            setTimeout(function () {
                                if (second_finger == "") {
                                    var response = "basic";
                                    var msg = "Finger not matched";
                                    
                                    $('#divloader').hide();
                                    $('#divfingerprint').show();
                                    msgalert(response, msg)
                                }
                                else {
                                    MatchSecondFinger();
                                }

                            }, delayInMilliseconds);
                        }
                        else {
                            document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                            document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                            var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq;
                            document.getElementById('txtImageInfo').value = imageinfo;
                            setTimeout(function () {

                                if (second_finger == "") {
                                    var response = "basic";
                                    var msg = "Finger not matched";
                                    
                                    $('#divloader').hide();
                                    $('#divfingerprint').show();
                                    msgalert(response, msg)

                                }
                                else {
                                    MatchSecondFinger();
                                }



                            }, delayInMilliseconds);
                        }
                    }
                }
                else {
                    document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + "";
                    setTimeout(function () {
                        alert(res.err);
                    }, delayInMilliseconds);
                }
            }, delayInMilliseconds);
        }
    }
    catch (e) {
        alert(e);
    }
    return false;
}

function GetImageByFormat() {
    try {
        var imgFormat = $("#ddlImageFormat").val();
        if (imgFormat == "0") {
            alert("Please Select Image Type");
            return true;
        }
        else {
            imgFormat = imgFormat - 1;
            DisabledButton(true);
            var res = GetImage(imgFormat);
            if (res.httpStaus) {
                document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;

                if (res.data.ErrorCode == "0") {
                    if (res.data != null) {
                        document.getElementById('txtIsoImage').value = res.data.ImgData;
                    }
                }
            }
            else {
                alert(res.err);
            }
        }
    }
    catch (e) {
        alert(e);
    }
    DisabledButton(false);
    return false;
}

function GetTemplateByFormat() {
    try {
        var tmpFormat = $("#ddlTemplateFormat").val();
        if (tmpFormat == "0") {
            alert("Please Select Template Type");
            return true;
        }
        else {
            tmpFormat = tmpFormat - 1;
            DisabledButton(true);
            var res = GetTemplate(tmpFormat);
            if (res.httpStaus) {
                document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;

                if (res.data.ErrorCode == "0") {
                    if (res.data != null) {
                        document.getElementById('txtIsoTemplate').value = res.data.ImgData;
                        
                        encriptdata(res.data.ImgData);

                    }
                }
            }
            else {
                alert(res.err);
            }
        }
    }
    catch (e) {
        alert(e);
    }
    DisabledButton(false);
    return false;
}

var check = true;
function validate() {
    
    var quality = document.getElementById('txtQuality').value.trim();
    var timeout = document.getElementById('txtTimeout').value.trim();

    if (quality == "") {
        alert("Quality must not be null");
        return false;
    }
    if (timeout == "") {
        alert("Timeout must not be null");
        return false;
    }

    if (quality > 0 && quality % 1 == 0) {
    }
    else {
        alert("Invalid quality");
        return false;
    }
    if (quality <= 100)
    { }
    else
    {
        alert("Quality should be between 1 to 100");
        return false;
    }
    if (timeout > 4 && timeout % 1 == 0) {

    }
    else {
        alert("Invalid timeout or timeout should be greater or equal to 10");
        return false;
    }
    return true;
}

function clearDeviceInfo() {
    document.getElementById('tdSerial').innerHTML = "";
    document.getElementById('tdMake').innerHTML = "";
    document.getElementById('tdModel').innerHTML = "";
    document.getElementById('tdWidth').innerHTML = "";
    document.getElementById('tdHeight').innerHTML = "";
}


function DisabledButton(isDisabled) {
    if (isDisabled == false) {
        setTimeout(function () {
            $('#btnInfo').prop('disabled', isDisabled);
            $('#btnCapture').prop('disabled', isDisabled);
            $('#btnGetImage').prop('disabled', isDisabled);
            $('#btnCheckDevice').prop('disabled', isDisabled);
            $('#btnGetSupportedDevice').prop('disabled', isDisabled);
            $('#btnGetConnctedDevice').prop('disabled', isDisabled);
            $('#btnUnInit').prop('disabled', isDisabled);
        }, delayInMilliseconds);
    }
    else {
        $('#btnInfo').prop('disabled', isDisabled);
        $('#btnCapture').prop('disabled', isDisabled);
        $('#btnGetImage').prop('disabled', isDisabled);
        $('#btnCheckDevice').prop('disabled', isDisabled);
        $('#btnGetSupportedDevice').prop('disabled', isDisabled);
        $('#btnGetConnctedDevice').prop('disabled', isDisabled);
        $('#btnUnInit').prop('disabled', isDisabled);
    }
}


function MatchSecondFinger() {
    try {
        document.getElementById('txtImageInfo').value = "";
        var SecondFinger = document.getElementById('txtSecondFinger').value.trim();

        var tmpFormat = $("#ddlTemplateFormat").val();
        if (tmpFormat == "0") {
            alert("Please Select Template Type");
            return true;
        }
        if (SecondFinger == "" || SecondFinger == null) {
            var response = "basic";
            var msg = "Finger not matched";

            //  alert("Gallery Template Is Not Available");
            return true;
        }
        else {
            tmpFormat = tmpFormat - 1;
            document.getElementById('imgFinger').src = "";
            if (isInit == 1) {
                document.getElementById('txtStatus').value = "Capture is in progress......";
            }
            setTimeout(function () {

                var res = MatchFinger(quality, timeout, SecondFinger, tmpFormat);

                if (res.httpStaus) {
                    if (res.data.Status) {
                        document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                        var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq;
                        document.getElementById('txtImageInfo').value = imageinfo;
                        setTimeout(function () {

                            var sessionUrl = document.getElementById('dashboardurl').value.trim();
                            if (sessionUrl != "" || sessionUrl != null) {
                                var response = "success";
                                var msg = "Finger matched";                                        
                                
                                window.location.href = sessionUrl;
                                $('#divloader').hide();
                                $('#divfingerprint').show();
                                msgalert(response, msg)
                                return true;
                            }
                            else {

                                alert("Time Out, Please try again Later");
                            }

                        }, delayInMilliseconds);
                    }
                    else {
                        if (res.data.ErrorCode != "0") {
                            document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                            setTimeout(function () {

                                var response = "basic";
                                var msg = "Finger not matched";
                                

                                $('#divloader').hide();
                                $('#divfingerprint').show();
                                msgalert(response, msg)
                            }, delayInMilliseconds);
                        }
                        else {
                            document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                            document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;
                            var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq;
                            document.getElementById('txtImageInfo').value = imageinfo;
                            setTimeout(function () {
                                var response = "basic";
                                var msg = "Finger not matched";
                                
                                $('#divloader').hide();
                                $('#divfingerprint').show();
                                msgalert(response, msg)

                            }, delayInMilliseconds);
                        }
                    }
                }
                else {
                    document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + "";
                    setTimeout(function () {
                        alert(res.err);
                    }, delayInMilliseconds);
                }
            }, delayInMilliseconds);
        }
    }
    catch (e) {
        alert(e);
    }
    return false;
}



</script>

<!-- end of fingreprint -->

</body>
</html>