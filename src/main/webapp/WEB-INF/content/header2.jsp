<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>	
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EDMSApp">
<head>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/bootstrap/angular-datepicker.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allahabad High Court Admin Panel</title>

 <%@ include file="style.jsp"%>
<%@ include file="script.jsp"%>
<script>
$(document).ready(function(){
	  $('li.active').parent().parent().addClass('active');
});
</script>
</head>
<body>
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

String role=user.getUserroles().get(0).getLk().getLk_longname();
System.out.println("Role name="+role);
String url=request.getRequestURL()+"";
String home="";
String casefile="";
String caveat="";
String applications="";
String upload="";
String scrutinycase="";
String scrutinycaveat="";
String scrutinyapplication="";
String uri=(String) request.getAttribute("javax.servlet.forward.request_uri");

String basePath=(String) request.getAttribute("javax.servlet.forward.context_path");

%>
<% 
	List<ObjectMaster> parent_list  = (List<ObjectMaster>)session.getAttribute("ob_list");
	List<ObjectMaster> child_list  = (List<ObjectMaster>)session.getAttribute("ob_list");
%>
<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-default navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<a href="index.html" class="navbar-brand"><span class="navbar-logo"></span>Allahabad High Court e-Filing</a>
				</div>
				<!-- end mobile sidebar expand / collapse button -->
				
				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${pageContext.request.contextPath}/assets/img/user-13.jpg" alt="" /> 
							<span class="hidden-xs"><%=user.getUm_fullname() %></span> <b class="caret"></b>
						</a>
						<ul class="dropdown-menu animated fadeInLeft">
							<li><a href="${pageContext.request.contextPath}/dms/logout">Log Out</a></li>
						</ul>
					</li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
			
		</div>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<ul class="nav">
					<% 
							 
						 for(int i=0;i<parent_list.size();i++)
						 {
							 ObjectMaster om = parent_list.get(i);
							System.out.println(om.getOm_object_link()+" "+om.getOm_object_stages());
							if(om.getOm_object_stages()==0)
							{
								%> 
								<li class="has-sub">
						    		 <a><b class="caret pull-right"></b><i class="fa fa-file-o"></i><%=om.getOm_object_name() %>
						    		 </a>
		                            
		                        	 <ul class="sub-menu">
			                        	 <%
									 		for(int j=0;j<child_list.size();j++)
											 {
												
												 ObjectMaster ch_om = child_list.get(j);
												 String active="";
												 String fullPath=basePath+ch_om.getOm_object_link();
												 
												 if(om.getOm_id().equals(ch_om.getOm_parent_id()))
												 {													 
													 if(uri.equals(fullPath)){
														 active="active";														 
													 }
													 else{
														 active="";	 
													 }
													 //add child
													 %>
													 <li class=<%=active %>><a href="${pageContext.request.contextPath}<%=ch_om.getOm_object_link() %>"> <%=ch_om.getOm_object_name() %> </a></li>
													 <%
												 }
											 }
			                        	 %>	 
		                        	 </ul>
		                        </li>
		                       <%
							}
						 }
					%>
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		
		<!-- end #sidebar -->
		
		<!-- begin #content -->
			
	<!-- ================== END PAGE LEVEL JS ================== -->

