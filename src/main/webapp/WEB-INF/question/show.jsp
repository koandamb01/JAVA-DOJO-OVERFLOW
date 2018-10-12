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
	
	.container{
		padding-top: 5vh;
	}
</style>
<title>Show Question</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center text-warning">${question.getContent()}</h1>
		<br>
		<p>
			<c:forEach var="tag" items="${question.getTags()}">
				<span class="badge badge-warning badge-pill"> ${tag.subject}</span>
			</c:forEach>
		</p>
		<div class="row">
			<div class="col-md-6">
				<ul class="list-group">
				  <li class="list-group-item active">Answers</li>
				  <c:forEach var="answer" items="${question.getAnswers()}">
				  	<li class="list-group-item">${answer.getContent()}</li>
			    </c:forEach>
				</ul>
			</div>
			
			<div class="col-md-6">
				<h4>Add your answer</h4>
				<form:form action="/questions/${question.getId()}" method="POST" modelAttribute="answer">
			    <div class="form-group">
			        <form:label path="content">Answer:</form:label>
			        <form:errors path="content" class="text-danger"/>
			        <form:textarea path="content" class="form-control" rows="4" type="text"></form:textarea>
			    </div>
			    <input type="submit" value="Answer it" class="btn btn-primary"/>
			</form:form>    
			</div>
		</div>
	</div>
</body>
</html>
