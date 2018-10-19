<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Event Home Page</title>
	</head>
	<body>
	<fieldset>
	<h1>Register</h1>
    <p><form:errors path="user.*"/></p>
    <form:form method="POST" action="/registration" modelAttribute="user">
        <p>
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName"/>
        </p>
        <p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
            <form:label path="location">Location:</form:label>
            <form:input path="location"/>
        	<form:select path="state">
        	<c:forEach items="${states}" var="us">
			  <form:option label="${us.state}" value="${us.id}"></form:option>
			</c:forEach>
			</form:select>
		</p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
	</fieldset>
	<fieldset>
    <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <form method="POST" action="/login" modelAttribute="user">
        <p>
            <label type="email" for="email">Email</label>
            <input type="text" id="email" name="emaillogin"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="passwordlogin"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>  
	</fieldset>
	</body>
</html>