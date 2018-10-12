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
	.container{ padding-top: 5vh; }
</style>
<title>Show Course</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center text-warning">${course.name}</h1>
		<br>
		<table class="table table-striped">
		    <thead class="thead-dark">
		        <tr>
		            <th>Questions</th>
		            <th>Tags</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${questions}" var="question">
		        <tr>
		            <td><a href="questions/${question.id}">${question.content}</a></td>
		            <td>
		            	<c:forEach items="${question.getTags()}" var="tag">
		            		<span>${tag.subject}, </span>
		            	</c:forEach>
		            </td>
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		<a href="questions/new" class="btn-sm btn-primary">New Question</a>
	</div>
</body>
</html>
