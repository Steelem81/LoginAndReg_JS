<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>Welcome</h1>
	<p> Join our growing community.</p>
	<div class="d-flex">
		<div class="container col-3">
			<h2>Register</h2>
			<form:form class="form" action="/register" method="post" modelAttribute="newUser">
				<div class="form-group row">
					<form:label path="userName">User Name</form:label>
					<form:input path="userName" class="form-control"/>
					<form:errors path="userName" class="text-danger"/> 
				</div>
				<div class="form-group row">
					<form:label path="email">Email</form:label>
					<form:input path="email" class="form-control"/>
					<form:errors path="email" class="text-danger"/> 
				</div>
				<div class="form-group row">
					<form:label path="password">Password</form:label>
					<form:input path="password" class="form-control"/>
					<form:errors path="password" class="text-danger"/> 
				</div>
				<div class="form-group row">
					<form:label path="confirm">Confirm PW</form:label>
					<form:input path="confirm" class="form-control"/>
					<form:errors path="confirm" class="text-danger"/> 
				</div>
				<button class='btn btn-primary'>Submit</button>
			</form:form>
		</div>
		
		<div class="container col-3">
			<h2>Log in</h2>
				<form:form class="form" action="/login" method="post" modelAttribute="newLogin">
				<div class="form-group row">
					<form:label path="email">Email</form:label>
					<form:input path="email" class="form-control"/>
					<form:errors path="email" class="text-danger"/> 
				</div>
				<div class="form-group row">
					<form:label path="password">Password</form:label>
					<form:input path="password" class="form-control"/>
					<form:errors path="password" class="text-danger"/> 
				</div>
		
				<button class="btn btn-success">Submit</button>
			</form:form>
		</div>
	</div>
</div>
   
</body>
</html>