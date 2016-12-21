<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>CoverMyMeds | CRUD</title>
	
	<c:url var="jQueryJS" value="/js/jquery.min.js" />
	<script src="${jQueryJS}"></script>
	
	<c:url var="jQueryValidateJS" value="/js/jquery.validate.min.js" />
	<script src="${ jQueryValidateJS }"></script>
	
	<c:url var="bootstrapJS" value="/js/bootstrap.min.js"/>	
	<script src="${bootstrapJS}"></script>
	
	<c:url var="bootstrapCSS" value="/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${bootstrapCSS}" />
	
	<c:url var="formValidationJS" value="/js/form-validation.js" />
	<script src="${ formValidationJS }"></script>

	<c:url var="mainJS" value="/js/application-main.js" />
	<script src="${ mainJS }"></script>
	
	<c:url var="mainCSS" value="/css/application-main.css" />
	<link rel="stylesheet" href="${mainCSS}" />
		
</head>

<body>

<c:url var="homePage" value="/" />
<c:url var="imagePath" value="/img/" />

<div class="container-fluid">

	<nav class="navbar navbar-inverse navbar-static-top">
	
		<div id="nav-header" class="hidden-xs" id="logo-image">
			<a href="${homePage}"><img src="${imagePath}/cmm-logo.png" alt="Cover my meds logo"/></a>
		</div>
	  
	    <!-- Hamburger button -->
	    <div class="navbar-header">
	    	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	    	</button>
	    </div>
	    
	    <c:url var="login" value="/login"/>
	    <c:url var="sign-up" value="/sign-up"/>
	    
	    <div class="collapse navbar-collapse dropdown-menu-right" id="navbar-collapse-1">
	    	<ul class="nav navbar-nav" id="main-nav">
	    		<li class="active"><a href="${homePage}">Home <span class="sr-only">(current)</span></a></li>
	    		<c:choose>
		    		<c:when test="${empty currentUser}">
			    		<li><a href="${ login }">Login</a></li>
			    		<li><a href="sign-up">Sign Up</a></li>
			    		<li><a href="#">About</a></li>
			    	</c:when>
			    	<c:otherwise>
						<c:url var="logoutAction" value="/logout" />
						<form id="logoutForm" action="${ logoutAction }" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${ CSRF_TOKEN }" />
						</form>
						<li><a id="logoutLink" href="#">Log Out</a></li>
			    		<li><a href="#">View Your Tasks</a></li>
			    		<li><a href="#">About</a></li>
			    	</c:otherwise>
			    </c:choose>
	    	</ul>
	    </div>
	    
	</nav>

	<div class="col-xs-12 col-md-10 col-md-push-1 content-wrapper">
