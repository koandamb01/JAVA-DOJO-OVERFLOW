<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ page import = "java.io.*,java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style>
	form{ display: inline; }
</style>
<title>New Course</title>
</head>
<body>
	<div class="container">
		<h1>Courses</h1>
		<br><br>
		<div class="row">
			<div class="col-md-6">
				<form:form action="/questions/new" method="POST" modelAttribute="question">
			    <div class="form-group">
			        <form:label path="content">Question:</form:label>
			        <form:errors path="content" class="text-danger"/>
			        <form:textarea path="content" class="form-control" rows="4" type="text"></form:textarea>
			    </div>
			    
			    <div class="form-group">
			        <label>Tags:</label>
			        <span class="text-danger">${tagsError}</span>
			        <input name="tagsList" class="form-control" value="${tags}"/>
			    </div>
			  
			    <input type="submit" value="Create" class="btn btn-primary"/>
			</form:form>    
			</div>
		</div>
	</div>
</body>
</html>
